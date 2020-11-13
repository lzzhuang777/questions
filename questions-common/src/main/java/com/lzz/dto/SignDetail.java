package com.lzz.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/11/13 14:36
 */
@Data
public class SignDetail implements Comparable<SignDetail> {

    @ApiModelProperty(value = "每月第几天")
    private Integer day;
    @ApiModelProperty(value = "是否签到:（0：未签到；1：已签到）")
    private Integer isSign;

    @Override
    public int compareTo(SignDetail o) {
        return this.getDay() - o.getDay();//按照天排序
    }

}
