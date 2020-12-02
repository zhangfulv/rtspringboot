package com.routine.rtservice;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.routine.rtmapper.AccessMapper;
import com.routine.rtpojo.Access;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限配置 服务实现类
 * </p>
 *
 * @author Zf.link:282734967@qq.com
 * @since 2020-12-02
 */
@Service
public class AccessService extends ServiceImpl<AccessMapper, Access> {
    public List<Access> list(){
        return this.list(null);
    }
}
