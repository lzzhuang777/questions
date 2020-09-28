package com.lzz.service;

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

    QmsQuestion getQuestionById( Long questionId);

}
