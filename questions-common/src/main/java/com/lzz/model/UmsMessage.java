package com.lzz.model;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("ums_message")
@ApiModel(value="UmsMessage对象", description="")
public class UmsMessage implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "通知内容")
    private String content;

    @ApiModelProperty(value = "接收用户 id")
    private Long receiveId;

    @ApiModelProperty(value = "发送用户 id (0为系统消息)")
    private Long sendId;

    @ApiModelProperty(value = "消息类型（0：系统消息，1：任务消息）")
    private Integer type;

    @ApiModelProperty(value = "（0：未读，1：已读）")
    private Boolean status;

    @ApiModelProperty(value = "重定向链接接")
    private String redirectUrl;

    @ApiModelProperty(value = "链接标题")
    private String urlTitle;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private Date createTime;


}
