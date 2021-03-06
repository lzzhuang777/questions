package com.lzz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.dto.TestRelationParamVO;
import com.lzz.model.QmsQuestion;
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

    @ApiOperation("查询测验的试题id")
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

    @ApiOperation("分页查询试题集合")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult<CommonPage<QmsTest>> list(@RequestParam(value = "testName",defaultValue = "") String testName,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Page<QmsTest> qmsTestList = qmsTestService.list(testName,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(qmsTestList));
    }

    @ApiOperation("获取所有测验")
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public CommonResult<List<QmsTest>> listAll (@RequestParam Long type){
        List<QmsTest> result = qmsTestService.listAll(type);
        return CommonResult.success(result);
    }

    @ApiOperation("添加测验与试题关系")
    @RequestMapping(value = "/addTestQuestions/{testId}", method = RequestMethod.POST)
    public CommonResult addTestQuestions(@RequestBody List<Long> quesIds,@PathVariable Long testId) {

        boolean result = qmsTestService.addTestQuestions(quesIds, testId);
        if (result) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("解除测验与试题关系")
    @RequestMapping(value = "/delTestQuestions",method = RequestMethod.POST)
    public CommonResult delTestQuestions (@RequestBody TestRelationParamVO relationParamVO){

      int result = testQuestionRelationsService.delTestQuestions(relationParamVO);
      if(result == 1){
          return CommonResult.success(null);
      }
      return CommonResult.failed("解除关系失败");
    }



}
