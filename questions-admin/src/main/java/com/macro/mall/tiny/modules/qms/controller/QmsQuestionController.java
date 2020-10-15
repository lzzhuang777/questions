package com.macro.mall.tiny.modules.qms.controller;

import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.dto.QuestionAnswerVO;
import com.lzz.model.QmsAnswer;
import com.lzz.model.QmsQuestion;
import com.macro.mall.tiny.feign.QuestionFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/9/29 16:09
 */
@Api(tags = "QuestionController", description = "后台题目管理")
@Controller
@RequestMapping("/admin/question")
public class QmsQuestionController {

    @Autowired
    private QuestionFeignService questionFeignService;

    @ApiOperation(value = "分页查询题目")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<QmsQuestion>> list(@RequestParam(value = "title", defaultValue = "") String title,
                                                      @RequestParam(value = "type", defaultValue = "-1") Long type,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        return questionFeignService.list(title, type, pageSize, pageNum);
    }

    @ApiOperation("添加试题")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody QuestionAnswerVO questionAnswerVO) {
        return questionFeignService.create(questionAnswerVO);
    }

    @ApiOperation("根据ID获取试题详情")
    @RequestMapping(value = "/{questionId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<QmsQuestion> getQuestionById(@PathVariable Long questionId) {
        return questionFeignService.getQuestionById(questionId);
    }

    @ApiOperation("修改试题")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody QuestionAnswerVO questionAnswerVO) {
        return questionFeignService.update(questionAnswerVO);
    }

    @ApiOperation("根据题目 id 查询答案")
    @ResponseBody
    @RequestMapping(value = "/answerList/{questionId}",method = RequestMethod.GET)
    public CommonResult<List<QmsAnswer>> getAnswerList (@PathVariable Long questionId){

        List<QmsAnswer> answerList = questionFeignService.getAnswerList(questionId).getData();
        if(answerList.size()>0){
            return CommonResult.success(answerList);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据题目 id 查询答案")
    @ResponseBody
    @RequestMapping(value = "/getQuestionAnswerVO/{id}",method = RequestMethod.GET)
    public CommonResult<QuestionAnswerVO> getQuestionAnswerVO(@PathVariable Long id){

        QuestionAnswerVO questionAnswerVO = questionFeignService.getQuestionAnswerVO(id);
        return CommonResult.success(questionAnswerVO);
    }


}
