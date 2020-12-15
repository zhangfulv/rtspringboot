package com.routine.tool.jdbcConvert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName BaseIClsConvert
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/15 16:48
 */
public class BaseIClsConvert implements IClsConvert {
    private final String STR_NULL = "null";
    @Override
    public void invokeSetConvert(Method method, Object Instance, Object objValue) throws InvocationTargetException, IllegalAccessException {
        if(method ==null || Instance ==null || objValue == null || objValue.toString().equals(STR_NULL)) {
            return ;
        }
    }
}
