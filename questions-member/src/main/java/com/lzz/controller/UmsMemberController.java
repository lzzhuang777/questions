package com.lzz.controller;


import cn.hutool.core.util.StrUtil;
import com.lzz.api.CommonResult;
import com.lzz.dto.LoginParam;
import com.lzz.model.UmsMember;
import com.lzz.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lzz
 * @since 2020-09-27
 */

@Api(tags = "UmsMemberController", description = "用户模块")
@RestController
@RequestMapping("/ums/umsMember")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @ApiOperation("用户登录")
    @RequestMapping(value = "/p/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody LoginParam loginParam) {

        Object o = umsMemberService.login(loginParam);
        return CommonResult.success(o);
    }

    @ApiOperation("通过token获取用户信息")
    @RequestMapping(value = "/v/loadCurrentUserByTokenAsJson", method = RequestMethod.GET)
    public CommonResult loadCurrentUserByTokenAsJson(@RequestParam String token) {

        String userJsons = umsMemberService.loadCurrentUserByTokenAsJson(token);
        if (StrUtil.isNotEmpty(userJsons)) {
            return CommonResult.success(userJsons);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改用户信息")
    @RequestMapping(value = "/v/update/{id}", method = RequestMethod.POST)
    public CommonResult updateUmsMember(@PathVariable Long id, @RequestBody UmsMember umsMember) {
        umsMember.setId(id);
        boolean result = umsMemberService.updateById(umsMember);
        if(result){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }




}


