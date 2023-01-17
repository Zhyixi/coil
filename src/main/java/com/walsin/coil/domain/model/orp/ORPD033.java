package com.walsin.coil.domain.model.orp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ORPD033 {
    private Integer id ;
    private String plantCode ;
    private String daysFromNowMin ;
    private String daysFromNowMax ;
    private String priorityContent ;
    private LocalDateTime dateCreate ;
    private String userCreate ;
    private LocalDateTime dateUpdate ;
    private String userUpdate ;
}
