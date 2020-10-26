package com.lzz.domain;


import lombok.Data;

import java.util.TreeMap;

@Data
public class SignResultVO {

    private TreeMap<Integer, Integer> signMap;

    private Integer signFlag;

}
