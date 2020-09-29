package com.macro.mall.tiny.feign;

import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.model.QmsQuestion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/9/29 13:30
 */

@FeignClient("questions-question")
public interface QuestionFeignService {

    @RequestMapping(value = "/qms/qmsQuestion/list",method = RequestMethod.GET)
     CommonResult<CommonPage<QmsQuestion>> list(@RequestParam(value = "title",defaultValue = "") String title,
                                                                 @RequestParam(value = "type",defaultValue = "-1") Long type,
                                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);
}
