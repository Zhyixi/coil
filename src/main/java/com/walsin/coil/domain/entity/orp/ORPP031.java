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
@TableName("tborpm031")
public class ORPP031 {
    @TableId(type = IdType.AUTO)
    private Integer id ;
    @TableField(value = "PLANT_CODE")
    private String plantCode ;
    @TableField(value = "SALE_ORDER_WIDTH_MIN")
    private Float saleOrderWidthMin ;
    @TableField(value = "SALE_ORDER_WIDTH_MAX")
    private Float saleOrderWidthMax ;
    @TableField(value = "PROCESS_TYPE")
    private String processType ;
    @TableField(value = "MATERIAL_WIDTH_MIN")
    private Float materialWidthMin ;
    @TableField(value = "MATERIAL_WIDTH_MAX")
    private Float materialWidthMax ;
    @TableField(value = "TOLERATE_MIN")
    private Float tolerateMin ;
    @TableField(value = "TOLERATE_MAX")
    private Float tolerateMax ;
    @TableField(value = "DATE_CREATE")
    private LocalDateTime dateCreate ;
    @TableField(value = "USER_CREATE")
    private String userCreate ;
    @TableField(value = "DATE_UPDATE")
    private LocalDateTime dateUpdate ;
    @TableField(value = "USER_UPDATE")
    private String userUpdate ;

}
