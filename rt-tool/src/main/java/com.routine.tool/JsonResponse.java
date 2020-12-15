package com.routine.tool;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

/**
 * @ClassName JsonResponse json响应统一格式
 * @DESCRIPTION TODO
 * @Author zf
 * @Date 2020/12/2 12:38
 */
@Data
@ToString
public class JsonResponse implements Serializable{
    private static final long serialVersionUID = -7507201371761531015L;
    private int code = 200;
    private String msg = "成功";
    private boolean ok = true;
    private String runningTime = "";
    /***************
    * desc: 返回对象
    ***************/
    private Object obj;


    /**
    * desc: 成功
    **/
    public static final int CODE_SUCCESS = 200;
    /**
    * desc: 编码错误
    */
    public static final int CODE_UNSUPPORTED_ENCODING = 400;
    /**
    * desc:权限错误
    */
    public static final int CODE_ILLEGAL_ACCESS = 401;
    /**
    * desc:未找到
    */
    public static final int CODE_NOT_FOUND = 404;
    /**
    * desc:参数错误
    */
    public static final int CODE_ILLEGAL_ARGUMENT = 406;
    /**
    * desc:未登录
    */
    public static final int CODE_NOT_LOGGED_IN = 407;
    /**
    * desc:超时
    */
    public static final int CODE_TIME_OUT = 408;
    /**
    * desc:条件错误，如密码错误
    */
    public static final int CODE_CONDITION_ERROR = 412;
    /**
    * desc:对象为空
    */
    public static final int CODE_NULL_POINTER = 417;
    /**
    * desc:服务器内部错误
    */
    public static final int CODE_SERVER_ERROR = 500;
    public static JsonResponse build(){
        return new JsonResponse();
    }
    public static String Success(Object obj,long funcStartTime){
        JsonResponse build = JsonResponse.build();
        build.obj = obj;
        build.runningTime = String.format("%sms",DateUtil.computerDiff(funcStartTime));
        return JSON.toJSONString(build);
    }
    public static String Failed(int errCode,String msg,long funcStartTime){
        JsonResponse build = JsonResponse.build();
        build.code = errCode;
        build.ok = false;
        build.msg = msg;
        build.runningTime = String.format("%sms",DateUtil.computerDiff(funcStartTime));
        return JSON.toJSONString(build);
    }
}
