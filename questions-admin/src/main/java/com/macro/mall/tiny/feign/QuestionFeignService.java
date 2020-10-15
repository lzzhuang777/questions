package com.macro.mall.tiny.feign;

import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.dto.QuestionAnswerVO;
import com.lzz.model.QmsAnswer;
import com.lzz.model.QmsQuestion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    CommonResult create(@RequestBody QuestionAnswerVO questionAnswerVO);

    @RequestMapping(value = "/qms/qmsQuestion/update", method = RequestMethod.POST)
    CommonResult update(@RequestBody QuestionAnswerVO questionAnswerVO);

    @RequestMapping(value = "/qms/qmsQuestion/answerList/{questionId}",method = RequestMethod.GET)
    CommonResult<List<QmsAnswer>> getAnswerList (@PathVariable Long questionId);

    @RequestMapping(value = "/qms/qmsQuestion/getQuestionAnswerVO/{id}",method = RequestMethod.GET)
    QuestionAnswerVO getQuestionAnswerVO(@PathVariable Long id);

    @RequestMapping(value = "/qms/qmsQuestion/selectQuesList",method = RequestMethod.GET)
    CommonResult< List<QmsQuestion>> selectQuesList(@RequestParam(value = "query",defaultValue = "") String query,
                                                    @RequestParam(value = "testId") Long testId);

    @RequestMapping(value = "/qms/qmsQuestion/selectQuestionsByTestId/{testId}",method = RequestMethod.GET)
    List<QmsQuestion> selectQuestionsByTestId (@PathVariable Long testId);
}
