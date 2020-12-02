package com.routine.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName JSONResponse json响应统一格式
 * @DESCRIPTION TODO
 * @Author zf
 * @Date 2020/12/2 12:38
 */
@Data
@ToString
public class JSONResponse {
    private int code = 200;
    private String msg = "成功";
    private boolean ok = true;
    private long running_time = 0l;
    private Object obj;//数组对象
    public static final int CODE_SUCCESS = 200; //成功
    public static final int CODE_UNSUPPORTED_ENCODING = 400; //编码错误
    public static final int CODE_ILLEGAL_ACCESS = 401; //权限错误
    public static final int CODE_UNSUPPORTED_OPERATION = 403; //禁止操作
    public static final int CODE_NOT_FOUND = 404; //未找到
    public static final int CODE_ILLEGAL_ARGUMENT = 406; //参数错误
    public static final int CODE_NOT_LOGGED_IN = 407; //未登录
    public static final int CODE_TIME_OUT = 408; //超时
    public static final int CODE_CONFLICT = 409; //重复，已存在
    public static final int CODE_CONDITION_ERROR = 412; //条件错误，如密码错误
    public static final int CODE_UNSUPPORTED_TYPE = 415; //类型错误
    public static final int CODE_OUT_OF_RANGE = 416; //超出范围
    public static final int CODE_NULL_POINTER = 417; //对象为空
    public static final int CODE_SERVER_ERROR = 500; //服务器内部错误
    public static JSONResponse build(){
        return new JSONResponse();
    }
    public static String Success(Object obj,long funcStartTime){
        JSONResponse build = JSONResponse.build();
        build.obj = obj;
        build.running_time = DateUtil.computerDiff(funcStartTime);
        return JSON.toJSONString(build);
    }
    public static String Failed(int errCode,long funcStartTime){
        JSONResponse build = JSONResponse.build();
        build.code = errCode;
        build.ok = false;
        build.running_time = DateUtil.computerDiff(funcStartTime);
        return JSON.toJSONString(build);
    }
}
