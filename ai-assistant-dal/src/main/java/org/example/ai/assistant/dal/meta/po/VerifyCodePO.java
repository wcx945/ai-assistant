package org.example.ai.assistant.dal.meta.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 验证码表
 * </p>
 *
 * @author wuchaoxin
 * @since 2023-08-27
 */
@Getter
@Setter
@TableName("verify_code")
public class VerifyCodePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 验证码
     */
    @TableField("code")
    private String code;

    /**
     * 验证码类型,=1短信验证码
     */
    @TableField("type")
    private Integer type;

    /**
     * 是否使用,=0未使用,=1已使用,=2已过期
     */
    @TableField("status")
    private Integer status;

    /**
     * 过期时间
     */
    @TableField("expire_time")
    private Long expireTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Long createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Long updateTime;


}
