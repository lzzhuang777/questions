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
@TableName("ums_member")
@ApiModel(value="UmsMember对象", description="")
public class UmsMember implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "小程序openid")
    private Integer miniOpenid;

    @ApiModelProperty(value = "服务号openid")
    private String mpOpenid;

    @ApiModelProperty(value = "微信unionid")
    private String unionid;

    @ApiModelProperty(value = "会员等级id")
    private Long levelId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    @TableField("PASSWORD")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "生日")
    private Date birth;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "用户来源")
    private Integer sourceType;

    @ApiModelProperty(value = "积分")
    private Integer integration;

    @ApiModelProperty(value = "注册时间")
    private Date registerTime;

    @ApiModelProperty(value = "删除标记（0-正常，1-删除）")
    private Boolean delFlag;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
