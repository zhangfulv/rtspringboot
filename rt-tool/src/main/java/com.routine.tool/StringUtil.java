package com.routine.tool;

/**
 * @ClassName StringUtil
 * @DESCRIPTION TODO
 * @Author zf
 * @Date 2020/12/2 14:42
 */
public class StringUtil {
    private StringUtil(){}
    /**
     * 判断字符串是否为空
     * null或者 只有空格的 默认为空.
     * @param str 待判定的字符串
     * @return
     * Zf 2018年4月26日 上午10:02:45
     */
    public static boolean isEmpty(String str){
        return  str == null || str.trim().equals("") || "null".equals(str);
    }
}
