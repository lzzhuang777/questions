package com.lzz.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.lzz.domain.SignResultVO;
import com.lzz.service.RedisService;
import com.lzz.service.UmsSignService;
import com.lzz.utils.Constants;
import com.netflix.hystrix.util.LongAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/26 14:18
 */
@Service
public class UmsSignServiceImpl implements UmsSignService {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean signIn(Long memberId) {

        //用String.format拼装好单个用户的位图key
        String signKey = String.format(Constants.Redis_Expire.USER_SIGN_IN, LocalDate.now().getYear(), memberId);
        //位图的偏移点为当天的日期,如今天,偏移值就是1026
        long monthAndDay = Long.parseLong(LocalDate.now().format(DateTimeFormatter.ofPattern("MMdd")));
        if(!redisService.getBit(signKey,monthAndDay)){
            //签到
            redisService.setBit(signKey,monthAndDay,true);
            //用户签到次数
            redisService.incr(String.format(Constants.Redis_Expire.USER_SIGN_IN_COUNT,Long.parseLong(LocalDate.now().format(DateTimeFormatter.ofPattern("yyMM"))), memberId),1);
        }
        return true;
    }

    @Override
    public SignResultVO selectSignIn(Long memberId, Integer year, Integer month) {
        SignResultVO signResultVO = new SignResultVO();
        boolean signFlag = Boolean.FALSE;
        String signKey = String.format(Constants.Redis_Expire.USER_SIGN_IN, year, memberId);
        LocalDate date = LocalDate.of(year, month, 1);
        //查询出一个偏移值区间的位图集合
        List<Long> list = redisService.bitField(signKey,date.lengthOfMonth(),month * 100 + 1,false);
        //查询reids中当前用户补签的hash列表 (hash列表的key为补签的日期,value存在就说明这个日期补签了)
        String retroactiveKey = String.format(Constants.Redis_Expire.USER_RETROACTIVE_SIGN_IN, date.getMonthValue(), memberId);
        Set<Object> keys = redisService.sMembers(retroactiveKey);
        TreeMap<Integer, Integer> signMap = new TreeMap<>();
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
                signMap.put(Integer.parseInt(d.format(DateTimeFormatter.ofPattern("dd"))), type);
                v >>= 1;
            }
        }
        Object o = redisService.get(String.format(Constants.Redis_Expire.USER_SIGN_IN_COUNT,Long.parseLong(LocalDate.now().format(DateTimeFormatter.ofPattern("yyMM"))), memberId));
        Integer count = ObjectUtil.isEmpty(0) ? 0 :(Integer)o;
        signResultVO.setSignFlag(signFlag ? 1 : 0);
        signResultVO.setSignMap(signMap);
        signResultVO.setSignCount(count);
        return signResultVO;
    }

    @Override
    public void integrationRules(Map<String,Integer> map){
        redisService.hSetAll(Constants.Redis_Expire.Integration_Rules,map);
    }



}
