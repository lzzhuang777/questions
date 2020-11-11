package com.macro.mall.tiny.feign;

import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.model.QmsType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/9/30 9:54
 */
@FeignClient("questions-question")
public interface QuestionTypeFeignService {


    @RequestMapping(value = "/qms/qmsType/list", method = RequestMethod.GET)
    CommonResult<CommonPage<QmsType>> list(@RequestParam(value = "type", defaultValue = "") String type,
                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);

    @RequestMapping(value = "/qms/qmsType/{qmsTypeId}", method = RequestMethod.GET)
    CommonResult<QmsType> getQuestionById(@PathVariable Long qmsTypeId);

    @RequestMapping(value = "/qms/qmsType/p/listAll", method = RequestMethod.GET)
    CommonResult<List<QmsType>> listAll();
}
