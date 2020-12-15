package com.routine.controller;

import com.alibaba.fastjson.JSONObject;
import com.routine.aop.ResponseAOPAnnotation;
import com.routine.config.database.MySQLProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName BaseController
 * @DESCRIPTION TODO
 * @Author zf
 * @Date 2020/12/2 12:19
 */
@RestController
@ResponseAOPAnnotation
public class BaseController {
    @Autowired
    private MySQLProperties mySQLProperties;
    @PostMapping("test.json")
    public Object test(){
        JSONObject json = new JSONObject();
        json.put("消息...","yes");
        json.put("测试...","yes");
        return json;
    }

    @PostMapping("datasource.json")
    public Object datasource( ){
        return mySQLProperties;
    }
}
