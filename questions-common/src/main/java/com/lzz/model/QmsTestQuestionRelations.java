package com.lzz.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lzz
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("qms_test_question_relations")
@ApiModel(value="QmsTestQuestionRelations对象", description="")
public class QmsTestQuestionRelations implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "测验id")
    private Long testId;

    @ApiModelProperty(value = "试题id")
    private Long quesId;


}
