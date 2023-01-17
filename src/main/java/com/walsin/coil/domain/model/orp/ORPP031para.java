package com.walsin.coil.domain.model.orp;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ORPP031para {
    private Integer id ;
    private String plantCode ;
    private Float saleOrderWidthMin ;
    private Float saleOrderWidthMax ;
    private String processType ;
    private Float materialWidthMin ;
    private Float materialWidthMax ;
    private Float tolerateMin ;
    private Float tolerateMax ;
    private LocalDateTime dateCreate ;
    private String userCreate ;
    private LocalDateTime dateUpdate ;
    private String userUpdate ;
}
