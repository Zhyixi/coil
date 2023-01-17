package com.walsin.coil.domain.model.orp;
import lombok.Data;
// 用來裝數據的
@Data
public class ORPP100para {
    private String customer ;
    private String gradeNo ;
    private String product;
    private Integer width;
    private Float weightMin;
    private Float weightMax;
    private Float thicknessMin;
    private Float thicknessMax;
    private Float materialThicknessMin;
    private Float materialThicknessMax;
    private String Trimming;
    private String JIS;
    private String certificate;
    private String expectDate;
    private String plantCode;
    private String userName;
    private String dateTime;//LocalDateTime
}
