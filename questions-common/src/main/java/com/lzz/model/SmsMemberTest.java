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
 *
 * @author lzz
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_member_test")
@ApiModel(value="SmsMemberTest对象", description="")
public class SmsMemberTest implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long memberId;

    @ApiModelProperty(value = "测验id")
    private Long testId;

    @ApiModelProperty(value = "测验名称")
    private String testName;

    @ApiModelProperty(value = "测验类型")
    private Long type;

    @ApiModelProperty(value = "是否完成（0：未完成，1已完成）")
    private Boolean isComplete;

    @ApiModelProperty(value = "得分")
    private Integer score;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "完成时间")
    private Date completeTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
