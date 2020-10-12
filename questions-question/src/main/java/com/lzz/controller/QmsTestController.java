package com.lzz.controller;

import com.lzz.api.CommonResult;
import com.lzz.service.QmsTestQuestionRelationsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/12 15:40
 */

@Api(tags = "QmsTestController", description = "测验模块")
@RestController
@RequestMapping("/qms/qmsTest")
public class QmsTestController {

    @Autowired
    private QmsTestQuestionRelationsService testQuestionRelationsService;

    @ApiOperation("查询测验的试题")
    @RequestMapping(value = "selectIdsByTestId/{testId}",method = RequestMethod.GET)
    public  List<Long> selectIdsByTestId (@PathVariable Long testId){

        return testQuestionRelationsService.selectIdsByTestId(testId);
    }


}
