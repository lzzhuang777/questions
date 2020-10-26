package com.lzz.service;


import com.lzz.domain.SignResultVO;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/26 14:18
 */
public interface UmsSignService {

    boolean signIn(Long memberId);

    SignResultVO selectSignIn(Long memberId, Integer year, Integer month);
}
