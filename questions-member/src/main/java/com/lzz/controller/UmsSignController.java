package com.lzz.controller;

import com.lzz.api.CommonResult;
import com.lzz.domain.SignResultVO;
import com.lzz.service.RedisService;
import com.lzz.service.UmsSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/26 14:07
 */
@Api(tags = "UmsSignController", description = "签到模块")
@RestController
@RequestMapping("/ums/sign")
public class UmsSignController {

    @Autowired
    private UmsSignService umsSignService;

    @ApiOperation("用户签到")
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public CommonResult signIn(@RequestParam Long memberId) {

       boolean result = umsSignService.signIn(memberId);
       if(result){
           return CommonResult.success(null);
       }
       return CommonResult.failed("今日已签到");
    }

    @ApiOperation("查询用户在year年month月的签到情况")
    @RequestMapping(value = "/selectSignIn", method = RequestMethod.GET)
    public CommonResult selectSignIn(@RequestParam Long memberId, @RequestParam Integer year, @RequestParam Integer month){

        SignResultVO signResultVO = umsSignService.selectSignIn(memberId, year, month);

        return CommonResult.success(signResultVO);
    }

}
