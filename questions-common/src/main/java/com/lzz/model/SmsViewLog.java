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
 * @author lzz
 * @since 2020-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_view_log")
@ApiModel(value="SmsViewLog对象", description="")
public class SmsViewLog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "题目id")
    private Long quesId;

    @ApiModelProperty(value = "题目类型id")
    private Long quesType;

    @ApiModelProperty(value = "用户id")
    private Long memberId;

    @ApiModelProperty(value = "用户做题答案")
    private String answer;

    @ApiModelProperty(value = "正确答案")
    private String correctAnswer;

    @ApiModelProperty(value = "删除标记（0-正常，1-删除）")
    private Boolean delFlag;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
