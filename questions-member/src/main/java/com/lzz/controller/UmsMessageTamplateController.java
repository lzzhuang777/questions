package com.lzz.controller;


import com.lzz.api.CommonResult;
import com.lzz.model.UmsMessage;
import com.lzz.model.UmsMessageTamplate;
import com.lzz.service.UmsMessageTamplateService;
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
 * @author lzz
 * @since 2020-10-23
 */
@Api(tags = "UmsMessageTamplateController", description = "用户消息模板模块")
@RestController
@RequestMapping("/ums/umsMessageTamplate")
public class UmsMessageTamplateController {

    @Autowired
    private UmsMessageTamplateService umsMessageTamplateService;

    @ApiOperation("创建消息模板")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsMessageTamplate template){

        boolean result =  umsMessageTamplateService.save(template);
        if(result){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }



}

