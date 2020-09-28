package com.lzz.controller;


import com.lzz.api.CommonResult;
import com.lzz.model.QmsQuestion;
import com.lzz.service.QmsQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */
@RestController
@RequestMapping("/qms/qmsQuestion")
public class QmsQuestionController {

    @Autowired
    private QmsQuestionService qmsQuestionService;

    @RequestMapping(value = "/getQuestionById/{questionId}",method = RequestMethod.GET)
    public CommonResult<QmsQuestion> getQuestionById(@PathVariable Long questionId){
       return CommonResult.success(qmsQuestionService.getQuestionById(questionId));
    }

}

