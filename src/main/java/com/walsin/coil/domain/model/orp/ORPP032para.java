package com.walsin.coil.domain.model.orp;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ORPP032para {
    private Integer id ;
    private String plantCode ;
    private String productType ;
    private Float thicknessMin ;
    private Float thicknessMax ;
    private String inputType ;
    private Integer optionSeq ;
    private Float materialThicknessMin ;
    private Float materialThicknessMax ;
    private LocalDateTime dateCreate ;
    private String userCreate ;
    private LocalDateTime dateUpdate ;
    private String userUpdate ;

}

