package com.walsin.coil.domain.model.oip;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OIPD029 {
    private Integer id ;
    private String plantCode ;
    private String productType ;
    private Float condition1 ;
    private Float condition2 ;
    private String condition3 ;
    private String condition4 ;
    private String condition5 ;
    private Float coefficientMill3 ;
    private Float coefficientMill4 ;
    private Float coefficientMill5 ;
    private LocalDateTime dateCreate ;
    private String userCreate ;
    private LocalDateTime dateUpdate ;
    private String userUpdate ;
}
