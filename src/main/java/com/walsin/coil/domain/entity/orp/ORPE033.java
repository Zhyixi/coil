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
@TableName("tborpm033")
public class ORPE033 {
    @TableId(type = IdType.AUTO)
    private Integer id ;
    @TableField(value = "PLANT_CODE")
    private String plantCode ;
    @TableField(value = "DAYS_FROM_NOW_MIN")
    private String daysFromNowMin ;
    @TableField(value = "DAYS_FROM_NOW_MAX")
    private String daysFromNowMax ;
    @TableField(value = "PRIORITY_CONTENT")
    private String priorityContent ;
    @TableField(value = "DATE_CREATE")
    private LocalDateTime dateCreate ;
    @TableField(value = "USER_CREATE")
    private String userCreate ;
    @TableField(value = "DATE_UPDATE")
    private LocalDateTime dateUpdate ;
    @TableField(value = "USER_UPDATE")
    private String userUpdate ;

}

