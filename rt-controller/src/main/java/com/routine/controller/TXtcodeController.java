package com.routine.controller;


import com.routine.config.aop.ResponseAOPAnnotation;
import com.routine.rtservice.TXtcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author Zf.link:282734967@qq.com
 * @since 2020-12-02
 */
@RestController
@RequestMapping("/t-xtcode")
@ResponseAOPAnnotation
public class TXtcodeController {
    @Autowired
    TXtcodeService tXtcodeService;
    @PostMapping("list.json")
    public Object list(){
            return tXtcodeService.list();
    }
}

