package com.walsin.coil.service.orp;
import tech.tablesaw.api.Table;
/**
 * @author ur08366
 * @date 2022/12/15 下午 3:30
 */
public interface ORPP100Service{
    Table orderMatching(String customer,String gradeNo,String product,Integer width,Float weightMin ,Float weightMax,Float thicknessMin,Float thicknessMax,Float materialThicknessMin,Float materialThicknessMax, String Trimming,String JIS, String certificate,String expectDate,String plantCode, String userName, String dateTime) throws Exception;
}
