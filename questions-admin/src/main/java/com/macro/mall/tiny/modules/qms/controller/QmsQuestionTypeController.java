package com.macro.mall.tiny.modules.qms.controller;

import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.model.QmsType;
import com.macro.mall.tiny.feign.QuestionTypeFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/9/30 9:52
 */

@Api(tags = "QmsQuestionTypeController", description = "后台题目类型管理")
@RestController
@RequestMapping("/admin/questionType")
public class QmsQuestionTypeController {

    @Autowired
    private QuestionTypeFeignService questionTypeFeignService;

    @ApiOperation("分页查询题目类型")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<QmsType>> list(@RequestParam(value = "type", defaultValue = "") String type,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        return questionTypeFeignService.list(type, pageSize, pageNum);
    }

    @ApiOperation("用ID获取题目类型")
    @RequestMapping(value = "/{qmsTypeId}", method = RequestMethod.GET)
    public CommonResult<QmsType> getQuestionById(@PathVariable Long qmsTypeId){

        return questionTypeFeignService.getQuestionById(qmsTypeId);
    }

    @ApiOperation("获取所有题目类型")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult listAll() {

        List<QmsType> list = questionTypeFeignService.listAll();
        return CommonResult.success(list);
    }


}
