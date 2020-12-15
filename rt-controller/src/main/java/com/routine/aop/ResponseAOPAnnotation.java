package com.routine.aop;


import java.lang.annotation.*;

/**@Description   注解方式，实现返回对象自动格式化
 * @Author zf
 * @Date 2020-12-02 14:23
 * @Update
 **/
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseAOPAnnotation {

}
