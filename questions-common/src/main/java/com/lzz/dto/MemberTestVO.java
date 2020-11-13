package com.lzz.dto;

import com.lzz.model.SmsMemberTest;
import com.lzz.model.SmsTestAnswer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/11/13 11:03
 */
@Data
public class MemberTestVO extends SmsMemberTest {

    @ApiModelProperty(value = "用户答案集合")
    private List<SmsTestAnswer> testAnswers;
}
