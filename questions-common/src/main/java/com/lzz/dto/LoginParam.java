package com.lzz.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/16 16:37
 */

@Data
public class LoginParam {

    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "密码")
    private String password;
}
