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
 * 用户测验试题答案表
 * </p>
 *
 * @author lzz
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_test_answer")
@ApiModel(value="SmsTestAnswer对象", description="用户测验试题答案表")
public class SmsTestAnswer implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户测验 SmsMemberTest id")//SmsMemberTest
    private Long testId;

    @ApiModelProperty(value = "题目 id")
    private Long quesId;

    @ApiModelProperty(value = "答案是否正确（0：错误，1正确）")
    private Boolean isCorrect;

    @ApiModelProperty(value = "答案")
    private Long answer;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
