package com.tiansuo.file.storage.core.exception;


import com.tiansuo.file.storage.api.enums.MinioPlusErrorCode;

/**
 * MinioPlus专用异常定义
 * @author zhangb
 */
public class MinioPlusException extends RuntimeException {

    private static final long serialVersionUID = 772046747932011086L;

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MinioPlusException() {
        super();
    }

    public MinioPlusException(String message) {
        super(message);
    }

    public MinioPlusException(MinioPlusErrorCode minioPlusErrorCode){
        this.code = minioPlusErrorCode.getCode();
        this.message = minioPlusErrorCode.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MinioPlusException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
