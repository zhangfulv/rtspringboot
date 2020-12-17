package com.routine.rtservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.routine.rtpojo.TUser;
import com.routine.tool.CryptoUtil;
import com.routine.tool.StringUtil;
import com.routine.tool.exception.TokenInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName TokenService
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/8 17:37
 */
@Service
public class TokenService {
    @Autowired
    private TUserService tUserService;

    public String getJWTToken(TUser tUser){
        String token = JWT.create().withAudience(tUser.getUserId()).sign(Algorithm.HMAC256(tUser.getUserLoginPassword()));
        return token;
    }
    public boolean verifierAesToken(String aesToken){
        String s = CryptoUtil.getInstance().AesDecrypt(aesToken);
        //默认aes 加密字符串为  jwtToken+ & + 时间戳
        return verifier(s.split("&")[0]);
    }
    public boolean verifier(String token){
        if(StringUtil.isEmpty(token)){
            throw  new TokenInvalidException();
        }
        TUser beanById = null;
        String userId = null;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw  new TokenInvalidException();
        }
        beanById = tUserService.getBeanById(userId);
        if(beanById != null){
            // 验证 token
            try {
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(beanById.getUserLoginPassword())).build();
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw  new TokenInvalidException();
            }
            return true;
        }else{
            throw  new TokenInvalidException("用户不存在");
        }

    }
}
