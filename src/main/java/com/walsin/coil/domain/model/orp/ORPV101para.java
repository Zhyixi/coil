package com.walsin.coil.domain.model.orp;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ORPV101para {
    private int id;
    private String plantCode;
    private String customer;
    private String productKind;
    private String product;
    private String idNo;
    private String gradeNo;
    private int width;
    private int thickness;
    private String quality;
    private int weight;
    private String wipTime;
    private String datePlanInStorage;
    private String dateDelveryPp;
    private String memo;
    private String dateCreate;
    private String userCreate;
    private String dateUpdate;
    private String userUpdate;

}

