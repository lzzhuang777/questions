package com.lzz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzz.mapper.QmsTestMapper;
import com.lzz.model.QmsTest;
import com.lzz.service.QmsTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/12 13:06
 */

@Service
public class QmsTestServiceImpl extends ServiceImpl<QmsTestMapper, QmsTest> implements QmsTestService {

    @Autowired
    private QmsTestMapper qmsTestMapper;

    @Override
    public boolean create(QmsTest qmsTest) {
        return save(qmsTest);
    }

    @Override
    public Page<QmsTest> list(String testName, Integer pageSize, Integer pageNum) {

        Page<QmsTest> page = new Page<>(pageNum, pageSize);
        QueryWrapper<QmsTest> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<QmsTest> lambda = wrapper.lambda();
        if (!StrUtil.hasEmpty(testName)) {
            lambda.like(QmsTest::getTestName, testName);
        }
        return page(page, wrapper);
    }
}
