package com.lzz.controller;


import com.lzz.api.CommonResult;
import com.lzz.model.SmsStudyTime;
import com.lzz.service.SmsStudyTimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzz
 * @since 2020-09-27
 */
@Api(tags = "SmsStudyTimeController", description = "学习时间")
@RestController
@RequestMapping("/sms/smsStudyTime")
public class SmsStudyTimeController {

    @Autowired
    private SmsStudyTimeService smsStudyTimeService;

    @ApiOperation("查询学习时间集合")
    @RequestMapping(value = "/list/{id}",method = RequestMethod.GET)
    public CommonResult memberStudyTimeTest(@PathVariable("id") Long id) {
        SmsStudyTime smsStudyTime = smsStudyTimeService.getById(id);
        return CommonResult.success(Arrays.asList(smsStudyTime));
    }

}

