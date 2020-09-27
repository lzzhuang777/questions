package com.lzz.controller;


import com.lzz.api.CommonResult;
import com.lzz.model.SmsStudyTime;
import com.lzz.service.SmsStudyTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */
@RestController
@RequestMapping("/sms/smsStudyTime")
public class SmsStudyTimeController {

    @Autowired
    private SmsStudyTimeService smsStudyTimeService;

    @RequestMapping("/list/{id}")
    public CommonResult memberStudyTimeTest(@PathVariable("id") Long id) {
        SmsStudyTime smsStudyTime = smsStudyTimeService.getById(id);
        return CommonResult.success(Arrays.asList(smsStudyTime));
    }
}

