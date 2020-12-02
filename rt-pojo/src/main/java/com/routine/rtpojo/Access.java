package com.routine.rtpojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限配置
 * </p>
 *
 * @author Zf.link:282734967@qq.com
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Access implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否为调试表，只允许在开发环境使用，测试和线上环境禁用
     */
    private Integer debug;

    /**
     * 实际表名，例如 apijson_user
     */
    private String name;

    /**
     * 外部调用的表别名，例如 User
     */
    private String alias;

    /**
     * 允许 get 的角色列表，例如 ["LOGIN", "CONTACT", "CIRCLE", "OWNER"]
用 JSON 类型不能设置默认值，反正权限对应的需求是明确的，也不需要自动转 JSONArray。
TODO: 直接 LOGIN,CONTACT,CIRCLE,OWNER 更简单，反正是开发内部用，不需要复杂查询。
     */
    private String get;

    /**
     * 允许 head 的角色列表，例如 ["LOGIN", "CONTACT", "CIRCLE", "OWNER"]
     */
    private String head;

    /**
     * 允许 gets 的角色列表，例如 ["LOGIN", "CONTACT", "CIRCLE", "OWNER"]
     */
    private String gets;

    /**
     * 允许 heads 的角色列表，例如 ["LOGIN", "CONTACT", "CIRCLE", "OWNER"]
     */
    private String heads;

    /**
     * 允许 post 的角色列表，例如 ["LOGIN", "CONTACT", "CIRCLE", "OWNER"]
     */
    private String post;

    /**
     * 允许 put 的角色列表，例如 ["LOGIN", "CONTACT", "CIRCLE", "OWNER"]
     */
    private String put;

    /**
     * 允许 delete 的角色列表，例如 ["LOGIN", "CONTACT", "CIRCLE", "OWNER"]
     */
    private String delete;

    /**
     * 创建时间
     */
    private LocalDateTime date;


}
