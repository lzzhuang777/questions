package com.lzz.controller;


import com.lzz.api.CommonResult;
import com.lzz.model.QmsType;
import com.lzz.service.QmsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
@RestController
@RequestMapping("/qms/qmsType")
public class QmsTypeController {

    @Autowired
    private QmsTypeService qmsTypeService;

    @RequestMapping(value = "/getQuestionById/{qmsTypeId}",method = RequestMethod.GET)
    public CommonResult<QmsType> getQuestionById(@PathVariable Long qmsTypeId){
        return CommonResult.success(qmsTypeService.getById(qmsTypeId));
    }

}

