package com.routine.tool.exception;

/**
 * @ClassName EnCodingErrorException 编码错误异常
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/3 10:07
 */
public class EnCodingErrorException extends  BaseException{
    private static final long serialVersionUID = -909272291261736101L;

    public EnCodingErrorException() {
        super("编码错误", 400);
    }
}
