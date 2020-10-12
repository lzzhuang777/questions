package com.lzz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzz.mapper.SmsTestAnswerMapper;
import com.lzz.model.SmsTestAnswer;
import com.lzz.service.SmsTestAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzz
 * @since 2020-10-12
 */

@Service
public class SmsTestAnswerServiceImpl extends ServiceImpl<SmsTestAnswerMapper, SmsTestAnswer> implements SmsTestAnswerService {

    @Autowired
    private SmsTestAnswerMapper smsTestAnswerMapper;

    @Override
    public List<SmsTestAnswer> getTestAnswer( Long testId) {
        QueryWrapper<SmsTestAnswer> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsTestAnswer> lamba = wrapper.lambda();
        lamba.eq(SmsTestAnswer::getTestId,testId);
        return list(wrapper);
    }
}
