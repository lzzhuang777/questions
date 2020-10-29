package com.lzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzz.model.UmsMessage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzz
 * @since 2020-10-23
 */
public interface UmsMessageService extends IService<UmsMessage> {

    List<UmsMessage> selectList(long memberId);
}
