package com.routine.tool.exception;


import lombok.Data;

/**
 * @ClassName BaseException
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/3 10:13
 */
public class BaseException extends  RuntimeException {
    private static final long serialVersionUID = -5167059255509998270L;
    public Integer code;
    public String message;

    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
