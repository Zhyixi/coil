package com.walsin.coil.domain.model.orp;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ORPP030para {
    private Integer id ;
    private String plantCode ;
    private String saleOrderGradeNo ;
    private String materialGradeNo ;
    private String purchaseFlag ;
    private Integer optionSeq ;
    private LocalDateTime dateCreate ;
    private String userCreate ;
    private LocalDateTime dateUpdate ;
    private String userUpdate ;
}
