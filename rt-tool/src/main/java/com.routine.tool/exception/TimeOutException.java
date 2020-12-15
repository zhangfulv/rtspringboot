package com.routine.tool.exception;

/**
 * @ClassName TimeOutException
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/3 10:21
 */
public class TimeOutException extends  BaseException {
    private static final long serialVersionUID = 2913328179993325857L;

    public TimeOutException() {
        super("超时", 408);
    }
}
