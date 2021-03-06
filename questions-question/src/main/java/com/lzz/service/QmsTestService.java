package com.lzz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzz.model.QmsTest;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/12 13:05
 */
public interface QmsTestService extends IService<QmsTest> {

    boolean create(QmsTest qmsTest);

    Page<QmsTest> list (String testName,Integer pageSize, Integer pageNum);

    boolean addTestQuestions(List<Long> quesIds, Long testId);

    List<QmsTest> listAll(Long type);

}
