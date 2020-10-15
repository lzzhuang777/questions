package com.macro.mall.tiny.feign;

import com.lzz.api.CommonPage;
import com.lzz.api.CommonResult;
import com.lzz.model.QmsTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/15 11:28
 */
@FeignClient("questions-question")
public interface TestFeignService {

    @RequestMapping(value = "/qms/qmsTest/addTestQuestions/{testId}", method = RequestMethod.POST)
    CommonResult addTestQuestions(@RequestBody List<Long> quesIds, @PathVariable Long testId);

    @RequestMapping(value = "/qms/qmsTest/list",method = RequestMethod.GET)
    CommonResult<CommonPage<QmsTest>> list(@RequestParam(value = "testName",defaultValue = "") String testName,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    CommonResult create(@RequestBody QmsTest qmsTest);
}
