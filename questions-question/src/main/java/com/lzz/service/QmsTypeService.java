package com.lzz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzz.model.QmsType;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzz
 * @since 2020-09-27
 */
public interface QmsTypeService extends IService<QmsType> {

    boolean create(QmsType qmsType);

    boolean update(Long id, QmsType qmsType);

    Page<QmsType> list (String type,Integer pageSize, Integer pageNum);

}
