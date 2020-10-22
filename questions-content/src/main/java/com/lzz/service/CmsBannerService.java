package com.lzz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzz.api.CommonPage;
import com.lzz.model.CmsBanner;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */
public interface CmsBannerService extends IService<CmsBanner> {


    Page<CmsBanner> list (String title,Integer pageSize,Integer pageNum);

}
