package com.lzz.feign;

import com.lzz.api.CommonResult;
import com.lzz.model.QmsQuestion;
import com.lzz.model.QmsTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/12 16:02
 */

@FeignClient(name = "questions-question")
public interface TestFeignService {

    @RequestMapping(value = "/qms/qmsTest/selectIdsByTestId/{testId}", method = RequestMethod.GET)
    List<Long> selectIdsByTestId(@PathVariable Long testId);

    @RequestMapping(value = "/qms/qmsTest/{questionId}",method = RequestMethod.GET)
    CommonResult<QmsQuestion> getQuestionById(@PathVariable Long questionId);

    @RequestMapping(value = "/qms/qmsTest/listAll",method = RequestMethod.GET)
    CommonResult<List<QmsTest>> listAll (@RequestParam Long type);
}
