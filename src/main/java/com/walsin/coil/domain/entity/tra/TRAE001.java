package com.walsin.coil.domain.entity.tra;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("tbtram001")
public class TRAE001 {
    @TableId(type = IdType.AUTO)
    private Integer id ;
    @TableField(value = "PLANT_CODE")
    private String plantCode ;
    @TableField(value = "EQUIP_CODE")
    private String equipCode ;
    @TableField(value = "LINEUP_PRODUCT_TYPE")
    private String lineupProductType ;
    @TableField(value = "PRODUCT_TYPE")
    private String productType ;
    @TableField(value = "PROCESS_FACTOR")
    private String processFactor ;
    @TableField(value = "PROCESS_FACTOR_MIN")
    private Float processFactorMin ;
    @TableField(value = "PROCESS_FACTOR_MAX")
    private Float processFactorMax ;
    @TableField(value = "DATE_CREATE")
    private LocalDateTime dateCreate ;
    @TableField(value = "USER_CREATE")
    private String userCreate ;
    @TableField(value = "DATE_UPDATE")
    private LocalDateTime dateUpdate ;
    @TableField(value = "USER_UPDATE")
    private String userUpdate ;
}
