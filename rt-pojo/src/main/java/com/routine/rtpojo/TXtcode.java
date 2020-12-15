package com.routine.rtpojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author Zf.link:282734967@qq.com
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TXtcode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId
    private String codeId;

    /**
     * 字典类型
     */
    private String codeType;

    /**
     * 类型名称
     */
    private String codeTypeName;

    /**
     * 字典编码
     */
    private String codeCode;

    /**
     * 字典名称
     */
    private String codeName;

    /**
     * 创建时间
     */
    private LocalDateTime codeCreatetime;

    /**
     * 字典显示顺序
     */
    private Integer codeSeq;

    /**
     * 创建人ID
     */
    private Integer codeCreateuser;

    /**
     * 字典状态
     */
    private String codeStatus;


}
