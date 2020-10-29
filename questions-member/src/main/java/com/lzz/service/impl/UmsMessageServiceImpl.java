package com.lzz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzz.mapper.UmsMessageMapper;
import com.lzz.model.UmsMessage;
import com.lzz.service.UmsMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzz
 * @since 2020-10-23
 */
@Service
public class UmsMessageServiceImpl extends ServiceImpl<UmsMessageMapper, UmsMessage> implements UmsMessageService {

    @Override
    public List<UmsMessage> selectList(long memberId) {
        QueryWrapper<UmsMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsMessage ::getReceiveId,memberId)
                .eq(UmsMessage ::getStatus,Boolean.FALSE);
        return this.list(queryWrapper);
    }
}
