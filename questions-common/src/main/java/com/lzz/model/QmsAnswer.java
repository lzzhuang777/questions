package com.lzz.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author macro
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("qms_answer")
@ApiModel(value="QmsAnswer对象", description="")
public class QmsAnswer implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "自增 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "答案")
    private String answer;

    @ApiModelProperty(value = "答案类型 0：选择题  1：问答题")
    private Integer type;

    @ApiModelProperty(value = "选择题  A B C D")
    private String answerHead;

    @ApiModelProperty(value = "题目id")
    private Long questionId;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
