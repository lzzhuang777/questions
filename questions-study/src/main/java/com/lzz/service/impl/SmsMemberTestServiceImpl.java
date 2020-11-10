package com.lzz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzz.api.CommonResult;
import com.lzz.dto.QuestionAnswerVO;
import com.lzz.feign.QuestionFeginService;
import com.lzz.feign.TestFeignService;
import com.lzz.mapper.SmsMemberTestMapper;
import com.lzz.model.QmsQuestion;
import com.lzz.model.QmsTest;
import com.lzz.model.SmsMemberTest;
import com.lzz.model.SmsTestAnswer;
import com.lzz.service.SmsMemberTestService;
import com.lzz.service.SmsTestAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
    public Page<QuestionAnswerVO> getTestQuesList(Long memberTestId, Integer pageNum) {

        SmsMemberTest smsMemberTest = smsMemberTestMapper.selectById(memberTestId);
        List<Long> ids = testFeignService.selectIdsByTestId(smsMemberTest.getTestId());
        if (CollUtil.isEmpty(ids)) {
            return null;
        }
        QuestionAnswerVO vo = questionFeginService.getQuestionAnswerVOById(ids.get(pageNum - 1));
        vo.setTestName(smsMemberTest.getTestName());
        SmsTestAnswer smsTestAnswer = smsTestAnswerService.getTestAnswer(memberTestId,ids.get(pageNum - 1));
        if(smsTestAnswer!=null) {
            vo.setMemberAnswer(smsTestAnswer.getAnswer());
        }
        Page<QuestionAnswerVO> page = new Page<>(pageNum,1);
        page.setTotal(ids.size());
        page.setRecords(Arrays.asList(vo));
        return page;
    }

    @Override
    public SmsMemberTest submitTest(Long memberTestId) {
        int score = 0;
        SmsMemberTest smsMemberTest = smsMemberTestMapper.selectById(memberTestId);
        smsMemberTest.setCompleteTime(new Date());
        smsMemberTest.setIsComplete(Boolean.TRUE);
        List<SmsTestAnswer> answerList = smsTestAnswerService.getTestAnswer(memberTestId);
        if (CollUtil.isNotEmpty(answerList)) {
            for (SmsTestAnswer smsTestAnswer : answerList) {
                CommonResult<QmsQuestion> commonResult = testFeignService.getQuestionById(smsTestAnswer.getQuesId());
                if (commonResult.getData().getAnswer().equals(smsTestAnswer.getAnswer())) {
                    smsTestAnswer.setIsCorrect(Boolean.TRUE);
                    smsTestAnswerService.updateById(smsTestAnswer);
                    score += 10;
                }
            }
        }
        smsMemberTest.setScore(score);
        updateById(smsMemberTest);
        return smsMemberTest;
    }

    public Long makeMemberTest(Long type, long memberId) {
        List<QmsTest> qmsTests = testFeignService.listAll(type).getData();
        QueryWrapper<SmsMemberTest> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsMemberTest> lamba = wrapper.lambda();
        lamba.eq(SmsMemberTest::getType, type);
        List<SmsMemberTest> list = list(wrapper);
        SmsMemberTest smsMemberTest = new SmsMemberTest();
        if (CollUtil.isEmpty(list)) {
            smsMemberTest.setTestId(qmsTests.get(0).getId());
            smsMemberTest.setTestName(qmsTests.get(0).getTestName());
        } else {
            smsMemberTest.setTestId(qmsTests.get(list.size()).getId());
            smsMemberTest.setTestName(qmsTests.get(list.size()).getTestName());
        }
        smsMemberTest.setType(type);
        smsMemberTest.setMemberId(memberId);
        smsMemberTest.setStartTime(new Date());
        smsMemberTestMapper.insert(smsMemberTest);
        return smsMemberTest.getId();
    }


}
