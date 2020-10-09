package com.lzz.feign;

import com.lzz.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/9/27 14:59
 */
@FeignClient(name ="questions-study")
public interface StudyTimeFeignSevice {

    @RequestMapping("/sms/smsStudyTime/list/{id}")
    CommonResult memberStudyTimeTest(@PathVariable("id") Long id);
}
