package com.routine.rtservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oracle.xmlns.internal.webservices.jaxws_databinding.JavaWsdlMappingType;
import com.routine.rtmapper.TUserMapper;
import com.routine.rtpojo.TUser;
import com.routine.tool.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Zf.link:282734967@qq.com
 * @since 2020-12-08
 */
@Service
public class TUserService extends ServiceImpl<TUserMapper, TUser>{
    @Autowired
    private TUserMapper tUserMapper;
    public TUser getBean(String userName,String password){
        if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)){
            return null;
        }
        QueryWrapper<TUser> qw = new QueryWrapper<TUser>();
        TUser tUser = new TUser();
        tUser.setUserLoginName(userName);
        tUser.setUserLoginPassword(password);
        qw.setEntity(tUser);
        List<TUser> list = this.list(qw);
        return list.size() > 0 ? list.get(0) : null;
    }

    public TUser getBeanById(String userId){
        if(StringUtil.isEmpty(userId)){
            return null;
        }
        return this.getById(userId);
    }


}
