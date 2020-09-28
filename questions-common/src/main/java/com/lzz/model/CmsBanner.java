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
@TableName("cms_banner")
@ApiModel(value="CmsBanner对象", description="")
public class CmsBanner implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "图片路径")
    private String imgUrl;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "排序")
    private Integer displayOrder;

    @ApiModelProperty(value = "是否显示")
    @TableField("ENABLE")
    private Integer enable;

    @ApiModelProperty(value = "跳转类型")
    private Integer renderType;

    @ApiModelProperty(value = "跳转路径")
    private String renderUrl;

    @ApiModelProperty(value = "删除标记（0-正常，1-删除）")
    private Boolean delFlag;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
