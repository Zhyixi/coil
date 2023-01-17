package com.walsin.coil.domain.model.orp;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ORPP029para {
    private Integer id ;
    private String plantCode ;
    private String productType ;
    private String custGradeNo ;
    private String saleOrderGradeNo ;
    private LocalDateTime dateCreate ;
    private String userCreate ;
    private LocalDateTime dateUpdate ;
    private String userUpdate ;
}
