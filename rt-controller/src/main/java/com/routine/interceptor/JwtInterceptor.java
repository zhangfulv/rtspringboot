package com.routine.interceptor;

import com.routine.rtservice.TokenService;
import com.routine.tool.JsonResponse;
import com.routine.tool.StringUtil;
import com.routine.tool.exception.TokenInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName JwtInterceptor Interceptor 相当于  preHandler
 *   start -> filter - > servlet - > dispatchHandler - >preHandler -> -...
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/8 17:07
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            return true;
        }
        try {
            if(!redisUtil.hasKey(token) || StringUtil.isEmpty(redisUtil.get(token))){
                    throw  new TokenInvalidException("过期,请重新登录。");
            }
            if(tokenService.verifier(token)){
                return true;
            }
        } catch (TokenInvalidException e){
            String failed = JsonResponse.Failed(e.code, e.message, System.currentTimeMillis());
            response.getOutputStream().write(failed.getBytes());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
