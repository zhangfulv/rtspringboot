package com.routine.tool.jdbcConvert;


import com.routine.tool.DateUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName DateIClsConvert
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/15 16:38
 */
public class DateIClsConvert extends BaseIClsConvert implements IClsConvert {
    @Override
    public void invokeSetConvert(Method method,Object Instance, Object objValue) throws InvocationTargetException,IllegalAccessException{
        super.invokeSetConvert(method,Instance,objValue);
        try {
            method.invoke(Instance, DateUtil.format(objValue, DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
