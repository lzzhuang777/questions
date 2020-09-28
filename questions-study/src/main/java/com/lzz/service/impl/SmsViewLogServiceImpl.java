package com.lzz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzz.mapper.SmsViewLogMapper;
import com.lzz.model.SmsViewLog;
import com.lzz.service.SmsViewLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */
@Service
public class SmsViewLogServiceImpl extends ServiceImpl<SmsViewLogMapper, SmsViewLog> implements SmsViewLogService {

    @Autowired
    private SmsViewLogMapper smsViewLogMapper;

    public Page<SmsViewLog> getList(Long memberId,Integer pageSize,Integer pageNum){

        Page<SmsViewLog> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsViewLog> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SmsViewLog::getMemberId,memberId)
        .orderByDesc(SmsViewLog::getCreateTime);
        return page(page,wrapper);
    }


}
