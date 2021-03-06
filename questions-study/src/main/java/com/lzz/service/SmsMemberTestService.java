package com.lzz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzz.dto.MemberTestVO;
import com.lzz.dto.QuestionAnswerVO;
import com.lzz.model.SmsMemberTest;
import org.springframework.transaction.annotation.Transactional;

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

    Page<QuestionAnswerVO> getTestQuesList (Long testId, Integer pageNum);

    MemberTestVO submitTest(Long memberTestId);

    @Transactional
    Long makeMemberTest(Long type,String token);

    Long getMemberId (String token);

}
