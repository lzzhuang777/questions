package com.lzz.controller;


import com.lzz.api.CommonResult;
import com.lzz.model.UmsMessage;
import com.lzz.service.UmsMessageService;
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
@Api(tags = "UmsMessageController", description = "用户消息模块")
@RestController
@RequestMapping("/ums/umsMessage")
public class UmsMessageController {

    @Autowired
    private UmsMessageService umsMessageService;

    @ApiOperation("创建消息")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsMessage umsMessage){

      boolean result =  umsMessageService.save(umsMessage);
      if(result){
          return CommonResult.success(null);
      }
      return CommonResult.failed();
    }


}

