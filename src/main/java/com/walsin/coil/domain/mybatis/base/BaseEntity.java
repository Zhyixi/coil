package com.walsin.coil.domain.mybatis.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jane
 * @date 2022/11/21 下午 01:41
 */
@Data
public abstract class BaseEntity implements Serializable {
    private static  final long serialVersionUID = 1L ;
    private String id;
    @TableField(value="use_status")
    private String useStatus;
    @TableField(value="del_status")
    private String delStatus;
    @TableField(value="create_user")
    private String createUser;
    @TableField(value="create_time")
    private String createTime;
    @TableField(value="update_user")
    private String updateUser;
    @TableField(value="update_time")
    private String updateTime;
}
