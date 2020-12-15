package com.routine.controller;


import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSONObject;
import com.routine.interceptor.PassToken;
import com.routine.rtpojo.TUser;
import com.routine.rtservice.TUserService;
import com.routine.rtservice.TokenService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("login.json")
    @PassToken
    public Object login(@RequestBody @NotNull  String resBody){
        JSONObject jsonObject = JSONObject.parseObject(resBody);
        TUser bean = tUserService.getBean(jsonObject.getString("user_name"), jsonObject.getString("password"));
        String token = tokenService.getJWTToken(bean);
        String encode = MD5.create().digestHex(token);
        return encode;
    }

}

