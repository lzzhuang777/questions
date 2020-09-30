package com.macro.mall.tiny.feign;

import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.model.QmsQuestion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/9/29 13:30
 */

@FeignClient("questions-question")
public interface QuestionFeignService {

    @RequestMapping(value = "/qms/qmsQuestion/list", method = RequestMethod.GET)
    CommonResult<CommonPage<QmsQuestion>> list(@RequestParam(value = "title", defaultValue = "") String title,
                                               @RequestParam(value = "type", defaultValue = "-1") Long type,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);

    @RequestMapping(value = "/qms/qmsQuestion/{questionId}", method = RequestMethod.GET)
    CommonResult<QmsQuestion> getQuestionById(@PathVariable Long questionId);

    @RequestMapping(value = "/qms/qmsQuestion/create", method = RequestMethod.POST)
    CommonResult create(@RequestBody QmsQuestion qmsQuestion);

    @RequestMapping(value = "/qms/qmsQuestion/update/{id}", method = RequestMethod.POST)
    CommonResult update(@PathVariable Long id,
                        @RequestBody QmsQuestion qmsQuestion);
}
