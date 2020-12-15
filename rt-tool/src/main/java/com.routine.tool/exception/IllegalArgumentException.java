package com.routine.tool.exception;

/**
 * @ClassName IllegalArgumentException 参数错误
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/3 10:19
 */
public class IllegalArgumentException extends  BaseException{
    private static final long serialVersionUID = 8095385746278944198L;

    public IllegalArgumentException() {
        super("参数错误", 406);
    }
}
