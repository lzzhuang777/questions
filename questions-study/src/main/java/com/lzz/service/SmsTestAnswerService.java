package com.lzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzz.model.SmsTestAnswer;

import java.util.List;

/**
 * @author lzz
 * @since 2020-10-12
 */

public interface SmsTestAnswerService extends IService<SmsTestAnswer> {

    List<SmsTestAnswer> getTestAnswer (Long testId);

    SmsTestAnswer getTestAnswer( Long testId,Long quesId);

    boolean submitQuesAnswer(SmsTestAnswer smsTestAnswer);

}
