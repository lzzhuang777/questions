package com.lzz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.model.QmsAnswer;
import com.lzz.model.QmsQuestion;
import com.lzz.service.QmsQuestionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzz
 * @since 2020-09-27
 */
@RestController
@RequestMapping("/qms/qmsQuestion")
public class QmsQuestionController {

    @Autowired
    private QmsQuestionService qmsQuestionService;


    @ApiOperation("添加试题")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody QmsQuestion qmsQuestion) {
        boolean success = qmsQuestionService.create(qmsQuestion);
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改试题")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id,
                               @RequestBody QmsQuestion qmsQuestion) {
        boolean success = qmsQuestionService.update(id, qmsQuestion);
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据ID获取试题详情")
    @RequestMapping(value = "/{questionId}",method = RequestMethod.GET)
    public CommonResult<QmsQuestion> getQuestionById(@PathVariable Long questionId){
       return CommonResult.success(qmsQuestionService.getQuestionById(questionId));
    }

    @ApiOperation("分页查询试题集合")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult<CommonPage<QmsQuestion>> list(@RequestParam(value = "title",defaultValue = "") String title,
                                                                 @RequestParam(value = "type",defaultValue = "-1") Long type,
                                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Page<QmsQuestion> qmsQuestionList = qmsQuestionService.list(title,type,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(qmsQuestionList));
    }

    @ApiOperation("根据题目 id 查询答案")
    @RequestMapping(value = "/answerList/{questionId}",method = RequestMethod.GET)
    public CommonResult<List<QmsAnswer>> getAnswerList (@PathVariable Long questionId){

        List<QmsAnswer> answerList = qmsQuestionService.getAnswerList(questionId);
        if(answerList.size()>0){
            return CommonResult.success(answerList);
        }
        return CommonResult.failed();
    }



}

