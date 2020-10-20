package com.lzz.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzz.api.ResultCode;
import com.lzz.dto.LoginParam;
import com.lzz.exception.ApiException;
import com.lzz.mapper.UmsMemberMapper;
import com.lzz.model.UmsMember;
import com.lzz.service.RedisService;
import com.lzz.service.UmsMemberService;
import com.lzz.utils.Constants;
import com.lzz.utils.GenerateToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzz
 * @since 2020-09-27
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

    private Logger logger = LoggerFactory.getLogger(UmsMemberServiceImpl.class);
    @Autowired
    private RedisService redisService;
    @Override
    public Object[] login(LoginParam loginParam) {

        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMember ::getPhone,loginParam.getPhone())
                .eq(UmsMember::getPassword, SecureUtil.md5(loginParam.getPassword()));
        UmsMember umsMember = getOne(wrapper);
        if(ObjectUtil.isEmpty(umsMember)){
            return null;
        }
        String token = GenerateToken.generateToken(umsMember);
        saveToken(token,umsMember);
        return new Object[]{token};
    }

    private void saveToken(String token,UmsMember umsMember){

        String tokenKey = Constants.USER_TOKEN_PREFIX + umsMember.getPhone();
        Object tokenValue = redisService.get(tokenKey);
        if(ObjectUtil.isNotEmpty(tokenValue)){
            logger.info("[saveToken]" + "删除重复登陆信息");
            redisService.del(tokenValue.toString());
        }
        logger.info("[saveToken]" + "缓存token信息");
        redisService.set(tokenKey,token, Constants.Redis_Expire.SESSION_TIMEOUT);
        logger.info("[saveToken]" + "缓存用户信息");
        redisService.set(token,JSON.toJSONString(umsMember),Constants.Redis_Expire.SESSION_TIMEOUT);
    }

    @Override
    public String loadCurrentUserByTokenAsJson(String token) {
        String tokenUser = null;
        if ((tokenUser = (String) redisService.get(token)) == null) {
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }
        return tokenUser;
    }

}
