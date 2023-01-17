package com.walsin.coil.domain.entity.orp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author ur10369
 * @date 2022/12/26 下午 2:35
 */
@Data
@TableName("tborpm999")
public class ORPV999 {

    @TableId(value = "ID", type = IdType.AUTO)
    private String id;
    @TableField(value = "PLANT_CODE")
    private String plantCode;
    @TableField(value = "customer")
    private String customer;
    @TableField(value = "productKind")
    private String productKind;
    @TableField(value = "product")
    private String product;
    @TableField(value = "ID_NO")
    private String idNo;
    @TableField(value = "gradeNo")
    private String gradeNo;
    @TableField(value = "width")
    private int width;
    @TableField(value = "thickness")
    private int thickness;
    @TableField(value = "quality")
    private String quality;
    @TableField(value = "weight")
    private int weight;
    @TableField(value = "wipTime")
    private String wipTime;
    @TableField(value = "DATE_PLAN_IN_STORAGE")
    private String datePlanInStorage;
    @TableField(value = "DATE_DELIVERY_PP")
    private String dateDelveryPp;
    @TableField(value = "memo")
    private String memo;
    @TableField(value = "DATE_CREATE")
    private String dateCreate;
    @TableField(value = "USER_CREATE")
    private String userCreate;
    @TableField(value = "DATE_UPDATE")
    private String dateUpdate;
    @TableField(value = "USER_UPDATE")
    private String userUpdate;



}

