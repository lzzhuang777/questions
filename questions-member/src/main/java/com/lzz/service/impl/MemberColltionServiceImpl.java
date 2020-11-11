package com.lzz.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzz.domain.CollectionResultVO;
import com.lzz.domain.MemberCollectionQuestions;
import com.lzz.exception.Asserts;
import com.lzz.model.UmsMember;
import com.lzz.repository.MemberCollectionQuestionsRepository;
import com.lzz.service.MemberColletionService;
import com.lzz.service.UmsMemberService;
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
    @Autowired
    private UmsMemberService umsMemberService;

    @Override
    public List<Document> list(String token) {
        UmsMember umsMember = umsMemberService.loadCurrentUserByTokenAsJson(token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("quesName","$quesName");
        jsonObject.put("quesId","$quesId");
        MatchOperation matchOperation = Aggregation.match(new Criteria("memberId").is(umsMember.getId()));
        GroupOperation groupOperation = Aggregation.group("tag").addToSet(jsonObject).as("list");
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation);
        AggregationResults<Document> aggregationResults = mongoTemplate.aggregate(aggregation, "memberCollectionQuestions", Document.class);
        return aggregationResults.getMappedResults();
    }

    @Override
    public void deleteByQuesIdAndMemberId(String token,Long quesId){
        UmsMember umsMember = umsMemberService.loadCurrentUserByTokenAsJson(token);
        memberCollectionQuestionsRepository.deleteByQuesIdAndMemberId(quesId,umsMember.getId());
    }

    @Override
    public void create(String token,MemberCollectionQuestions memberCollectionQuestions){
        UmsMember umsMember = umsMemberService.loadCurrentUserByTokenAsJson(token);
        memberCollectionQuestions.setMemberId(umsMember.getId());
        memberCollectionQuestions.setCreateTime(new Date());
        memberCollectionQuestionsRepository.save(memberCollectionQuestions);
    }

    @Override
    public boolean isCollection(String token,Long quesId){
        UmsMember umsMember = umsMemberService.loadCurrentUserByTokenAsJson(token);
        MemberCollectionQuestions m = memberCollectionQuestionsRepository.findByMemberIdAndQuesId(umsMember.getId(), quesId);
        return ObjectUtil.isNotEmpty(m);
    }





}
