package com.lzz.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/21 15:02
 */
@Document
public class CollectionResultVO {

    private Object _id;
    private Object tag;
    private List<Map<String,Object>> list ;
}
