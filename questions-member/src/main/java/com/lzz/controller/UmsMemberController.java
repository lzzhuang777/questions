package com.lzz.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.lzz.api.CommonResult;
import com.lzz.domain.MemberCollectionQuestions;
import com.lzz.dto.LoginParam;
import com.lzz.model.UmsMember;
import com.lzz.service.MemberColletionService;
import com.lzz.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lzz
 * @since 2020-09-27
 */

@Api(tags = "UmsMemberController", description = "用户模块")
@RestController
@RequestMapping("/ums/umsMember")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;
    @Autowired
    private MemberColletionService memberColletionService;

    @ApiOperation("用户登录")
    @RequestMapping(value = "/p/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody LoginParam loginParam) {

        String token = umsMemberService.login(loginParam);
        return CommonResult.success(token);
    }

    @ApiOperation("通过token获取用户信息")
    @RequestMapping(value = "/v/loadCurrentUserByTokenAsJson", method = RequestMethod.GET)
    public CommonResult loadCurrentUserByTokenAsJson(HttpServletRequest request) {

        UmsMember userJsons = umsMemberService.loadCurrentUserByTokenAsJson(request.getHeader("token"));
        if (ObjectUtil.isNotEmpty(userJsons)) {
            return CommonResult.success(userJsons);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改用户信息")
    @RequestMapping(value = "/v/update/{id}", method = RequestMethod.POST)
    public CommonResult updateUmsMember(@PathVariable Long id, @RequestBody UmsMember umsMember) {
        umsMember.setId(id);
        boolean result = umsMemberService.updateById(umsMember);
        if(result){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("用户收藏题目")
    @RequestMapping(value = "/v/collectionQuestion", method = RequestMethod.POST)
    public CommonResult collectionQuestion(@RequestHeader String token, @RequestBody MemberCollectionQuestions memberCollectionQuestions){

        memberColletionService.create(token,memberCollectionQuestions);
        return CommonResult.success(null);
    }

    @ApiOperation("查询用户收藏题目")
    @RequestMapping(value = "/v/selectCollections", method = RequestMethod.GET)
    public CommonResult selectCollections(@RequestHeader String token){

      List<Document> list =  memberColletionService.list(token);
      return CommonResult.success(list);
    }

    @ApiOperation("移除收藏题目")
    @RequestMapping(value = "/v/delCollection", method = RequestMethod.GET)
    public CommonResult delCollection (@RequestHeader String token,@RequestParam Long quesId){

        memberColletionService.deleteByQuesIdAndMemberId(token,quesId);
        return CommonResult.success(null);
    }

    @ApiOperation("查询题目是否已收藏")
    @RequestMapping(value = "/v/isCollection", method = RequestMethod.GET)
    public CommonResult isCollection (@RequestHeader String token,@RequestParam Long quesId){

        boolean result = memberColletionService.isCollection(token,quesId);
        return CommonResult.success(result);
    }

}


