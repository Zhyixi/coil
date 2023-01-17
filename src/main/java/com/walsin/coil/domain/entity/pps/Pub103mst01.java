package com.walsin.coil.domain.entity.pps;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wen
 * @date 2023/1/5 上午 8:45
 */
@Data
@TableName("pub103mst01")
public class Pub103mst01 {
    @TableField(value = "PRONO")
    private String proNo ;
    @TableField(value = "PRONO_DESC")
    private String pronoDesc ;
}
