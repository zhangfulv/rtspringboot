package com.routine.tool.jdbcConvert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zf
 */
public interface IClsConvert {
    /**
     *  方法调用接口
     * @param method 反射方法实例（包含方法名称)
     * @param Instance 对象(方法所属对象)
     * @param objValue (方法需要放入的值)
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    void invokeSetConvert(Method method, Object Instance, Object objValue) throws InvocationTargetException,IllegalAccessException;
}
