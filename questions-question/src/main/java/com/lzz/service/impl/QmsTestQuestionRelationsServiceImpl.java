package com.lzz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzz.dto.TestRelationParamVO;
import com.lzz.mapper.QmsTestQuestionRelationsMapper;
import com.lzz.model.QmsTestQuestionRelations;
import com.lzz.service.QmsTestQuestionRelationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzz
 * @since 2020-10-12
 */
@Service
public class QmsTestQuestionRelationsServiceImpl extends ServiceImpl<QmsTestQuestionRelationsMapper, QmsTestQuestionRelations> implements QmsTestQuestionRelationsService {

    @Autowired
    private QmsTestQuestionRelationsMapper testQuestionRelationsMapper;

    @Override
    public List<Long> selectIdsByTestId (Long testId){

       return  testQuestionRelationsMapper.selectIdsByTestId(testId);
       
    }

    @Override
    public int delTestQuestions(TestRelationParamVO relationParamVO) {
        QueryWrapper<QmsTestQuestionRelations> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(QmsTestQuestionRelations ::getQuesId,relationParamVO.getQuesId())
                .eq(QmsTestQuestionRelations ::getTestId,relationParamVO.getTestId());
        return testQuestionRelationsMapper.delete(wrapper);

    }
}
