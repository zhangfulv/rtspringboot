package com.routine.tool;

import java.lang.annotation.*;

/**
 *  标签备注表名、字段名
 * @author zf
 */
@Documented
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface jdbcAnnotation {

     String tableName() default "";
     String field() default "";
}
