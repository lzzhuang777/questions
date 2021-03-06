package com.lzz.utils;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/19 15:12
 */
public class Constants {

    public static final String USER_TOKEN_PREFIX = "userToken:";
    public static final int INTEGRATION_SIGN = 10;
    public static final long SYSTEM_ID = 0;
    public static class Redis_Expire {
        public static final long DEFAULT_EXPIRE = 60;//80s 有慢sql，超时时间设置长一点
        public final static int SESSION_TIMEOUT = 2 * 60 * 60;//默认2h
        public final static int REPLACEABLE_TIME_RANGE = 32 * 60;
        public final static int REPLACEMENT_PROTECTION_TIMEOUT = SESSION_TIMEOUT - REPLACEABLE_TIME_RANGE;//默认1.5h
        public final static int REPLACEMENT_DELAY = 2 * 60;//默认2min
        public final static String USER_SIGN_IN = "userSign:%d:%d";
        public final static String USER_RETROACTIVE_SIGN_IN = "userSign:retroactive:%d:%d";
        public final static String Integration_Rules = "integrationRules";
        public final static String USER_SIGN_IN_COUNT = "userSign:count:%d:%d";
    }

    public static class Message_Template{
        public final static long Sign_Day = 2;
        public final static long CUMULATIVE_SIGN = 3;
    }
}
