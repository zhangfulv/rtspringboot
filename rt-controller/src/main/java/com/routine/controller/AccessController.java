package com.routine.controller;


import com.routine.aop.ResponseAOPAnnotation;
import com.routine.rtservice.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限配置 前端控制器
 * </p>
 *
 * @author Zf.link:282734967@qq.com
 * @since 2020-12-02
 */
@RestController
@RequestMapping("/access")
@ResponseAOPAnnotation
public class AccessController {
    @Autowired
    AccessService accessService;
    @PostMapping("list.json")
    public Object listTest(){
            return accessService.list();
    }


}

