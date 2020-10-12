package com.lzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzz.dto.QuestionAnswerVO;
import com.lzz.model.SmsMemberTest;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzz
 * @since 2020-10-12
 */
public interface SmsMemberTestService extends IService<SmsMemberTest> {


    List<SmsMemberTest> getTestByMemberId(Long memberId);

    List<QuestionAnswerVO> getTestQuesList (Long testId);

}
