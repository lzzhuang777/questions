package com.lzz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzz.model.QmsTestQuestionRelations;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lzz
 * @since 2020-10-12
 */
@Mapper
public interface QmsTestQuestionRelationsMapper extends BaseMapper<QmsTestQuestionRelations> {

    List<Long> selectIdsByTestId(@Param("testId") Long testId);

    //int addTestQuestions(@Param("questIds") List<Long> quesIds,@Param("testId")Long testId);

}
