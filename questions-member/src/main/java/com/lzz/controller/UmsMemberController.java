package com.lzz.controller;


import com.lzz.api.CommonResult;
import com.lzz.feign.StudyTimeFeignSevice;
import com.lzz.feign.StudyViewLogFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/ums/umsMember")
public class UmsMemberController {

    @Autowired
    private StudyTimeFeignSevice studyTimeFeignSevice;

    @Autowired
    private StudyViewLogFeignService studyViewLogFeignService;

    @RequestMapping("/studytime/list/{id}")
    public CommonResult getMemberStudyTimeListTest(@PathVariable("id") Long id) {

        return  CommonResult.success(studyTimeFeignSevice.memberStudyTimeTest(id));
    }

    @RequestMapping("/viewLog/list/{id}")
    public CommonResult getViewLogList(@PathVariable("id") Long id) {

        return  CommonResult.success(studyViewLogFeignService.list(id,5,1));
    }


}

