package com.lzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzz.dto.TestRelationParamVO;
import com.lzz.model.QmsTestQuestionRelations;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzz
 * @since 2020-10-12
 */
public interface QmsTestQuestionRelationsService extends IService<QmsTestQuestionRelations> {

    List<Long> selectIdsByTestId (Long testId);

    int delTestQuestions(TestRelationParamVO testRelationParamVO);
}
