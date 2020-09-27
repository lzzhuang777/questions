package com.lzz.controller;


import com.lzz.api.CommonResult;
import com.lzz.feign.StudyTimeFeignSevice;
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

    @RequestMapping("/studytime/list/{id}")
    public CommonResult getMemberStudyTimeListTest(@PathVariable("id") Long id) {

        //远程调用拿到该用户的学习时长（学习时长是mock数据）
        return  studyTimeFeignSevice.memberStudyTimeTest(id);

    }
}

