package com.lzz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzz.dto.QuestionAnswerVO;
import com.lzz.model.QmsAnswer;
import com.lzz.model.QmsQuestion;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzz
 * @since 2020-09-27
 */
public interface QmsQuestionService extends IService<QmsQuestion> {

    @Transactional
    boolean create(QuestionAnswerVO questionAnswerVO);

    boolean update( QuestionAnswerVO questionAnswerVO);

    QmsQuestion getQuestionById( Long questionId);

    Page<QmsQuestion> list (String title, Long type,Integer pageSize,Integer pageNum);

    List<QmsAnswer> getAnswerList(Long id);

    List<QuestionAnswerVO> getQuestionsByIds(List<Long> ids);

    QuestionAnswerVO getQuestionAnswerVO(Long id);

    List<QmsQuestion> selectQuesList(String query,Long testId);

    List<QmsQuestion> selectQuestionsByTestId (Long testId);

    QuestionAnswerVO getQuestionAnswerVOById(long quesId);
}
