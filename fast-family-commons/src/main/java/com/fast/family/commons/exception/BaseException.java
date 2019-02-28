package com.fast.family.commons.exception;

/**
 * @author 张顺
 * @version 1.0
 */
public class BaseException extends RuntimeException {

    private int errCode = 500;

    private String errMessage;

    public BaseException(String message) {
        super(message);
        this.errMessage = message;
    }

    public BaseException(int errCode, String errMessage) {
        super(errMessage);
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public BaseException(String message, int errCode, String errMessage) {
        super(message);
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
