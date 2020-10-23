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
@TableName("ums_message_tamplate")
@ApiModel(value="UmsMessageTamplate对象", description="")
public class UmsMessageTamplate implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    @ApiModelProperty(value = "模板内容")
    private String templateContent;

    @ApiModelProperty(value = "重定向链接接")
    private String redirectUrl;

    @ApiModelProperty(value = "链接标题")
    private String urlTitle;

    @ApiModelProperty(value = "消息类型 id")
    private Integer typeId;

    @ApiModelProperty(value = "模板参数用，隔开")
    private String params;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
