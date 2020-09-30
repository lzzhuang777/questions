package com.lzz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzz.mapper.QmsTypeMapper;
import com.lzz.model.QmsType;
import com.lzz.service.QmsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzz
 * @since 2020-09-27
 */
@Service
public class QmsTypeServiceImpl extends ServiceImpl<QmsTypeMapper, QmsType> implements QmsTypeService {

    @Autowired
    private QmsTypeMapper qmsTypeMapper;

    @Override
    public boolean create(QmsType qmsType) {

        return save(qmsType);
    }

    @Override
    public boolean update(Long id, QmsType qmsType) {
        qmsType.setId(id);
        return updateById(qmsType);
    }

    @Override
    public Page<QmsType> list(String type,  Integer pageSize, Integer pageNum) {

        Page<QmsType> page = new Page<>(pageNum,pageSize);
        QueryWrapper<QmsType> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<QmsType> lambda = wrapper.lambda();
        if(!StrUtil.hasEmpty(type)){
            lambda.like(QmsType::getType,type);
        }
        lambda.eq(QmsType::getDelFlag,0);
        lambda.orderByAsc(QmsType::getCreateTime);
        return page(page,wrapper);
    }

}
