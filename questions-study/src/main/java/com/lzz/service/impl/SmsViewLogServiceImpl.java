package com.lzz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzz.dto.FailQuestionVO;
import com.lzz.dto.QuestionAnswerVO;
import com.lzz.feign.QuestionFeginService;
import com.lzz.mapper.SmsViewLogMapper;
import com.lzz.model.QmsQuestion;
import com.lzz.model.SmsViewLog;
import com.lzz.service.SmsViewLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzz
 * @since 2020-09-27
 */
@Service
public class SmsViewLogServiceImpl extends ServiceImpl<SmsViewLogMapper, SmsViewLog> implements SmsViewLogService {

    @Autowired
    private SmsViewLogMapper smsViewLogMapper;
    @Autowired
    private QuestionFeginService questionFeginService;

    public Page<SmsViewLog> getList(Long memberId,Integer pageSize,Integer pageNum){

        Page<SmsViewLog> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsViewLog> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SmsViewLog::getMemberId,memberId)
        .orderByDesc(SmsViewLog::getCreateTime);
        return page(page,wrapper);
    }

    @Override
    public List<FailQuestionVO> getFailQuestions(Long memberId) {
        return smsViewLogMapper.selectFailQuestions(memberId);
    }

    @Override
    public List<QuestionAnswerVO> getQuestions(Long memberId, Long quesType) {

        List<Long> ids = smsViewLogMapper.selectQuesIds(memberId, quesType);
        if(CollUtil.isEmpty(ids)){
            return null;
        }
        return questionFeginService.getQuestionsByIds(ids);
    }
}
