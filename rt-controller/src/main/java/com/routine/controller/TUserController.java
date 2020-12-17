package com.routine.controller;


import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSONObject;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.routine.interceptor.PassToken;
import com.routine.rtpojo.TUser;
import com.routine.rtpojo.vo.LoginVo;
import com.routine.rtservice.TUserService;
import com.routine.rtservice.TokenService;
import com.routine.tool.CryptoUtil;
import com.routine.tool.DateUtil;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Zf.link:282734967@qq.com
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/t-user")
public class TUserController {
    @Autowired
    private TUserService tUserService;
    @Autowired
    private TokenService tokenService;
    @CreateCache(expire = 60 * 30 ,name = "tokenCache")
    private Cache<String,String> tokenCache;

    @RequestMapping("login.json")
    @PassToken
    public Object login(@RequestBody @NotNull  String resBody){
        JSONObject jsonObject = JSONObject.parseObject(resBody);
        TUser bean = tUserService.getBean(jsonObject.getString("user_name"), jsonObject.getString("password"));
        LoginVo loginVo = new LoginVo();
        if(bean != null) {
            if(tokenCache.get(bean.getUserId()) != null){
                String s = tokenCache.get(bean.getUserId());
                String[] split = s.split("&");
                loginVo.setToken( CryptoUtil.getInstance().AesEncryptHex(split[0]));
                loginVo.setExpire(new Date(Long.parseLong(split[1])));
            }else {
                String token = tokenService.getJWTToken(bean);
                Date date = new Date(System.currentTimeMillis() + 60 * 30 * 1000);
                String encryStr = String.format("%s&%s", token, date.getTime());
                String aesToken = CryptoUtil.getInstance().AesEncryptHex(encryStr);
                loginVo.setToken(aesToken);
                tokenCache.put(bean.getUserId(), encryStr);
                tokenCache.put(aesToken, bean.getUserId());
                loginVo.setExpire(date);
            }
        }
        return loginVo;
    }


}

