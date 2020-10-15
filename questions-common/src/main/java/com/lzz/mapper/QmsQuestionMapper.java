package com.lzz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzz.model.QmsQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */
@Mapper
public interface QmsQuestionMapper extends BaseMapper<QmsQuestion> {

    List<QmsQuestion> getQuestionsByIds(@Param("ids") List<Long> ids);

    List<QmsQuestion> selectQuesList(@Param("query") String query,@Param("testId")Long testId);

    List<QmsQuestion> selectQuestionsByTestId (@Param("testId") Long testId);

}
