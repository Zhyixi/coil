package com.walsin.coil.service.orp.impl;
import tech.tablesaw.api.Table;
public class SPECIAL_ORDER_MACHING_TEST {
    private static ORPP100ServiceImp tborpm100Service = new ORPP100ServiceImp();
//    private static ORPP100Service tborpm100Service;
    public static void main(String[] args) throws Exception {
        String customer = "運錩(零稅)";
        String gradeNo = "316L";
        String product = "2B";
        Integer width = 1240;
        Float weightMin = null; //單重下限
        Float weightMax = null; //單重上限
        Float thicknessMin = 0.84f; // 需求厚度下限
        Float thicknessMax = 1f; // 需求厚度上限
        Float materialThicknessMin = null;
        Float materialThicknessMax = null;
        String Trimming = null;
        String JIS = null;
        String certificate = null;
        String expectDate = "2023-01-10";
        String plantCode = "TC";
        String userName = "ur08366";
        String dateTime = "2023-01-10 12:00:00";
        Table result = tborpm100Service.orderMatching(customer, gradeNo, product,width, weightMin, weightMax,thicknessMin,thicknessMax,materialThicknessMin,materialThicknessMax, Trimming,JIS,certificate,expectDate,plantCode,userName,dateTime);
    }
}

