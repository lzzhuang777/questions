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
@TableName("chms_channel")
@ApiModel(value="ChmsChannel对象", description="")
public class ChmsChannel implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "渠道名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "渠道appid")
    private String appid;

    @ApiModelProperty(value = "渠道appsecret")
    private String appsecret;

    @ApiModelProperty(value = "删除标记（0-正常，1-删除）")
    private Boolean delFlag;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
