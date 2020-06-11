package org.cheng.common.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.cheng.common.config.ErrorCode;

/**
 * HTTP自定义请求头数据的封装
 * Created by whf on 8/16/16.
 */
public class ResponseResult {
    /**
     * 错误的code值
     */
    private int code;
    /**
     * 错误描述
     */
    private String message;

    private Map<String, String> customHeaders;

    private HttpServletResponse response;

    private static ResponseResult successResult = new ResponseResult(ErrorCode.SUCCESS.code(), ErrorCode.SUCCESS.msg());

    protected ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(int code, String message, HttpServletResponse resp) {
        this(code, message);
        this.response = resp;
    }

    public ResponseResult(int code, String message, Map<String, String> customHeaders, HttpServletResponse resp) {
        this(code, message, resp);

        this.customHeaders = new HashMap<>(customHeaders.size());
        this.customHeaders.putAll(customHeaders);
    }

    public static ResponseResult getSuccessResult() {
        return ResponseResult.successResult;
    }

    public static ResponseResult getSuccessResultNew() {
        return new ResponseResult(ErrorCode.SUCCESS.code(), ErrorCode.SUCCESS.msg());
    }

    /**
     * 添加自定义的键值对
     * @param key
     * @param val
     */
    public void addKeyValuePair(String key, String val) {
        if (null == this.customHeaders) {
            this.customHeaders = new HashMap<>();
        }

        this.customHeaders.put(key, val);
    }

    public Map<String, String> getCustomHeaders() {
        if (null == this.customHeaders) {
            return Collections.emptyMap();
        }

        return this.customHeaders;
    }

    public int getCode() {
        return code;
    }

    public String getCodeAsString() {
        return String.valueOf(this.code);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseResult{");
        sb.append("code=").append(code);
        sb.append(", message='").append(message).append('\'');
        sb.append(", customHeaders=").append(customHeaders);
        sb.append('}');
        return sb.toString();
    }
}
