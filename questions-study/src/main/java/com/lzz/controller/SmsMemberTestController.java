package com.lzz.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.dto.MemberTestVO;
import com.lzz.dto.QuestionAnswerVO;
import com.lzz.model.SmsMemberTest;
import com.lzz.model.SmsTestAnswer;
import com.lzz.model.UmsMember;
import com.lzz.service.SmsMemberTestService;
import com.lzz.service.SmsTestAnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/12 13:37
 */

@Api(tags = "SmsMemberTestController", description = "用户测验中心")
@RestController
@RequestMapping("/sms/smsMemberTest")
public class SmsMemberTestController {

    @Autowired
    private SmsMemberTestService smsMemberTestService;
    @Autowired
    private SmsTestAnswerService smsTestAnswerService;

    @ApiOperation("查询用户测验集合")
    @RequestMapping(value = "/getTestByMemberId/{memberId}", method = RequestMethod.GET)
    public CommonResult<List<SmsMemberTest>> getTestByMemberId(@PathVariable Long memberId) {

        List<SmsMemberTest> list = smsMemberTestService.getTestByMemberId(memberId);
        if (CollUtil.isEmpty(list)) {
            return CommonResult.failed();
        }
        return CommonResult.success(list);
    }

    @ApiOperation("查询测验结果")
    @RequestMapping(value = "/getTestResult/{testId}", method = RequestMethod.GET)
    public CommonResult<List<SmsTestAnswer>> getTestResult(@PathVariable Long testId) {

        List<SmsTestAnswer> list = smsTestAnswerService.getTestAnswer(testId);
        if (CollUtil.isEmpty(list)) {
            return CommonResult.failed();
        }
        return CommonResult.success(list);
    }

    @ApiOperation("分页查询测验题目")
    @RequestMapping(value = "/getTestQuesList/{memberTestId}", method = RequestMethod.POST)
    public CommonResult<CommonPage<QuestionAnswerVO>> getTestQuesList (@PathVariable Long memberTestId,@RequestParam Integer pageNum){

        Page<QuestionAnswerVO> page = smsMemberTestService.getTestQuesList(memberTestId,pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("选择题目答案")
    @RequestMapping(value = "/submitQuesAnswer", method = RequestMethod.POST)
    public CommonResult submitQuesAnswer(@RequestBody SmsTestAnswer smsTestAnswer){
        boolean result = smsTestAnswerService.submitQuesAnswer(smsTestAnswer);
        if(result){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("提交测验")
    @RequestMapping(value = "/submitTest/{memberTestId}", method = RequestMethod.POST)
    public CommonResult submitTest(@PathVariable Long memberTestId){

        MemberTestVO result = smsMemberTestService.submitTest(memberTestId);
        if(result!= null){
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @ApiOperation("创建测验")
    @RequestMapping(value = "/makeMemberTest", method = RequestMethod.POST)
    public CommonResult makeMemberTest(@RequestHeader String token,@RequestParam Long type){

        Long memberTestId  = smsMemberTestService.makeMemberTest(type,token);
        return CommonResult.success(memberTestId);
    }



}
