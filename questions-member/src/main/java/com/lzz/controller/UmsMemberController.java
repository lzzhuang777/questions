package com.lzz.controller;


import com.lzz.api.CommonResult;
import com.lzz.dto.LoginParam;
import com.lzz.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */

@Api(tags = "UmsMemberController", description = "用户模块")
@RestController
@RequestMapping("/ums/umsMember")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @ApiOperation("用户登录")
    @RequestMapping(value = "/ums/umsMember",method = RequestMethod.POST)
    public CommonResult login(@RequestBody LoginParam loginParam){


        umsMemberService.login(loginParam);
        return CommonResult.success(null);
    }



}

