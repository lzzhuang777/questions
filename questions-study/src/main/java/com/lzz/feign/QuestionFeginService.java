package com.lzz.feign;

import com.lzz.dto.QuestionAnswerVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/10 10:41
 */
@FeignClient(name ="questions-question")
public interface QuestionFeginService {

    @RequestMapping(value = "/qms/qmsQuestion/getQuestionsByIds",method = RequestMethod.POST)
    List<QuestionAnswerVO> getQuestionsByIds(@RequestBody List<Long> ids);

    @RequestMapping(value = "/qms/qmsQuestion/getQuestionAnswerVOById",method = RequestMethod.GET)
    QuestionAnswerVO getQuestionAnswerVOById(@RequestParam Long quesId);
}
