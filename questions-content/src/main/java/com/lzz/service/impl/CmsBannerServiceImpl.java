package com.lzz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzz.mapper.CmsBannerMapper;
import com.lzz.model.CmsBanner;
import com.lzz.service.CmsBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */
@Service
public class CmsBannerServiceImpl extends ServiceImpl<CmsBannerMapper, CmsBanner> implements CmsBannerService {

    @Autowired
    private CmsBannerMapper cmsBannerMapper;

    @Override
    public Page<CmsBanner> list(String title, Integer pageSize, Integer pageNum) {
        Page<CmsBanner> page = new Page<>(pageNum, pageSize);
        QueryWrapper<CmsBanner> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<CmsBanner> lambda = wrapper.lambda();
        if (!StrUtil.hasEmpty(title)) {
            lambda.like(CmsBanner::getTitle, title);
        }
        lambda.eq(CmsBanner::getDelFlag, 0);
        lambda.orderByAsc(CmsBanner::getDisplayOrder);
        return page(page, wrapper);
    }

}
