package com.walsin.coil.domain.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.walsin.coil.domain.mybatis.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Jane
 * @date 2022/12/8 上午 09:13
 */
@Data
@Accessors(chain = true)
//自动生成无参的构造函数
@NoArgsConstructor
//自动生成全参数的构造函数
@AllArgsConstructor
@TableName("system_users")
public class SystemUser extends BaseEntity {
    @TableField(value = "user_code")
    private String userCode;
    @TableField(value = "user_name")
    private String userName;
    @TableField(value = "user_nick_name")
    private String userNickName;
    @TableField(value = "user_avatar")
    private String userAvatar;
    @TableField(value = "password")
    private String password;
    @TableField(value = "salt")
    private String salt;
    @TableField(value = "email")
    private String email;
    @TableField(value = "phone")
    private String phone;
    @TableField(value = "landline")
    private String landline;
    @TableField(value = "platform")
    private String platform;
}
