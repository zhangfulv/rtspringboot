package com.routine.tool.exception;

/**
 * @ClassName NotLoginException
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/3 10:20
 */
public class NotLoginException extends  BaseException {
    private static final long serialVersionUID = -4776129591702332424L;

    public NotLoginException() {
        super("未登录", 407);
    }
}
