package com.lzz.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.TreeMap;

@Data
public class SignResultVO {

    private TreeMap<Integer, Integer> signMap;
    @ApiModelProperty(value = "今天是否签到（1：签到，0：未签到）")
    private Integer signFlag;
    @ApiModelProperty(value = "月签到次数")
    private Integer signCount;

}
