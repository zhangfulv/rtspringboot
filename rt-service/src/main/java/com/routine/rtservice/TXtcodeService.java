package com.routine.rtservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.routine.rtmapper.TXtcodeMapper;
import com.routine.rtpojo.TXtcode;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    TXtcodeMapper tXtcodeMapper;
    @Override
    public List<TXtcode> list(){
        return this.list(null);
    }


    public IPage<TXtcode> listPager(Integer page ,Integer size){
        QueryWrapper qw = new QueryWrapper();
        IPage<TXtcode> iPage = new Page<>();
        iPage.setPages(page);
        iPage.setSize(size);
        iPage.setCurrent(page);
        IPage iPage1 = tXtcodeMapper.selectPage(iPage, qw);
        return iPage1;
    }
}
