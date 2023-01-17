package com.walsin.coil.domain.model.tra;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TRAD001 {
    private Integer id ;
    private String plantCode ;
    private String equipCode ;
    private String lineupProductType ;
    private String productType ;
    private String processFactor ;
    private String processFactorMin ;
    private String processFactorMax ;
    private LocalDateTime dateCreate ;
    private String userCreate ;
    private LocalDateTime dateUpdate ;
    private String userUpdate ;
}
