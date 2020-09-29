package com.lzz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzz.model.QmsQuestion;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */
public interface QmsQuestionService extends IService<QmsQuestion> {

    boolean create(QmsQuestion qmsQuestion);

    boolean update(Long id, QmsQuestion qmsQuestion);

    QmsQuestion getQuestionById( Long questionId);

    Page<QmsQuestion> list (String title, Long type,Integer pageSize,Integer pageNum);
}
