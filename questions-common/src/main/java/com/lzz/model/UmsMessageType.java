package com.lzz.model;

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
 * @author lzz
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_message_type")
@ApiModel(value="UmsMessageType对象", description="")
public class UmsMessageType implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
