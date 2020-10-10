package com.lzz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzz.dto.FailQuestionVO;
import com.lzz.model.SmsViewLog;
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
public interface SmsViewLogMapper extends BaseMapper<SmsViewLog> {


    List<FailQuestionVO> selectFailQuestions(@Param("memberId")Long memberId);

    List<Long> selectQuesIds(@Param("memberId")Long memberId,@Param("quesType")Long quesType);

}
