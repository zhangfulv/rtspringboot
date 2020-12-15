package com.routine.tool.exception;

/**
 * @ClassName IllegalAccessException 非法访问异常
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/3 10:12
 */
public class IllegalAccessException extends  BaseException{
    private static final long serialVersionUID = 5618340620317204001L;

    public IllegalAccessException() {
        super("权限错误", 401);
    }
}
