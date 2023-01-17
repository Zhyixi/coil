package com.walsin.coil.domain.entity.pps;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Jane
 * @date 2022/12/8 下午 03:44
 */
@Data
@TableName("ppsinptb01")
public class Ppsinptb01 {
    @TableId(type = IdType.AUTO)
    private Integer id ;
    @TableField(value = "GRADE_NO")
    private String gradeNo ;
    @TableField(value = "GRADE_GROUP")
    private String gradeGroup ;
    @TableField(value = "SPECIAL_EQUIP_CODE")
    private String specialEquipCode ;
}
