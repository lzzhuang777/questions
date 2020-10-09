package com.macro.mall.tiny.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/9 9:38
 */
@FeignClient("questions-study")
public interface StudyTimeFeignService {


}
