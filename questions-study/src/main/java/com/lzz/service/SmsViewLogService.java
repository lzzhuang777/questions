package com.lzz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzz.model.SmsViewLog;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */
public interface SmsViewLogService extends IService<SmsViewLog> {

     Page<SmsViewLog> getList(Long memberId, Integer pageSize, Integer pageNum);
}
