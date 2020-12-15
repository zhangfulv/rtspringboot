package com.routine.tool.exception;

/**
 * @ClassName ConditionErrorException
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/3 10:23
 */
public class TokenInvalidException extends  BaseException {
    private static final long serialVersionUID = -4948213543323213582L;
    public TokenInvalidException() {
        super("token无效", 501);
    }
    public TokenInvalidException(String addpendMsg) {
        super(String.format("token无效,%s",addpendMsg), 501);
    }
}
