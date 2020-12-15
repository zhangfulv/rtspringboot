package com.routine.tool.jdbcConvert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName IntegerIClsConvert
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/15 16:44
 */
public class StringClsConvert extends BaseIClsConvert implements IClsConvert {
    @Override
    public void invokeSetConvert(Method method,Object Instance, Object objValue) throws InvocationTargetException, IllegalAccessException {
        super.invokeSetConvert(method,Instance,objValue);
        method.invoke(Instance, objValue.toString());
    }
}
