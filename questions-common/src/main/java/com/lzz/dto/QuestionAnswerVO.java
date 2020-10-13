package com.lzz.dto;

import com.lzz.model.QmsAnswer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/10 11:10
 */
@Data
public class QuestionAnswerVO {

    @ApiModelProperty(value = "题目id")
    private Long quesId;

    @ApiModelProperty(value = "题目标题")
    private String title;

    @ApiModelProperty(value = "正确答案")
    private String answer;

    @ApiModelProperty(value = "用户选择答案")
    private String memberAnswer;

    @ApiModelProperty(value = "题目难度等级")
    private Integer level;

    @ApiModelProperty(value = "排序")
    private Integer displayOrder;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "题目类型")
    private Long type;

    @ApiModelProperty(value = "题目答案集合")
    private List<QmsAnswer> answerList;


}
