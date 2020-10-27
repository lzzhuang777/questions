package com.lzz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzz.model.UmsMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */
@Mapper
public interface UmsMemberMapper extends BaseMapper<UmsMember> {

    int addIntegration (@Param("integration") Integer integration ,@Param("memberId") Long memberId);

}
