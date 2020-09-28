package com.lzz.feign;

import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.model.SmsViewLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/9/28 16:05
 */
@FeignClient(name = "questions-study")
public interface StudyViewLogFeignService {

    @RequestMapping(value = "/sms/smsViewLog/list/{memberId}", method = RequestMethod.GET)
    public CommonResult<CommonPage<SmsViewLog>> list(@PathVariable Long memberId, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);
}
