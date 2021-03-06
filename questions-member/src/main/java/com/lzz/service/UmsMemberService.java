package com.lzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzz.dto.LoginParam;
import com.lzz.model.UmsMember;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */
public interface UmsMemberService extends IService<UmsMember> {

    String login(LoginParam loginParam);

    UmsMember loadCurrentUserByTokenAsJson(String token);

    int addIntegration(Integer integration,Long memberId);

}
