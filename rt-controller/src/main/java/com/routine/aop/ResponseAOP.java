package com.routine.aop;

import com.routine.tool.JsonResponse;
import com.routine.tool.exception.*;
import com.routine.tool.exception.IllegalAccessException;
import com.routine.tool.exception.IllegalArgumentException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @ClassName ResponseAOP 响应消息aop处理 基于注解响应/controller下所有返回结果
 * @DESCRIPTION TODO 将统一返回对象进行格式化处理，返回json
 * @Author zf
 * @Date 2020/12/2 13:50
 */
@Aspect
@Component
public class ResponseAOP {


    @Pointcut("execution(public * com.routine.controller.*.*(..)) || @within(com.routine.aop.ResponseAOPAnnotation)")
    public void rAopPointCut(){}


    @Around("rAopPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = null;
        String resultObject = null;
        try {
            result = proceedingJoinPoint.proceed();
            resultObject = JsonResponse.Success(result, beginTime);
        }catch (ConditionErrorException e){
            resultObject =JsonResponse.Failed(e.code,e.message,beginTime);
        }catch (EnCodingErrorException e){
            resultObject =JsonResponse.Failed(e.code,e.message,beginTime);
        }catch (IllegalAccessException e){
            resultObject =JsonResponse.Failed(e.code,e.message,beginTime);
        }catch (IllegalArgumentException e){
            resultObject =JsonResponse.Failed(e.code,e.message,beginTime);
        }catch (NotFoundException e){
            resultObject =JsonResponse.Failed(e.code,e.message,beginTime);
        }catch (NotLoginException e){
            resultObject = JsonResponse.Failed(e.code,e.message,beginTime);
        }catch (ServerErrorException e){
            resultObject =JsonResponse.Failed(e.code,e.message,beginTime);
        }catch (TimeOutException e){
            resultObject =JsonResponse.Failed(e.code,e.message,beginTime);
        }catch (TokenInvalidException e){
            resultObject =JsonResponse.Failed(e.code,e.message,beginTime);
        }catch (Exception e){
            resultObject = JsonResponse.Failed(9999,e.getMessage(),beginTime);
        }finally {

        }
        return resultObject;
    }



}
