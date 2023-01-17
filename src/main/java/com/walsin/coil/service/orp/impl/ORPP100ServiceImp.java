package com.walsin.coil.service.orp.impl;
import com.walsin.coil.common.utils.Classifier;
import com.walsin.coil.common.utils.dataQuery;
import com.walsin.coil.service.orp.ORPP100Service;
import com.walsin.coil.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tech.tablesaw.api.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * @author ur08366
 * @date 2022/12/15 下午 3:30
 */
@Primary
@Service
public class ORPP100ServiceImp  implements ORPP100Service {
    private static String oracle_order_sql = "select XXX.* , 訂單重 - nvl(鋼捲成品庫存, 0) \n" +
            "               - nvl(鋼捲半成品, 0) - nvl(扁胚掛單量, 0) \n" +
            "               - NVL(出貨量, 0) - NVL(備料訂單量, 0) - NVL(外購鋼捲備料, 0)  \n" +
            "               -  NVL(轉出不補量, 0) - NVL(撿貨未過帳, 0)  - NVL(生計訂單備料量, 0) 待軋量\n" +
            "        FROM (select distinct pdo.sale_order || '-' || pdo.sale_item 訂單號碼,\n" +
            "               pdo.DATE_DELIVERY_PP 生計交期,pdo.sale_order_weight 訂單重,PDO.DATE_DELIVERY_SALES 訂單交期,PDO.CYCLE_NO CYCLE_NO,       \n" +
            "               DECODE(SUBSTR(pdo.MTRL_NO,1,4),'YP12','No.1','YP13','No.1','YP44','2D','YP46','2D','YP45','2B','YP4G','2B','YP4C','2E','YP4D','2E','YP4K','2E','YP4L','2E','YP1K','HRB','   ') 產品分類,\n" +
            "               decode (PDO.CUST_ABBREVIATIONS, '備料庫存', PDO.CONSIGNEE, PDO.CUST_ABBREVIATIONS) 客戶,recipe_table.PP_SHOW_LINEUP_DESC 製程 ,\n" +
            "               pdo.MTRL_NO 料號,\n" +
            "               PDO.GRADE_NO 鋼種,\n" +
            "               PDO.sale_order_thick 訂單厚度,PDO.SALE_ORDER_WIDTH 訂單寬度,\n" +
            "               pdo.MIC_NO MIC_NO,nvl(pdo.JIS_MARK, 'N') JIS_MARK,pdo.SALE_ORDER_WIDTH 寬度,\n" +
            "               pdo.THICK_MAX 厚度上限,pdo.THICK_MIN 厚度下限,recipe_table.hot_rolled_thick 熱軋厚度,pdo.max_weight_of_each_prod 單重上限,\n" +
            "               pdo.min_weight_of_each_prod 單重下限,\n" +
            "               recipe_table.FLAG_CERTIFICATE_ORIGIN 產證,recipe_table.FLAG_BIS BIS認證,recipe_table.CUST_SPECIAL 訂單特殊需求,\n" +
            "               nvl(PDO.picked_not_shipping_weight, 0) 撿貨未過帳,nvl(PDO.WEIGHT_NONREPLENISH,0) 轉出不補量,nvl(PDO.shipping_weight, 0)  扁胚掛單量, nvl(PDO.current_weight_finish_good, 0)  鋼捲成品庫存,\n" +
            "                nvl((select  sum(s.weight) wei from sfc001cur01 s, WIP101CUR02 W\n" +
            "                 where substr(s.mic_no, 1, 1) = 'P' and substr(s.sale_order, 1, 1) <> 'P'\n" +
            "                       and ((s.final_mic_no <> mic_no) or (s.final_mic_no = mic_no and S.SHOP_CODE <> '801'))\n" +
            "                       AND S.ID_NO = W.ID_NO and s.sale_order = pdo.sale_order and s.sale_item = pdo.sale_item\n" +
            "                ), 0) 鋼捲半成品,                       \n" +
            "                nvl((select sum(s.weight) wei from sfc001cur01 s\n" +
            "                 where substr(s.mic_no, 1, 1) = 'N' and substr(s.sale_order, 1, 1) <> 'P'\n" +
            "                        and s.sale_order = pdo.sale_order and s.sale_item = pdo.sale_item), 0) 出貨量,\n" +
            "                 nvl((select  sum(s.weight) wei from sfc001cur01 s, WIP101CUR02 W\n" +
            "                  where S.DATE_UPDATE >= SYSDATE - 100 and s.id_no = w.id_no(+)  AND substr(s.sale_order, 1, 1) <> 'P'\n" +
            "                        AND S.STOCK_STATUS <> 'OUT' and w.house is null\n" +
            "                        and s.sale_order = pdo.sale_order and s.sale_item = pdo.sale_item),0) 外購鋼捲備料,\n" +
            "                  nvl((select  sum(s.weight) wei from sfc001cur01 s, WIP101CUR02 W\n" +
            "                   where s.id_no = w.id_no  AND substr(s.sale_order, 1, 1) <> 'P'\n" +
            "                         and s.sale_order = pdo.sale_order and s.sale_item = pdo.sale_item), 0) 備料訂單量 ,\n" +
            "                  nvl((select SUM(A.WEIGHT) X_WEI from (select sfc.WEIGHT, sfc.Id_no FROM SFC001CUR01 sfc, WIP101CUR02 wip\n" +
            "                                                    WHERE sfc.id_no = wip.id_no  AND SUBSTR(sfc.SALE_ORDER, 1, 2) = '0X') a,\n" +
            "                                                   MES.PRP420MAP01 P3\n" +
            "                   WHERE A.ID_NO = P3.ID_NO(+)  AND P3.PRELOAD_NO IS NOT NULL and SUBSTR(P3.PRELOAD_NO, 1, 8) = pdo.sale_order\n" +
            "                        and SUBSTR(P3.PRELOAD_NO, 10, 2) = pdo.sale_item),0) 生計訂單備料量                              \n" +
            "        FROM  ssma01mst01 pdo,ssm401dtl01 recipe_table ,PRP201MST02 prp\n" +
            "        WHERE substr(pdo.MIC_NO,1,1)='P' \n" +
            "              and PDO.SALE_ORDER = prp.SALE_ORDER(+) \n" +
            "              and PDO.SALE_ITEM = prp.SALE_ITEM(+)\n" +
            "              AND pdo.sale_order = recipe_table.sale_order(+) \n" +
            "              and pdo.sale_item = recipe_table.sale_item(+)\n" +
            "             and nvl(recipe_table.PP_FLAG_CLOSED,'N') = 'N' \n" +
            "              ) XXX order by 訂單號碼";
    private static String oracle_wip_coil_sql = "(select '廠內' \"類別\",a.id_no,\n" +
            "       a.sap_mtrl_no \"現況料號\",a.mic_no \"MIC\",\n" +
            "       substr(sfc_get.get_bar_lineup(a.final_mic_no, '1') || '(' || SUBSTR(sfc_get.get_bar_lineup(a.final_mic_no, a.mic_no),1,20) || ')', 1, 60) \"流程說明\",\n" +
            "       a.thickness \"厚度\",a.width \"寬度\",a.length \"長度\", a.actual_thickness \"實際厚度\",a.actual_width \"實際寬度\", a.actual_length \"實際長度\",\n" +
            "       a.weight \"重量\",a.date_update \"最後異動日\",\n" +
            "       NVL(c.shop_code,  a.shop_code) \"站號\", b.loc \"儲區\",a.stock_status \"庫存狀態\",c.shop_code \"台中站別\",d.loc \"台中儲區\",e.FLAG_MIT MIT,\n" +
            "       replace(f.plate_remark, chr('10'), '') \"鋼板註記\",\n" +
            "       a.quality_note||c.QUALITY_NOTE  \"品質註記說明\",     \n" +
            "       (select decode(slab_unusual_note||slab_unusual_remark,null,null,decode(slab_unusual_remark,null,slab_unusual_note||'-請管制出貨NO.1',\n" +
            "                      slab_unusual_note||' '||slab_unusual_remark))\n" +
            "        from sfc100mst01  where id_no = a.id_no)  \"扁胚異常註記說明\"\n" +
            "  from sfc001cur01 a ,wip101cur02 b ,sfc001cur01_tc c ,wip101cur02_tc d,PRP001CUR01 e ,prp420map01 f\n" +
            "  where a.ID_NO = b.ID_NO  and  SUBSTR(A.MIC_NO,1,1) = 'P' and SUBSTR(NVL(a.SALE_ORDER,'P'),1,1)= 'P'  \n" +
            "  and a.ID_NO = c.ID_NO(+) and c.ID_NO = d.ID_NO(+) and a.id_no=e.id_no(+) and a.id_no = f.id_no(+) \n" +
            "  and ((b.loc<> 'P500') or  (b.loc= 'P500' and NVL(c.shop_code,  a.shop_code) = '609')))\n" +
            "union all\n" +
            "(select '在途' \"類別\",a.ID_NO ,\n" +
            "       a.sap_mtrl_no \"現況料號\",a.mic_no \"MIC\",\n" +
            "       substr(sfc_get.get_bar_lineup(a.final_mic_no, '1') || '(' || SUBSTR(sfc_get.get_bar_lineup(a.final_mic_no, a.mic_no),1,20) || ')', 1, 60) \"流程說明\",\n" +
            "       a.thickness \"厚度\",a.width \"寬度\",a.length \"長度\", a.actual_thickness \"實際厚度\",a.actual_width \"實際寬度\", a.actual_length \"實際長度\",\n" +
            "       a.weight \"重量\",a.date_update \"最後異動日\",\n" +
            "       null \"站號\", null \"儲區\",null \"庫存狀態\",null \"台中站別\",null \"台中儲區\",null MIT,\n" +
            "     null \"鋼板註記\",     null  \"品質註記說明\", null \"扁胚異常註記說明\"\n" +
            "from sfc001cur01 a ,prp421mst01@tcdbp1_prp b\n" +
            "where a.ID_NO = b.ID_NO and b.ID_STATUS = '待提貨' and SUBSTR(NVL(a.SALE_ORDER,'P'),1,1)= 'P')";
    @Override
    public Table orderMatching(String customer,String gradeNo,String product,Integer width,Float weightMin,Float weightMax,Float thicknessMin,Float thicknessMax,Float materialThicknessMin,Float materialThicknessMax,String Trimming,String JIS, String certificate,String expectDate,String plantCode, String userName, String dateTime) throws Exception { // 撈庫存資料(MES)
        // 建立新訂單
        // 建立新訂單Table
        if(customer == null)
        {
            customer="";
        }
        if(gradeNo == null)
        {
            gradeNo="";
        }
        if(product == null)
        {
            product="";
        }
        if(width == null)
        {
            width=0;
        }
        if(weightMin == null)
        {
            weightMin=0f;
        }
        if(weightMax == null)
        {
            weightMax=0f;
        }
        if(thicknessMin == null)
        {
            thicknessMin=0f;
        }
        if(thicknessMax == null)
        {
            thicknessMax=0f;
        }
        if(materialThicknessMin == null)
        {
            materialThicknessMin=0f;
        }
        if(materialThicknessMax == null)
        {
            materialThicknessMax=0f;
        }
        if(Trimming == null)
        {
            Trimming="";
        }
        if(JIS == null)
        {
            JIS="";
        }
        if(certificate == null)
        {
            certificate="";
        }
        if(expectDate == null)
        {
            expectDate="";
        }
        if(plantCode == null)
        {
            plantCode="";
        }
        if(userName == null)
        {
            userName="";
        }
        if(dateTime == null)
        {
            dateTime="";
        }
        Table order_data;
        {
            String[] customer_col = {customer};
            String[] gradeNo_col = {gradeNo};
            String[] product_col = {product};
            Integer[] width_col = {width};
            Float[] weightMin_col = {weightMin};
            Float[] weightMax_col = {weightMax};
            Float[] thicknessMin_col = {thicknessMin};
            Float[] thicknessMax_col = {thicknessMax};
            Float[] materialThicknessMin_col = {materialThicknessMin};
            Float[] materialThicknessMax_col = {materialThicknessMax};
            String[] Trimming_col = {Trimming};
            String[] JIS_col = {JIS};
            String[] certificate_col = {certificate};
            String[] expectDate_col = {expectDate};
            String[] plantCode_col = {plantCode};
            String[] userName_col = {userName};
            String[] dateTime_col = {dateTime};
            StringColumn customer_nc = StringColumn.create("客戶", customer_col);
            StringColumn gradeNo_nc = StringColumn.create("鋼種", gradeNo_col);
            StringColumn product_nc = StringColumn.create("產品分類", product_col);
            IntColumn width_nc = IntColumn.create("寬度", width_col);
            FloatColumn weightMin_nc = FloatColumn.create("單重下限", weightMin_col);
            FloatColumn weightMax_nc = FloatColumn.create("單重上限", weightMax_col);
            FloatColumn thicknessMin_nc = FloatColumn.create("厚度下限", thicknessMin_col);
            FloatColumn thicknessMax_nc = FloatColumn.create("厚度上限", thicknessMax_col);
            FloatColumn materialThicknessMin_nc = FloatColumn.create("materialThicknessMin", materialThicknessMin_col);
            FloatColumn materialThicknessMax_nc = FloatColumn.create("materialThicknessMax", materialThicknessMax_col);
            StringColumn Trimming_nc = StringColumn.create("修邊", Trimming_col);
            StringColumn JIS_nc = StringColumn.create("JIS_MARK", JIS_col);
            StringColumn certificate_nc = StringColumn.create("產證", certificate_col);
            StringColumn expectDate_nc = StringColumn.create("expectDate", expectDate_col);
            StringColumn plantCode_nc = StringColumn.create("plantCode", plantCode_col);
            StringColumn userName_nc = StringColumn.create("userName", userName_col);
            StringColumn dateTime_nc = StringColumn.create("dateTime", dateTime_col);
            order_data = Table.create("name", customer_nc, gradeNo_nc, product_nc, width_nc,weightMin_nc,weightMax_nc, thicknessMin_nc,thicknessMax_nc,materialThicknessMin_nc,materialThicknessMax_nc,Trimming_nc,JIS_nc,certificate_nc,expectDate_nc,plantCode_nc,userName_nc,dateTime_nc);
        }
        // 訂單資料建立新欄位"待軋量"
        // 訂單資料建立新欄位"訂單號碼"
        Double predefine_unroll_weight = -9999999d;
        String predefine_sale_order="特殊訂單號碼";
        {
            DoubleColumn nc1 = DoubleColumn.create("待軋量", order_data.rowCount());
            StringColumn nc2 = StringColumn.create("訂單號碼", order_data.rowCount());
            int idx = 0;
            for(Row row: order_data){
                nc1.set(idx, predefine_unroll_weight);
                nc2.set(idx, predefine_sale_order);
                idx++;
            }
            order_data.addColumns(nc1);
            order_data.addColumns(nc2);
        }

        // 新增訂單
        {
            dataQuery mysql_db = new dataQuery("10.106.2.154","ppsdb","pps","ppsDEV13579","3306","mysql");
            String insert_order_sql = "INSERT INTO tborpm100 (customer, gradeNo, product, width, weightMin, weightMax, thicknessMin, thicknessMax, materialThicknessMin, " +
                    "materialThicknessMax, Trimming, JIS_MARK, certificate, expectDate, plantCode, userName, update_dateTime, unroll_weight, sale_order) VALUES " + "("
                    + String.format("\"%s\", \"%s\",\"%s\", %d, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f,\"%s\",\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", %f, \"%s\");",customer, gradeNo, product, width, weightMin, weightMax, thicknessMin, thicknessMax, materialThicknessMin,materialThicknessMax, Trimming, JIS, certificate, expectDate, plantCode, userName, dateTime, predefine_unroll_weight, predefine_sale_order);
            System.out.println(insert_order_sql);
            try{
                mysql_db.exeSql(insert_order_sql,"",true);
            }catch (Exception e){
                System.out.println("重複訂單");
            }
        }
        // 撈訂單資料(MES)
        // dataQuery db_order_oracle = new dataQuery("ysdbp1.walsin.corp","ysdbp1","prp","prpmgr","1521","oracle");
        // Table order_data = db_order_oracle.queryTable(oracle_order_sql, "order_data");
        // 一些訂單不要
        // order_data = order_data.dropWhere(order_data.stringColumn("訂單號碼").containsString("0X"));
        // order_data = order_data.dropWhere(order_data.stringColumn("訂單號碼").containsString("0Z"));
        // order_data = order_data.dropWhere(order_data.stringColumn("CYCLE_NO").containsString("x"));
        // order_data = order_data.dropWhere(order_data.stringColumn("CYCLE_NO").isEqualTo(""));
        dataQuery db_coil_oracle = new dataQuery("ysdbp1.walsin.corp","ysdbp1","prp","prpmgr","1521","oracle");
        Table inventory  = db_coil_oracle.queryTable(oracle_wip_coil_sql,"inventory");
//        inventory = inventory.dropWhere(inventory.stringColumn("類別").isEqualTo("廠內"));
//        inventory = inventory.dropWhere(inventory.stringColumn("類別").isEqualTo("在途"));
//        System.out.println(inventory.selectColumns("儲區","台中站別","台中儲區"));
        inventory = inventory.dropWhere(inventory.stringColumn("台中站別").isNotEqualTo("609"));
        {
            StringColumn customer_nc = StringColumn.create("客戶", inventory.rowCount());
            StringColumn product_nc = StringColumn.create("product", inventory.rowCount());
            StringColumn quality_nc = StringColumn.create("quality", inventory.rowCount());
            int index = 0;
            for (Row row : inventory) {
                row.setString("類別","原料"); //更改內容
                customer_nc.set(index, customer); //庫存資料建立新欄位"客戶"
                product_nc.set(index, product);// 庫存資料建立新欄位"product"
                quality_nc.set(index, row.getString("MIC"));// 庫存資料建立新欄位"quality"
                index++;
            }
            inventory.addColumns(customer_nc);
            inventory.addColumns(product_nc);
            inventory.addColumns(quality_nc);
        }
        // 庫存資料建立新欄位"廠內鋼種"
        {
            StringColumn nc = StringColumn.create("廠內鋼種", inventory.rowCount());
            int index = 0;
            for (Row row : inventory) {
                String s = row.getString("MIC");
                nc.set(index, s.substring(2,8));
                index++;
            }
            inventory.addColumns(nc);
        }
        // 庫存資料建立新欄位"wipTime" 在庫時間
        {
            DoubleColumn nc = DoubleColumn.create("wipTime", inventory.rowCount());
            Instant instant_date_time = Instant.now();
            Integer idx = 0;
            for(Row row: inventory){
                //LocalDateTime dt = row.getDateTime("最後異動日");
                Instant dt = row.getInstant("最後異動日");
                Duration dur = Duration.between(dt,instant_date_time);
                nc.set(idx, dur.toDays());
                idx++;
            }
            inventory.addColumns(nc);
        }
        // 庫存資料建立新欄位"DATE_PLAN_IN_STORAGE"，(預計)入庫日
        {
            StringColumn nc = StringColumn.create("DATE_PLAN_IN_STORAGE", inventory.rowCount());
            Integer idx = 0;
            for(Row row: inventory){
                nc.set(idx, "");
                idx++;
            }
            inventory.addColumns(nc);
        }
        // 庫存資料建立新欄位"DATE_DELIVERY_PP"，建議交期
        {
            StringColumn nc = StringColumn.create("DATE_DELIVERY_PP", inventory.rowCount());
            Integer idx = 0;
            for(Row row: inventory){
                nc.set(idx, "");
                idx++;
            }
            inventory.addColumns(nc);
        }
        // 庫存資料建立新欄位"訂單號碼"
        {
            StringColumn nc = StringColumn.create("訂單號碼", inventory.rowCount());
            Integer idx = 0;
            for(Row row: inventory){
                nc.set(idx, null);
                idx++;
            }
            inventory.addColumns(nc);
        }
        // 庫存資料建立新欄位"PURCHASE_FLAG"
        {
            String english="[a-za-z]"; // 正則篩選用
            StringColumn nc = StringColumn.create("PURCHASE_FLAG", inventory.rowCount());
            Integer idx = 0;
            for(Row row: inventory){
                if (row.getString("ID_NO").substring(0).matches(english)){
                    nc.set(idx, "Y");
                }else {
                    nc.set(idx, "N");
                }
                idx++;
            }
            inventory.addColumns(nc);
        }
        // 庫存資料建立新欄位"SELECT"
        {
            StringColumn nc = StringColumn.create("SELECT", inventory.rowCount());
            Integer idx = 0;
            for(Row row: inventory){
                nc.set(idx, "N");
                idx++;
            }
            inventory.addColumns(nc);
        }

        // 呼叫分類邏輯
        Classifier classifier = new Classifier();
        //最終結果鋼捲資料儲存用
        Table result_coil_data = inventory.first(1).copy();;
        result_coil_data = result_coil_data.dropRows(0);
        System.out.println("訂單匹配前整體庫存數量: " + inventory.rowCount());
        //咬料配單程式區域
        for(Row order_row : order_data)
        {
            Table filted_inventory = null; // 單筆訂單的可用鋼捲庫存
            Double unroll_weight = order_row.getDouble("待軋量");
            String sale_order = order_row.getString("訂單號碼");
            System.out.println("======" + "處理訂單: "+ sale_order + "======");
            System.out.println("======" + "初始待軋量: "+ unroll_weight + "======");
            System.out.println("======" + "訂單: \n"+ order_row + "======");
            System.out.println("JIS篩選");
            // JIS
            filted_inventory = classifier.filterJis(order_row, inventory);
            if (filted_inventory.isEmpty()){
                System.out.println("JIS沒有合適鋼捲");
                continue;
            }
            System.out.println("JIS篩選後可配鋼捲: "+ filted_inventory.rowCount());
            System.out.println("單重上下限篩選");
            // 單重上下限
            filted_inventory = classifier.filterWeight(order_row, filted_inventory);
            if (filted_inventory.isEmpty()){
                System.out.println("單重上下限沒有合適鋼捲");
                continue;
            }
            System.out.println("單重上下限篩選後可配鋼捲: "+ filted_inventory.rowCount());
            System.out.println("產證篩選");
            // 產證
            filted_inventory = classifier.filterMit(order_row, filted_inventory);
            if (filted_inventory.isEmpty()){
                System.out.println("產證沒有合適鋼捲");
                continue;
            }
            System.out.println("產證篩選後可配鋼捲: "+ filted_inventory.rowCount());
            System.out.println("寬度篩選");
            // 訂單寬度篩選庫存鋼捲寬度
            filted_inventory = classifier.filterWidth(order_row, filted_inventory);
            if (filted_inventory.isEmpty()){
                System.out.println("寬度沒有合適鋼捲");
                continue;
            }
            System.out.println("寬度篩選後可配鋼捲: "+ filted_inventory.rowCount());
            //訂單厚度篩選庫存鋼捲寬度
            System.out.println("厚度篩選");
            filted_inventory = classifier.filterThick(order_row, filted_inventory);
            if (filted_inventory.isEmpty()){
                System.out.println("厚度沒有合適鋼捲");
                continue;
            }
            System.out.println("厚度篩選後可配鋼捲: "+ filted_inventory.rowCount());
            // 鋼種篩選
            System.out.println("鋼種篩選");
            filted_inventory = classifier.filterGradeNo(order_row, filted_inventory);
            if (filted_inventory.isEmpty()){
                System.out.println("鋼種沒有合適鋼捲");
                continue;
            }
            System.out.println("鋼種篩選後可配鋼捲: "+ filted_inventory.rowCount());

            //單筆訂單配料結果處理
            filted_inventory = filted_inventory.sortAscendingOn("重量"); //因為要先挑重量較小的鋼捲
            System.out.println("單筆訂單最終可配鋼捲: "+ filted_inventory.rowCount());
            //將符合條件之鋼捲標上所屬訂單後放入結果table
            for(Row filted_row : filted_inventory){
                unroll_weight += filted_row.getInt("重量");
                filted_row.setString("訂單號碼",sale_order);
                if (unroll_weight <= 0){
                    filted_row.setString("SELECT","Y");
                }
                result_coil_data.append(filted_row);// 將成功標記之鋼捲數據加到結果之中
                // 去除重複
                // inventory = inventory.dropWhere(inventory.stringColumn("ID_NO").containsString(filted_row.getString("ID_NO")));
                // System.out.println("待軋量: "+ unroll_weight);
                // if (unroll_weight<=0){
                // break;}
            }
//            break;
        }
        //選擇欄位
        result_coil_data = result_coil_data.selectColumns("客戶", "類別", "product", "ID_NO", "廠內鋼種", "寬度", "厚度", "quality", "重量", "wipTime", "DATE_PLAN_IN_STORAGE", "DATE_DELIVERY_PP", "鋼板註記");
        System.out.println("訂單匹配後整體庫存數量: " + inventory.rowCount());
        System.out.println("整體配單數量: " + result_coil_data.rowCount());
        // 改庫存資料欄位名稱
        {
            String[] odl_col_names = {"客戶", "類別", "廠內鋼種", "寬度", "厚度", "重量", "鋼板註記"};
            String[] new_col_names = {"customer", "productKind", "gradeNo", "width", "thickness", "weight","memo"};
            for(int idx=0; idx < new_col_names.length; idx++)
            {
                    result_coil_data.column(odl_col_names[idx]).setName(new_col_names[idx]);
            }
        }
        //最終存檔
//        result_coil_data.write().csv("result_coil_data.csv");
        System.out.println(result_coil_data.printAll());


        return result_coil_data;
    }
}
