package com.walsin.coil.domain.entity.oip;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ur09024
 * @date 2022/12/15 下午 3:30
 */
@Data
@TableName("tboipm029")
public class OIPE029 {
    @TableId(type = IdType.AUTO)
    private Integer id ;
    @TableField(value = "PLANT_CODE")
    private String plantCode ;
    @TableField(value = "PRODUCT_TYPE")
    private String productType ;
    @TableField(value = "CONDITION1")
    private Float condition1 ;
    @TableField(value = "CONDITION2")
    private Float condition2 ;
    @TableField(value = "CONDITION3")
    private String condition3 ;
    @TableField(value = "CONDITION4")
    private String condition4 ;
    @TableField(value = "CONDITION5")
    private String condition5 ;
    @TableField(value = "COEFFICIENT_MILL3")
    private Float coefficientMill3 ;
    @TableField(value = "COEFFICIENT_MILL4")
    private Float coefficientMill4 ;
    @TableField(value = "COEFFICIENT_MILL5")
    private Float coefficientMill5 ;
    @TableField(value = "DATE_CREATE")
    private LocalDateTime dateCreate ;
    @TableField(value = "USER_CREATE")
    private String userCreate ;
    @TableField(value = "DATE_UPDATE")
    private LocalDateTime dateUpdate ;
    @TableField(value = "USER_UPDATE")
    private String userUpdate ;

}


