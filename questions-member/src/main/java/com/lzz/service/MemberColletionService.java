package com.lzz.service;

import com.lzz.domain.MemberCollectionQuestions;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/20 15:56
 */
public interface MemberColletionService {

    List<MemberCollectionQuestions> list (Long memberId);

    void deleteByQuesIdAndMemberId(Long memberId,Long quesId);
}
