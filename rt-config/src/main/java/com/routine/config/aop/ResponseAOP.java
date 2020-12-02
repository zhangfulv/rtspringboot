package com.routine.config.aop;

import com.routine.tool.JSONResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @ClassName ResponseAOP 响应消息aop处理
 * @DESCRIPTION TODO 将统一返回对象进行格式化处理，返回json
 * @Author zf
 * @Date 2020/12/2 13:50
 */
@Aspect
@Component
public class ResponseAOP {


    @Pointcut("@within(com.routine.config.aop.ResponseAOPAnnotation)")
    public void rAopPointCut(){}


    @Around("rAopPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        String success = JSONResponse.Success(result, beginTime);
        return success;
    }



}
