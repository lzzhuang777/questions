package com.lzz.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.lzz.model.UmsMember;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/16 15:45
 */

public class GenerateToken {

    public static String tokenPrefix = "token:";

    /**
     * 生成token方法（PC：“前缀PC-USERCODE-USERID-CREATIONDATE-RONDEM[6位]”）
     * @param user
     * @return
     */
    public static String generateToken( UmsMember user) {
        StringBuilder sb = new StringBuilder();
        sb.append(tokenPrefix);//统一前缀
        // 设备，暂时固定为PC
        sb.append("PC-");
        // usercode
        sb.append(SecureUtil.md5(StrUtil.isEmpty(user.getPhone()) ? user.getUnionid() : user.getPhone()) + "-");
        // userid
        sb.append(user.getId() + "-");
        // creation date
        sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-");
        // 6位random，暂无特定含义或用途
        sb.append(UUID.randomUUID().toString().substring(0, 6));
        return sb.toString();
    }


}
