package org.cheng.common.config;

/**
 * 常量定义
 * Created by whf on 8/5/16.
 */
public class Const {
    public static final String TEST_SECRET_KEY = "secret_key";

    /**
     * 每页默认大小
     */
    public static final int DEFAULT_PAGE_SIZE = 8;
    public static final String DEFAULT_PAGE_SIZE_STRING = "8";

    public static final int AVERAGE_PARM_LENGTH = 15;

    public static final int ERROR_CODE_SUCCESS = 0;
    public static final String ERROR_CODE_SUCCESS_MSG = "success";


    public static final int HTTP_STATUS_BUSINESS_ERROR = 600;

/*    public static class RequestParam {
        public static final String SIGN = "sign";
        *//* 时间戳 *//*
        public static final String TIMESTAMP = "ts";
        public static final String PUBLIC_KEY = "pk";
    }*/

    /**
     * Http请求头
     */
    public static class HeaderParam {
        public static final String PREFIX = "X-Alan-Custom-";

        public static final String CODE = "X-Alan-Code";
        public static final String MESSAGE = "X-Alan-Message";
        public static final String IP = "X-Alan-IP";
    }

}
