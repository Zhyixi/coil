package com.walsin.coil.domain.entity.orp;

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
@TableName("tborpm029")
public class ORPP029 {
    @TableId(type = IdType.AUTO)
    private Integer id ;
    @TableField(value = "PLANT_CODE")
    private String plantCode ;
    @TableField(value = "PRODUCT_TYPE")
    private String productType ;
    @TableField(value = "CUST_GRADE_NO")
    private String custGradeNo ;
    @TableField(value = "SALE_ORDER_GRADE_NO")
    private String saleOrderGradeNo ;
    @TableField(value = "DATE_CREATE")
    private LocalDateTime dateCreate ;
    @TableField(value = "USER_CREATE")
    private String userCreate ;
    @TableField(value = "DATE_UPDATE")
    private LocalDateTime dateUpdate ;
    @TableField(value = "USER_UPDATE")
    private String userUpdate ;

}

