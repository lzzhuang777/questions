package com.lzz.service.impl;

import com.lzz.service.RedisService;
import com.lzz.service.UmsSignService;
import com.lzz.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        }
        return true;
    }

    @Override
    public void selectSignIn(Long memberId, Integer year, Integer month) {
        
    }
}
