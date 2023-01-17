package com.walsin.coil.domain.entity.orp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author ur10369
 * @date 2022/12/26 下午 2:35
 */
@Data
@TableName("ppsinptb15")
public class ORPV101 {

    @TableField(value = "CUST_ABBREVIATIONS")
    private String custAbbreviations;



}

