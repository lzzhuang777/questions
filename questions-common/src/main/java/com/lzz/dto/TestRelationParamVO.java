package com.lzz.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/16 13:31
 */

@Data
public class TestRelationParamVO {

    @ApiModelProperty(value = "测验id")
    private Long TestId;
    @ApiModelProperty(value = "题目id")
    private Long quesId;
}
