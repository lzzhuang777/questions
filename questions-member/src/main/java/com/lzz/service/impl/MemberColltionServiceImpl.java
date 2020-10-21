package com.lzz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lzz.domain.CollectionResultVO;
import com.lzz.domain.MemberCollectionQuestions;
import com.lzz.repository.MemberCollectionQuestionsRepository;
import com.lzz.service.MemberColletionService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Document> list(Long memberId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("quesName","$quesName");
        jsonObject.put("quesId","$quesId");
        MatchOperation matchOperation = Aggregation.match(new Criteria("memberId").is(memberId));
        GroupOperation groupOperation = Aggregation.group("tag").addToSet(jsonObject).as("list");
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation);
        AggregationResults<Document> aggregationResults = mongoTemplate.aggregate(aggregation, "memberCollectionQuestions", Document.class);
        return aggregationResults.getMappedResults();
    }

    @Override
    public void deleteByQuesIdAndMemberId(Long memberId,Long quesId){

        memberCollectionQuestionsRepository.deleteByQuesIdAndMemberId(quesId,memberId);
    }

    @Override
    public void create(Long memberId,MemberCollectionQuestions memberCollectionQuestions){

        memberCollectionQuestions.setMemberId(memberId);
        memberCollectionQuestions.setCreateTime(new Date());
        memberCollectionQuestionsRepository.save(memberCollectionQuestions);
    }





}
