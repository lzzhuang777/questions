package com.lzz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.model.QmsType;
import com.lzz.service.QmsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lzz
 * @since 2020-09-27
 */
@Api(tags = "QmsTypeController", description = "试题类型模块")
@RestController
@RequestMapping("/qms/qmsType")
public class QmsTypeController {

    @Autowired
    private QmsTypeService qmsTypeService;

    @ApiOperation("分页查询试题集合")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<QmsType>> list(@RequestParam(value = "type", defaultValue = "") String type,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<QmsType> qmsQuestionList = qmsTypeService.list(type, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(qmsQuestionList));
    }

    @ApiOperation("添加试题类型")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody QmsType qmsType) {
        boolean success = qmsTypeService.create(qmsType);
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改试题类型")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id,
                               @RequestBody QmsType qmsType) {
        boolean success = qmsTypeService.update(id, qmsType);
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }
    @ApiOperation("根据类型 id 查询试题类型")
    @RequestMapping(value = "/{qmsTypeId}", method = RequestMethod.GET)
    public CommonResult<QmsType> getQuestionById(@PathVariable Long qmsTypeId) {
        return CommonResult.success(qmsTypeService.getById(qmsTypeId));
    }

    @ApiOperation("查询所有试题类型")
    @RequestMapping(value = "/p/listAll", method = RequestMethod.GET)
    public CommonResult<List<QmsType>> listAll() {
        return CommonResult.success(qmsTypeService.list());
    }


}

