package com.lzz.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("qms_question")
@ApiModel(value="QmsQuestion对象", description="")
public class QmsQuestion implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "题目标题")
    private String title;

    @ApiModelProperty(value = "题目解答解析")
    private String answer;

    @ApiModelProperty(value = "正确答案id")
    private Long answerId;

    @ApiModelProperty(value = "题目难度等级")
    @TableField("LEVEL")
    private Integer level;

    @ApiModelProperty(value = "排序")
    private Integer displayOrder;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "题目类型")
    @TableField("TYPE")
    private Long type;

    @ApiModelProperty(value = "是否显示")
    @TableField("ENABLE")
    private Integer enable;

    @ApiModelProperty(value = "删除标记（0-正常，1-删除）")
    private Boolean delFlag;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
