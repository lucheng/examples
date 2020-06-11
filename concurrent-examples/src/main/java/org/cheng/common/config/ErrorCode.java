package org.cheng.common.config;

/**
 * Created by whf on 8/6/16.
 */
public enum ErrorCode {
    SUCCESS(0, "success"),
    FAILED(-1, "failed");

    private int code;
    private String msg;

    private ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }

}
