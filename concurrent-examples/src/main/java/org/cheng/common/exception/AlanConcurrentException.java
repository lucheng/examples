package org.cheng.common.exception;

/**
 * Created by whf on 8/27/16.
 */
public class AlanConcurrentException extends Exception {
	private static final long serialVersionUID = 1L;
	private Exception reason;

    public AlanConcurrentException(Exception reason) {
        this.reason = reason;
    }

    public Exception getReason() {
        return this.reason;
    }
}
