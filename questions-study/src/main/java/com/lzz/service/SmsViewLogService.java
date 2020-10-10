package com.lzz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzz.dto.FailQuestionVO;
import com.lzz.dto.QuestionAnswerVO;
import com.lzz.model.QmsQuestion;
import com.lzz.model.SmsViewLog;

import java.util.List;

/**
 * @author lzz
 * @since 2020-09-27
 */
public interface SmsViewLogService extends IService<SmsViewLog> {

     Page<SmsViewLog> getList(Long memberId, Integer pageSize, Integer pageNum);

     List<FailQuestionVO> getFailQuestions(Long memberId);

     List<QuestionAnswerVO> getQuestions(Long memberId, Long quesType);
}
