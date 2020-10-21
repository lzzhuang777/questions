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

    List<Document> list (Long memberId);

    void deleteByQuesIdAndMemberId(Long memberId,Long quesId);

    void create(Long memberId,MemberCollectionQuestions memberCollectionQuestions);
}
