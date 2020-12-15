package com.routine.tool.exception;

/**
 * @ClassName ConditionErrorException
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/3 10:23
 */
public class ConditionErrorException extends  BaseException {
    private static final long serialVersionUID = -4948213543323213582L;
    public ConditionErrorException() {
        super("条件错误", 412);
    }
}
