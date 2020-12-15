package com.routine.tool.exception;

/**
 * @ClassName NotFoundException
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/3 10:18
 */
public class NotFoundException extends  BaseException {
    private static final long serialVersionUID = 4418966051234304317L;

    public NotFoundException() {
        super("未找到", 404);
    }
}
