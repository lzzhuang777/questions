package com.lzz.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzz.dto.LoginParam;
import com.lzz.mapper.UmsMemberMapper;
import com.lzz.model.UmsMember;
import com.lzz.service.UmsMemberService;
import com.lzz.utils.GenerateToken;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author macro
 * @since 2020-09-27
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {


    @Override
    public Object[] login(LoginParam loginParam) {

        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMember ::getPhone,loginParam.getPhone())
                .eq(UmsMember::getPassword, SecureUtil.md5(loginParam.getPassword()));
        UmsMember umsMember = getOne(wrapper);
        if(ObjectUtil.isEmpty(umsMember)){
            return null;
        }
        String token = GenerateToken.generateToken(umsMember);
        return new Object[]{token};
    }
}
