package com.lzz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.model.CmsBanner;
import com.lzz.service.CmsBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */

@Api(tags = "CmsBannerController", description = "内容模块")
@RestController
@RequestMapping("/cms/cmsBanner")
public class CmsBannerController {

    @Autowired
    private CmsBannerService cmsBannerService;

    @ApiOperation("创建Banner")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult create(@RequestBody CmsBanner cmsBanner){

       boolean result =  cmsBannerService.save(cmsBanner);
       if(result){
           return CommonResult.success(null);
       }
       return CommonResult.failed();
    }

    @ApiOperation("查询banner集合")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult<CommonPage<CmsBanner>> list(@RequestParam(value = "title",defaultValue = "") String title,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){

        Page<CmsBanner> result =  cmsBannerService.list(title,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(result));
    }

}

