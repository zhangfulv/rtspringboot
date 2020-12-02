package com.routine.rtservice;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.routine.rtmapper.TXtcodeMapper;
import com.routine.rtpojo.TXtcode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author Zf.link:282734967@qq.com
 * @since 2020-12-02
 */
@Service
public class TXtcodeService extends ServiceImpl<TXtcodeMapper, TXtcode>  {

    public List<TXtcode> list(){
        return this.list(null);
    }
}
