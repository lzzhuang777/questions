package com.lzz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.dto.QuestionAnswerVO;
import com.lzz.model.QmsAnswer;
import com.lzz.model.QmsQuestion;
import com.lzz.service.QmsQuestionService;
import io.swagger.annotations.Api;
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
@Api(tags = "QmsQuestionController", description = "试题模块")
@RestController
@RequestMapping("/qms/qmsQuestion")
public class QmsQuestionController {

    @Autowired
    private QmsQuestionService qmsQuestionService;


    @ApiOperation("添加试题")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody QuestionAnswerVO questionAnswerVO) {
        boolean success = qmsQuestionService.create(questionAnswerVO);
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改试题")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@RequestBody QuestionAnswerVO questionAnswerVO) {
        boolean success = qmsQuestionService.update( questionAnswerVO);
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

    @ApiOperation("查询试题")
    @RequestMapping(value = "/selectQuesList",method = RequestMethod.GET)
    public CommonResult< List<QmsQuestion>> selectQuesList(@RequestParam(value = "query",defaultValue = "") String query,
                                                           @RequestParam(value = "testId") Long testId){
        List<QmsQuestion> qmsQuestionList = qmsQuestionService.selectQuesList(query,testId);
        return CommonResult.success(qmsQuestionList);
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

    @ApiOperation("根据题目 ids 查询答案集合")
    @RequestMapping(value = "/getQuestionsByIds",method = RequestMethod.POST)
    public List<QuestionAnswerVO> getQuestionsByIds(@RequestBody List<Long> ids){

       return qmsQuestionService.getQuestionsByIds(ids);
    }

    @ApiOperation("根据题目 id 查询试题答案详情")
    @RequestMapping(value = "/getQuestionAnswerVO/{id}",method = RequestMethod.GET)
    public QuestionAnswerVO getQuestionAnswerVO(@PathVariable Long id){

        return qmsQuestionService.getQuestionAnswerVO(id);
    }

    @ApiOperation("查询测验的题目")
    @RequestMapping(value = "/selectQuestionsByTestId/{testId}",method = RequestMethod.GET)
    public List<QmsQuestion> selectQuestionsByTestId (@PathVariable Long testId){

        return qmsQuestionService.selectQuestionsByTestId(testId);
    }

}

