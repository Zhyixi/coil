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
@TableName("tborpm032")
public class ORPP032 {
    @TableId(type = IdType.AUTO)
    private Integer id ;
    @TableField(value = "PLANT_CODE")
    private String plantCode ;
    @TableField(value = "PRODUCT_TYPE")
    private String productType ;
    @TableField(value = "THICKNESS_MIN")
    private Float thicknessMin ;
    @TableField(value = "THICKNESS_MAX")
    private Float thicknessMax ;
    @TableField(value = "INPUT_TYPE")
    private String inputType ;
    @TableField(value = "OPTION_SEQ")
    private Integer optionSeq ;
    @TableField(value = "MATERIAL_THICKNESS_MIN")
    private Float materialThicknessMin ;
    @TableField(value = "MATERIAL_THICKNESS_MAX")
    private Float materialThicknessMax ;
    @TableField(value = "DATE_CREATE")
    private LocalDateTime dateCreate ;
    @TableField(value = "USER_CREATE")
    private String userCreate ;
    @TableField(value = "DATE_UPDATE")
    private LocalDateTime dateUpdate ;
    @TableField(value = "USER_UPDATE")
    private String userUpdate ;

}

