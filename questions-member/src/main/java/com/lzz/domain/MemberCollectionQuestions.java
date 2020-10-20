package com.lzz.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/20 15:36
 */
@Document
@Data
public class MemberCollectionQuestions {

    @Id
    private String id;
    @Indexed
    private String tag;
    @Indexed
    private long memberId;
    @Indexed
    private Long quesId;
    private String quesName;
    private Date createTime;
}
