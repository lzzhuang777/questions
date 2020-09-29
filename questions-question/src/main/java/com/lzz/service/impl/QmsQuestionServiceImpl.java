package com.lzz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzz.mapper.QmsQuestionMapper;
import com.lzz.model.QmsQuestion;
import com.lzz.service.QmsQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */
@Service
public class QmsQuestionServiceImpl extends ServiceImpl<QmsQuestionMapper, QmsQuestion> implements QmsQuestionService {

    @Autowired
    private QmsQuestionMapper qmsQuestionMapper;


    @Override
    public boolean create(QmsQuestion qmsQuestion) {
        return  save(qmsQuestion);
    }

    @Override
    public boolean update(Long id, QmsQuestion qmsQuestion) {
        qmsQuestion.setId(id);
        return updateById(qmsQuestion);
    }

    @Override
    public QmsQuestion getQuestionById(Long questionId) {
        return qmsQuestionMapper.selectById(questionId);
    }

    @Override
    public Page<QmsQuestion> list(String title, Long type, Integer pageSize, Integer pageNum) {

        Page<QmsQuestion> page = new Page<>(pageNum,pageSize);
        QueryWrapper<QmsQuestion> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<QmsQuestion> lambda = wrapper.lambda();
        if(!StrUtil.hasEmpty(title)){
           lambda.like(QmsQuestion::getTitle,title);
        }
        if(type>0){
            lambda.eq(QmsQuestion ::getType,type);
        }
        lambda.eq(QmsQuestion::getDelFlag,0);
        lambda.orderByDesc(QmsQuestion::getDisplayOrder);
        return page(page,wrapper);
    }


}
