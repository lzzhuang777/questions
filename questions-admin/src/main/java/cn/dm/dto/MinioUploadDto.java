package cn.dm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * created  by lzz on 2020/1/7
 */
@Setter
@Getter
public class MinioUploadDto {
    @ApiModelProperty("图片名称")
    private String name;
    @ApiModelProperty("图片路径")
    private String url;
}
