package com.lzz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzz.api.CommonResult;
import com.lzz.dto.QuestionAnswerVO;
import com.lzz.feign.QuestionFeginService;
import com.lzz.feign.TestFeignService;
import com.lzz.mapper.SmsMemberTestMapper;
import com.lzz.model.QmsQuestion;
import com.lzz.model.SmsMemberTest;
import com.lzz.model.SmsTestAnswer;
import com.lzz.service.SmsMemberTestService;
import com.lzz.service.SmsTestAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lzz
 * @since 2020-10-12
 */

@Service
public class SmsMemberTestServiceImpl extends ServiceImpl<SmsMemberTestMapper, SmsMemberTest> implements SmsMemberTestService {

    @Autowired
    private SmsMemberTestMapper smsMemberTestMapper;
    @Autowired
    private TestFeignService testFeignService;
    @Autowired
    private QuestionFeginService questionFeginService;
    @Autowired
    private SmsTestAnswerService smsTestAnswerService;

    @Override
    public List<SmsMemberTest> getTestByMemberId(Long memberId) {
        QueryWrapper<SmsMemberTest> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsMemberTest> lamba = wrapper.lambda();
        lamba.eq(SmsMemberTest::getMemberId, memberId);
        return smsMemberTestMapper.selectList(wrapper);
    }

    @Override
    public List<QuestionAnswerVO> getTestQuesList(Long memberTestId) {
        SmsMemberTest smsMemberTest = smsMemberTestMapper.selectById(memberTestId);
        List<Long> ids = testFeignService.selectIdsByTestId(smsMemberTest.getTestId());
        if (CollUtil.isEmpty(ids)) {
            return null;
        }
        List<QuestionAnswerVO> voList = questionFeginService.getQuestionsByIds(ids);
        List<SmsTestAnswer> testAnswerList = smsTestAnswerService.getTestAnswer(memberTestId);
        for (QuestionAnswerVO questionAnswerVO : voList) {
            for (SmsTestAnswer smsTestAnswer : testAnswerList) {
                if (questionAnswerVO.getQuesId().equals(smsTestAnswer.getQuesId())
                        && smsTestAnswer.getAnswer() != null) {
                    questionAnswerVO.setMemberAnswer(smsTestAnswer.getAnswer());
                }
            }
        }
        return voList;
    }

    @Override
    public SmsMemberTest submitTest(Long memberTestId) {
        int score = 0;
        SmsMemberTest smsMemberTest = smsMemberTestMapper.selectById(memberTestId);
        smsMemberTest.setCompleteTime(new Date());
        smsMemberTest.setIsComplete(true);
        List<SmsTestAnswer> answerList = smsTestAnswerService.getTestAnswer(memberTestId);
        if(CollUtil.isNotEmpty(answerList)) {
            for (SmsTestAnswer smsTestAnswer : answerList) {
                CommonResult<QmsQuestion> commonResult = testFeignService.getQuestionById(smsTestAnswer.getQuesId());
                if (commonResult.getData().getAnswer().equals(smsTestAnswer.getAnswer())) {
                    smsTestAnswer.setIsCorrect(true);
                    smsTestAnswerService.updateById(smsTestAnswer);
                    score += 10;
                }
            }
        }
        smsMemberTest.setScore(score);
        updateById(smsMemberTest);
        return smsMemberTest;
    }


}
