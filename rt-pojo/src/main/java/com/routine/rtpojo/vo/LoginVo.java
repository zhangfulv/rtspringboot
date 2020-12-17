package com.routine.rtpojo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName LoginVo
 * @DESCRIPTION TODO
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/17 9:48
 */
@Data
public class LoginVo {
    private String token;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date expire;
}
