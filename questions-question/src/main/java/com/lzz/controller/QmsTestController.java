package com.lzz.controller;

import com.lzz.api.CommonResult;
import com.lzz.model.QmsTest;
import com.lzz.service.QmsTestQuestionRelationsService;
import com.lzz.service.QmsTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private QmsTestService qmsTestService;

    @ApiOperation("查询测验的试题")
    @RequestMapping(value = "selectIdsByTestId/{testId}", method = RequestMethod.GET)
    public List<Long> selectIdsByTestId(@PathVariable Long testId) {

        return testQuestionRelationsService.selectIdsByTestId(testId);
    }

    @ApiOperation("添加测验")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody QmsTest qmsTest) {

        boolean result = qmsTestService.save(qmsTest);
        if (result) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }


}
