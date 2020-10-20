package com.lzz.service.impl;

import com.lzz.domain.MemberCollectionQuestions;
import com.lzz.repository.MemberCollectionQuestionsRepository;
import com.lzz.service.MemberColletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/20 15:56
 */
@Service
public class MemberColltionServiceImpl implements MemberColletionService {

    @Autowired
    private MemberCollectionQuestionsRepository memberCollectionQuestionsRepository;


    @Override
    public List<MemberCollectionQuestions> list(Long memberId) {

        return memberCollectionQuestionsRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }

    public void deleteByQuesIdAndMemberId(Long memberId,Long quesId){

        memberCollectionQuestionsRepository.deleteByQuesIdAndMemberId(quesId,memberId);
    }



}
