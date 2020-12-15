package com.routine.rtpojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Zf.link:282734967@qq.com
 * @since 2020-12-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String userId;

    /**
     * 登录用户名
     */
    private String userLoginName;

    /**
     * 登录密码
     */
    private String userLoginPassword;

    /**
     * 用户登录时间
     */
    private LocalDateTime userLoginTime;

    /**
     * 用户创建时间
     */
    private LocalDateTime userCreateTime;

    /**
     * 用户关联角色id
     */
    private String userRoleId;

    /**
     * 用户关联组织id
     */
    private String userOrgId;

    /**
     * 昵称
     */
    private String userNickName;

    /**
     * 用户真实姓名
     */
    private String userRealName;

    /**
     * 用户头像
     */
    private String userImgUrl;

    /**
     * 联系手机电话
     */
    private String userPhone;

    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 用户email
     */
    private String userEmail;

    /**
     * 用户组织根节点id(用于直接读取组织名称(用户类型))
     */
    private String userRootOrgId;

    /**
     * 状态（0：正常；1：注销）
     */
    private String userStatus;

    /**
     * 创建用户
     */
    private String userCreateuser;

    /**
     * 相关单位ID
     */
    private String companyId;

    /**
     * 菜单id
     */
    private String menuId;


}
