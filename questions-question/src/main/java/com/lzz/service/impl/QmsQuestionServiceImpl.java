package com.lzz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzz.dto.QuestionAnswerVO;
import com.lzz.mapper.QmsAnswerMapper;
import com.lzz.mapper.QmsQuestionMapper;
import com.lzz.model.QmsAnswer;
import com.lzz.model.QmsQuestion;
import com.lzz.service.QmsAnswerService;
import com.lzz.service.QmsQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lzz
 * @since 2020-09-27
 */
@Service
public class QmsQuestionServiceImpl extends ServiceImpl<QmsQuestionMapper, QmsQuestion> implements QmsQuestionService {

    @Autowired
    private QmsQuestionMapper qmsQuestionMapper;

    @Autowired
    private QmsAnswerService qmsAnswerService;

    @Override
    public boolean create(QuestionAnswerVO questionAnswerVO) {
        QmsQuestion qmsQuestion = new QmsQuestion();
        BeanUtil.copyProperties(questionAnswerVO,qmsQuestion);
        int quesId = qmsQuestionMapper.insert(qmsQuestion);
        if(CollUtil.isNotEmpty(questionAnswerVO.getAnswerList())){
            for (QmsAnswer qmsAnswer : questionAnswerVO.getAnswerList()){
                qmsAnswer.setQuestionId(qmsQuestion.getId());
                qmsAnswerService.save(qmsAnswer);
            }
        }
        return quesId == 1;
    }

    @Override
    public boolean update( QuestionAnswerVO questionAnswerVO) {
        QmsQuestion qmsQuestion = new QmsQuestion();
        BeanUtil.copyProperties(questionAnswerVO,qmsQuestion);
        qmsQuestion.setId(questionAnswerVO.getQuesId());
        qmsQuestion.setUpdateTime(new Date());
        if(CollUtil.isNotEmpty(questionAnswerVO.getAnswerList())){
            for (QmsAnswer qmsAnswer : questionAnswerVO.getAnswerList()){
                if(qmsAnswer.getId()==null){
                    qmsAnswer.setQuestionId(questionAnswerVO.getQuesId());
                    qmsAnswerService.save(qmsAnswer);
                }else {
                    qmsAnswerService.updateById(qmsAnswer);
                }
            }
        }
        return updateById(qmsQuestion);
    }

    @Override
    public QmsQuestion getQuestionById(Long questionId) {
        return qmsQuestionMapper.selectById(questionId);
    }

    @Override
    public Page<QmsQuestion> list(String title, Long type, Integer pageSize, Integer pageNum) {

        Page<QmsQuestion> page = new Page<>(pageNum, pageSize);
        QueryWrapper<QmsQuestion> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<QmsQuestion> lambda = wrapper.lambda();
        if (!StrUtil.hasEmpty(title)) {
            lambda.like(QmsQuestion::getTitle, title);
        }
        if (type > 0) {
            lambda.eq(QmsQuestion::getType, type);
        }
        lambda.eq(QmsQuestion::getDelFlag, 0);
        lambda.orderByAsc(QmsQuestion::getDisplayOrder);
        return page(page, wrapper);
    }

    @Override
    public List<QmsAnswer> getAnswerList(Long id) {

        QueryWrapper<QmsAnswer> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<QmsAnswer> lambda = queryWrapper.lambda();
        lambda.eq(QmsAnswer::getQuestionId, id);
        return qmsAnswerService.list(queryWrapper);

    }
    @Override
    public QuestionAnswerVO getQuestionAnswerVO(Long id){

        QmsQuestion qmsQuestion = getQuestionById(id);
        QuestionAnswerVO questionAnswerVO = new QuestionAnswerVO();
        BeanUtil.copyProperties(qmsQuestion, questionAnswerVO);
        List<QmsAnswer> answerList = getAnswerList(qmsQuestion.getId());
        questionAnswerVO.setQuesId(qmsQuestion.getId());
        questionAnswerVO.setAnswerList(answerList);
        return questionAnswerVO;
    }

    @Override
    public List<QuestionAnswerVO> getQuestionsByIds(List<Long> ids) {

        List<QmsQuestion> questions = qmsQuestionMapper.getQuestionsByIds(ids);
        List<QuestionAnswerVO> voList = new ArrayList<>(questions.size());
        for (QmsQuestion question : questions) {
            QuestionAnswerVO questionAnswerVO = new QuestionAnswerVO();
            BeanUtil.copyProperties(question, questionAnswerVO);
            List<QmsAnswer> answerList = getAnswerList(question.getId());
            questionAnswerVO.setQuesId(question.getId());
            questionAnswerVO.setAnswerList(answerList);
            voList.add(questionAnswerVO);
        }
        return voList;
    }



}
