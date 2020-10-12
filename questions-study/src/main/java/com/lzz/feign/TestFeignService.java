package com.lzz.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/12 16:02
 */

@FeignClient(name = "questions-question")
public interface TestFeignService {

    @RequestMapping(value = "/qms/qmsTest/selectIdsByTestId/{testId}", method = RequestMethod.GET)
    List<Long> selectIdsByTestId(@PathVariable Long testId);
}
