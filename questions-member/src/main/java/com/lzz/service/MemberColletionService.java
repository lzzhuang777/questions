package com.lzz.service;

import com.lzz.domain.MemberCollectionQuestions;
import org.bson.Document;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/20 15:56
 */
public interface MemberColletionService {

    List<Document> list (String token);

    void deleteByQuesIdAndMemberId(String token,Long quesId);

    void create(String token,MemberCollectionQuestions memberCollectionQuestions);

    boolean isCollection(String token,Long quesId);
}
