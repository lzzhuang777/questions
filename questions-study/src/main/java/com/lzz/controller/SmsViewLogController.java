package com.lzz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.model.SmsViewLog;
import com.lzz.service.SmsViewLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */
@RestController
@RequestMapping("/sms/smsViewLog")
public class SmsViewLogController {

    @Autowired
    private SmsViewLogService smsViewLogService;


    @RequestMapping(value = "/list/{memberId}", method = RequestMethod.GET)
    public CommonResult<CommonPage<SmsViewLog>> list(@PathVariable Long memberId, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Page<SmsViewLog> page = smsViewLogService.getList(memberId,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

}

