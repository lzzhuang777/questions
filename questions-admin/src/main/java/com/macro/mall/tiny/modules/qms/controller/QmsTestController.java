package com.macro.mall.tiny.modules.qms.controller;

import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.dto.TestRelationParamVO;
import com.lzz.model.QmsTest;
import com.macro.mall.tiny.feign.TestFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/15 11:26
 */
@Api(tags = "QmsTestController", description = "后台测验管理")
@Controller
@RequestMapping("/admin/qmsTest")
public class QmsTestController {

    @Autowired
    private TestFeignService testFeignService;

    @ApiOperation("分页查询测验")
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult<CommonPage<QmsTest>> list(@RequestParam(value = "testName",defaultValue = "") String testName,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        return testFeignService.list(testName, pageSize, pageNum);
    }

    @ApiOperation("添加测验")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody QmsTest qmsTest){
        return testFeignService.create(qmsTest);
    }

    @ApiOperation("测验分配试题")
    @ResponseBody
    @RequestMapping(value = "/addTestQuestions/{testId}", method = RequestMethod.POST)
    public CommonResult addTestQuestions(@RequestBody List<Long> quesIds, @PathVariable Long testId){

        return testFeignService.addTestQuestions(quesIds, testId);
    }

    @ApiOperation("解除测验题目关系")
    @ResponseBody
    @RequestMapping(value = "/delTestQuestions",method = RequestMethod.POST)
    public CommonResult delTestQuestions (@RequestBody TestRelationParamVO relationParamVO){
        return testFeignService.delTestQuestions(relationParamVO);
    }


}
