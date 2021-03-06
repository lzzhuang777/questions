package com.lzz.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.lzz.domain.SignResultVO;
import com.lzz.dto.SignDetail;
import com.lzz.model.UmsMember;
import com.lzz.model.UmsMessage;
import com.lzz.model.UmsMessageTamplate;
import com.lzz.service.*;
import com.lzz.utils.Constants;
import com.netflix.hystrix.util.LongAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/26 14:18
 */
@Service
public class UmsSignServiceImpl implements UmsSignService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private UmsMemberService umsMemberService;
    @Autowired
    private UmsMessageTamplateService tamplateService;
    @Autowired
    private UmsMessageService umsMessageService;

    @Override
    public boolean signIn(Long memberId) {

        //用String.format拼装好单个用户的位图key
        String signKey = String.format(Constants.Redis_Expire.USER_SIGN_IN, LocalDate.now().getYear(), memberId);
        //位图的偏移点为当天的日期,如今天,偏移值就是1026
        long monthAndDay = Long.parseLong(LocalDate.now().format(DateTimeFormatter.ofPattern("MMdd")));
        if (!redisService.getBit(signKey, monthAndDay)) {
            //签到
            redisService.setBit(signKey, monthAndDay, true);
            //用户签到次数增加1
            redisService.incr(String.format(Constants.Redis_Expire.USER_SIGN_IN_COUNT, Long.parseLong(LocalDate.now().format(DateTimeFormatter.ofPattern("yyMM"))), memberId), 1);
            //积分增加
            umsMemberService.addIntegration(Constants.INTEGRATION_SIGN, memberId);
            sendMessage(memberId);  //发送签到成功消息
        }
        return true;
    }

    private void sendMessage(Long memberId) {

        UmsMessageTamplate template = tamplateService.getById(Constants.Message_Template.Sign_Day);
        UmsMessage umsMessage = new UmsMessage();
        umsMessage.setContent(template.getTemplateContent());
        umsMessage.setReceiveId(memberId);
        umsMessage.setSendId(Constants.SYSTEM_ID);
        umsMessage.setType(2);
        umsMessageService.save(umsMessage);
    }

    @Override
    public SignResultVO selectSignIn(String token, Integer year, Integer month) {
        UmsMember umsMember =umsMemberService.loadCurrentUserByTokenAsJson(token);
        SignResultVO signResultVO = new SignResultVO();
        boolean signFlag = Boolean.FALSE;
        String signKey = String.format(Constants.Redis_Expire.USER_SIGN_IN, year, umsMember.getId());
        LocalDate date = LocalDate.of(year, month, 1);
        //查询出一个偏移值区间的位图集合
        List<Long> list = redisService.bitField(signKey, date.lengthOfMonth(), month * 100 + 1, false);
        //查询reids中当前用户补签的hash列表 (hash列表的key为补签的日期,value存在就说明这个日期补签了)
        String retroactiveKey = String.format(Constants.Redis_Expire.USER_RETROACTIVE_SIGN_IN, date.getMonthValue(), umsMember.getId());
        Set<Object> keys = redisService.sMembers(retroactiveKey);
        List<SignDetail> signDetails = new ArrayList<>(date.lengthOfMonth());
        if (list != null && list.size() > 0) {
            // 由低位到高位，为0表示未签，为1表示已签
            long v = list.get(0) == null ? 0 : list.get(0);
            //循环次数为当月的天数
            for (int i = date.lengthOfMonth(); i > 0; i--) {
                LocalDate d = date.withDayOfMonth(i);
                int type = 0;
                if (v >> 1 << 1 != v) {
                    //状态为正常签到
                    type = 1;
                    //这里和当前日期对比,方便前端特殊标记今天是否签到
                    if (d.compareTo(LocalDate.now()) == 0) {
                        signFlag = Boolean.TRUE;
                    }
                }
                if (keys.contains(d.getDayOfMonth() + "")) {
                    //状态为补签
                    type = 2;
                }
                //返回给前端当月的所有日期,以及签,补签或者未签的状态
                SignDetail signDetail = new SignDetail();
                signDetail.setDay(Integer.parseInt(d.format(DateTimeFormatter.ofPattern("dd"))));
                signDetail.setIsSign(type);
                signDetails.add(signDetail);
                //signMap.put(Integer.parseInt(d.format(DateTimeFormatter.ofPattern("dd"))), type);
                v >>= 1;
            }
        }
        Collections.sort(signDetails);
        Object o = redisService.get(String.format(Constants.Redis_Expire.USER_SIGN_IN_COUNT, Long.parseLong(LocalDate.now().format(DateTimeFormatter.ofPattern("yyMM"))), umsMember.getId()));
        Integer count = ObjectUtil.isEmpty(o) ? 0 : (Integer) o;
        signResultVO.setSignFlag(signFlag ? 1 : 0);
        signResultVO.setSignList(signDetails);
        signResultVO.setSignCount(count);
        return signResultVO;
    }

    @Override
    public void integrationRules(Map<String, Integer> map) {
        redisService.hSetAll(Constants.Redis_Expire.Integration_Rules, map);
    }

    @Override
    public int cumulativeSign(Long memberId, String signCount) {
        Integer integration = (Integer) redisService.hGet(Constants.Redis_Expire.Integration_Rules, signCount);
        UmsMessageTamplate template = tamplateService.getById(Constants.Message_Template.CUMULATIVE_SIGN);
        UmsMessage umsMessage = new UmsMessage();
        umsMessage.setContent(String.format(template.getTemplateContent(),Integer.parseInt(signCount),integration));
        umsMessage.setReceiveId(memberId);
        umsMessage.setSendId(Constants.SYSTEM_ID);
        umsMessage.setType(2);
        umsMessageService.save(umsMessage);
        return umsMemberService.addIntegration(integration, memberId);
    }



}
