package com.lzz.controller;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.dto.FailQuestionVO;
import com.lzz.dto.QuestionAnswerVO;
import com.lzz.model.QmsQuestion;
import com.lzz.model.SmsViewLog;
import com.lzz.service.SmsViewLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author macro
 * @since 2020-09-27
 */
@Api(tags = "SmsViewLogController", description = "已做试题记录")
@RestController
@RequestMapping("/sms/smsViewLog")
public class SmsViewLogController {

    @Autowired
    private SmsViewLogService smsViewLogService;

    @ApiOperation("查询错题")
    @RequestMapping(value = "/list/{memberId}", method = RequestMethod.GET)
    public CommonResult<CommonPage<SmsViewLog>> list(@PathVariable Long memberId, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<SmsViewLog> page = smsViewLogService.getList(memberId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("查询错题类型和数量")
    @RequestMapping(value = "/getFailQuestions/{memberId}", method = RequestMethod.GET)
    public CommonResult<List<FailQuestionVO>> getFailQuestions(@PathVariable Long memberId) {

        List<FailQuestionVO> list = smsViewLogService.getFailQuestions(memberId);
        if(CollUtil.isEmpty(list)){
            return CommonResult.failed("错误试题集合为空");
        }
        return CommonResult.success(list);
    }

    @ApiOperation("查询 错误试题 集合 ")
    @RequestMapping(value = "/getQuesIds",method = RequestMethod.GET)
    public CommonResult<List<QuestionAnswerVO>> getQuestions(@RequestParam("memberId")Long memberId, @RequestParam("quesType")Long quesType){

        List<QuestionAnswerVO> list = smsViewLogService.getQuestions(memberId, quesType);
        if(CollUtil.isEmpty(list)){
            return CommonResult.failed("查询 错误试题 出错");
        }
        return CommonResult.success(list);
    }

}

