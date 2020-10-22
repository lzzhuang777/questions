package com.lzz.repository;

import com.lzz.domain.MemberCollectionQuestions;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/20 15:33
 */
public interface MemberCollectionQuestionsRepository extends MongoRepository<MemberCollectionQuestions, String> {

    List<MemberCollectionQuestions> findByMemberIdOrderByCreateTimeDesc(Long memberId);

    void deleteByQuesIdAndMemberId(Long quesId, Long memberId);

    MemberCollectionQuestions findByMemberIdAndQuesId(Long memberId,Long quesId);
}
