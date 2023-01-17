package com.walsin.coil.common.utils;
import tech.tablesaw.api.*;
import java.time.Instant;
import org.apache.commons.math3.util.Precision;

public class Classifier {
    //tborpm029 客戶鋼種廠內鋼種對應表(翻譯用
    private Table tborpm029 = null;
    //tborpm030 訂單鋼種原料對照表(鋼種邏輯)
    private Table tborpm030 = null;
    //tborpm031 訂單寬度原料對照表(寬度邏輯)
    private Table tborpm031 = null;
    //tborpm032 訂單厚度原料對照表(厚度邏輯)
    private Table tborpm032 = null;
    private boolean check_mode = false;

    public Classifier() throws Exception {
        // 撈"訂單寬度原料對照表"
        dataQuery mysql_db = new dataQuery("10.106.2.154","ppsdb","pps","ppsDEV13579","3306","mysql");
        //tborpm029 客戶鋼種廠內鋼種對應表(翻譯用
        this.tborpm029 = mysql_db.queryTable("SELECT PLANT_CODE, PRODUCT_TYPE, CUST_GRADE_NO, SALE_ORDER_GRADE_NO FROM tborpm029", "tborpm029"); //
        this.tborpm029 = this.tborpm029.sortOn("-PLANT_CODE"); //排序(presently only can be able ordered by one column)
        //tborpm030 訂單鋼種原料對照表(鋼種邏輯)
        this.tborpm030 = mysql_db.queryTable("SELECT PLANT_CODE, SALE_ORDER_GRADE_NO,MATERIAL_GRADE_NO, PURCHASE_FLAG, OPTION_SEQ FROM tborpm030", "tborpm030"); //
        //tborpm031 訂單寬度原料對照表(寬度邏輯)
        this.tborpm031 = mysql_db.queryTable("SELECT PLANT_CODE, SALE_ORDER_WIDTH_MIN, SALE_ORDER_WIDTH_MAX, PROCESS_TYPE , MATERIAL_WIDTH_MIN, MATERIAL_WIDTH_MAX, TOLERATE_MIN, TOLERATE_MAX FROM tborpm031","tborpm031"); //
        //tborpm032 訂單厚度原料對照表(厚度邏輯)
        this.tborpm032 =mysql_db.queryTable("SELECT PLANT_CODE, PRODUCT_TYPE, ROUND(THICKNESS_MIN,2) THICKNESS_MIN, ROUND(THICKNESS_MAX,2) THICKNESS_MAX, INPUT_TYPE , OPTION_SEQ, MATERIAL_THICKNESS_MIN, MATERIAL_THICKNESS_MAX FROM tborpm032", "tborpm032");
        // db_cus_grade_no_mysql.exeSql("CREATE TABLE "+"tborpm100"+" (\n" +
        //"    column1 varchar(3),\n" +
        //"    column2 varchar(3),\n" +
        //"    column3 varchar(3));", "tborpm100",true);
    }

    public Table filterWidth(Row input_order_row, Table inventory) throws Exception {
        inventory = inventory.copy();
        Integer order_width = input_order_row.getInt("寬度"); //訂單寬度
        Table new_tb = inventory.first(1).copy();
        new_tb = new_tb.dropRows(0);
        Float material_width_min=null;
        Float material_width_max=null;
        //查寬度表找出庫存原料寬度範圍
        Table tborpm031 = this.tborpm031.copy();
        tborpm031 = tborpm031.dropWhere(tborpm031.floatColumn("SALE_ORDER_WIDTH_MIN").isGreaterThan(order_width));
        tborpm031 = tborpm031.dropWhere(tborpm031.floatColumn("SALE_ORDER_WIDTH_MAX").isLessThan(order_width));
        // 判斷是否修邊
        if (input_order_row.getString("修邊").contains("Y")){
            tborpm031 = tborpm031.dropWhere(tborpm031.stringColumn("PROCESS_TYPE").isNotEqualTo("修邊"));
        }else if (input_order_row.getString("修邊").contains("N")) {
            tborpm031 = tborpm031.dropWhere(tborpm031.stringColumn("PROCESS_TYPE").isNotEqualTo("毛邊"));
        }else {
        }

        for(Row width_table_row: tborpm031)
        {
            material_width_min = width_table_row.getFloat("MATERIAL_WIDTH_MIN");
            material_width_max = width_table_row.getFloat("MATERIAL_WIDTH_MAX");
            // 檢查庫存
            for(Row coil_row: inventory)
            {
                if((coil_row.getFloat("寬度") <= material_width_max) && (coil_row.getFloat("寬度") >= material_width_min))
                {
                    new_tb.append(coil_row);
                    //剔除重複值
                    inventory = inventory.dropWhere(inventory.stringColumn("ID_NO").containsString(coil_row.getString("ID_NO")));
                }
            }
        }
        // 檢查庫存
        return new_tb; //返回符合結果之庫存
    }

    public Table filterThick(Row input_order_row, Table inventory) throws Exception
    {
        inventory = inventory.copy();
        String jis_mark = input_order_row.getString("JIS_MARK"); //JIS_MARK
        Float order_thick;
        if((input_order_row.getString("產品分類").equals(""))||(input_order_row.getFloat("厚度上限")==0f)||(input_order_row.getFloat("厚度下限")==0f))
        {
            return inventory;
        }
        if (input_order_row.getString("產品分類").equals("No.1")){
            order_thick = (input_order_row.getFloat("厚度上限") + input_order_row.getFloat("厚度下限")) / 2;
        }else{
            order_thick = input_order_row.getFloat("厚度下限");
        }
        Float material_thick_min=null;
        Float material_thick_max=null;
        Table new_tb = inventory.copy().first(1);
        new_tb = new_tb.dropRows(0);
        // 查厚度表找出庫存原料厚度範圍
        Table tborpm032 = this.tborpm032.copy();
        tborpm032 = tborpm032.dropWhere(tborpm032.stringColumn("PRODUCT_TYPE").isNotEqualTo(input_order_row.getString("產品分類")));

        Table tborpm032_copy = tborpm032.first(1).copy();
        tborpm032_copy = tborpm032_copy.dropRows(0);
        //初步篩選
        //如果訂單要求JIS的話
        if(jis_mark.equals("Y")){
            tborpm032 = tborpm032.dropWhere(tborpm032.stringColumn("INPUT_TYPE").isNotEqualTo("Y"));
        } else if (jis_mark.equals("N")) {
            tborpm032 = tborpm032.dropWhere(tborpm032.stringColumn("INPUT_TYPE").isNotEqualTo("N"));
        }

        for(Row row: tborpm032)
        {
            //訂單製程 process_code
            //訂單鋼種 grade_no
            //訂單 jis_mark
            Float thick_min = Precision.round(row.getFloat("THICKNESS_MIN"),2);
            Float thick_max = Precision.round(row.getFloat("THICKNESS_MAX"),2);
            if((thick_min <= order_thick) && (thick_max >= order_thick))
            {
                material_thick_min = row.getFloat("MATERIAL_THICKNESS_MIN");
                material_thick_max = row.getFloat("MATERIAL_THICKNESS_MAX");
                // 檢查庫存
                for(Row coil_row: inventory)
                {
                    if((coil_row.getFloat("厚度") <= material_thick_max) && (coil_row.getFloat("厚度") >= material_thick_min))
                    {
                        new_tb.append(coil_row);
                        //剔除重複值
                        inventory = inventory.dropWhere(inventory.stringColumn("ID_NO").containsString(coil_row.getString("ID_NO")));
                    }
                }
            }
        }
        return new_tb; //返回符合結果之庫存
    }

    public Table filterJis(Row input_order_row, Table inventory) throws Exception {
        String jis_mark = input_order_row.getString("JIS_MARK"); //JIS_MARK
        String english="[a-za-z]";
        Table new_tb = inventory.first(1).copy();
        new_tb = new_tb.dropRows(0);
//        現況料號YP=鋼捲，YN=扁胚
//        ID_NO前一字是字母表示
        // 檢查庫存
        if(jis_mark.equals("Y"))
        {
            for(String targe_s: new String[]{"YP","YN"})
            {
                for(Row coil_row: inventory)
                {
                    if (coil_row.getString("ID_NO").substring(0).matches(english))
                    {
                        //目前邏輯只使用外購
                        if(coil_row.getString("現況料號").matches(targe_s))
                        {
                            //鋼捲優先
                            new_tb.append(coil_row);
                        }
                    }
                    else
                    {
                        //非外購
                    }
                }
            }
        }
        else{
            new_tb = inventory.copy();
        }
        return new_tb; //返回符合結果之庫存
    }
    //MIT篩選
    public Table filterMit(Row input_order_row, Table inventory) throws Exception {
        String MIT = input_order_row.getString("產證"); //產證
        Table new_tb = inventory.first(1).copy();
        new_tb = new_tb.dropRows(0);
        // 檢查庫存
        if (MIT.equals("Y")){
            new_tb = inventory.dropWhere(inventory.stringColumn("MIT").isNotEqualTo("Y"));
        } else if (MIT.equals("N")) {
            new_tb = inventory.dropWhere(inventory.stringColumn("MIT").isNotEqualTo("N"));
        } else{
            new_tb = inventory.copy();
        }
        return new_tb; //返回符合結果之庫存
    }

    //單重上下限篩選
    public Table filterWeight(Row input_order_row, Table inventory) throws Exception{
        try
        {
            input_order_row.getFloat("單重上限");
            input_order_row.getFloat("單重下限");
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.exit(11111);
            return inventory;
        }
        Float unit_weight_max = input_order_row.getFloat("單重上限"); //單重上限
        Float unit_weight_min = input_order_row.getFloat("單重下限"); //單重下限
        if(unit_weight_max.equals(0f)){
            return inventory;
        }
        if(unit_weight_min.equals(0f)){
            return inventory;
        }
        Table new_tb = inventory.first(1).copy();
        new_tb = new_tb.dropRows(0);
        while(new_tb.isEmpty())
        {
            new_tb = inventory.copy().dropWhere(inventory.intColumn("重量").isBetweenInclusive((double)unit_weight_min,(double)unit_weight_max));
            if (new_tb.isEmpty())
            {
                unit_weight_min = unit_weight_min * 2;
                unit_weight_max = unit_weight_max * 2;
            }
            if(unit_weight_min > inventory.intColumn("重量").max())
            {
                break;
            }
        }
        return new_tb; //返回符合結果之庫存
    }

    //鋼種篩選
    public Table filterGradeNo(Row input_order_row, Table inventory) throws Exception {
        inventory = inventory.copy();
        String order_grade_no = input_order_row.getString("鋼種"); //鋼種
        String order_product = input_order_row.getString("產品分類"); //產品分類
        System.out.println(order_grade_no);
        if(order_grade_no.equals("")||(order_product.equals(""))){
            return inventory;
        }else {
            Table tborpm029_copy = this.tborpm029.copy();
            tborpm029_copy = tborpm029_copy.dropWhere(tborpm029_copy.stringColumn("PRODUCT_TYPE").isNotEqualTo(order_product));
            tborpm029_copy = tborpm029_copy.dropWhere(tborpm029_copy.stringColumn("CUST_GRADE_NO").isNotEqualTo(order_grade_no));
            if(tborpm029_copy.rowCount()==1){
                for(Row row:tborpm029_copy){
                    order_grade_no = row.getString("SALE_ORDER_GRADE_NO");
                    break;
                }
            }
        }
        // 用來放結果
        Table new_tb = inventory.first(1).copy();
        new_tb = new_tb.dropRows(0);
        Table tborpm030 = this.tborpm030.copy();
        tborpm030 = tborpm030.dropWhere(tborpm030.stringColumn("SALE_ORDER_GRADE_NO").isNotEqualTo(order_grade_no)).copy();
        tborpm030.sortOn("OPTION_SEQ");
        for (Row grade_tb : tborpm030){
            for(Row coil_row : inventory){
//                System.out.println("庫存鋼種: " + coil_row.getString("鋼種"));
//                System.out.println("庫存PURCHASE_FLAG: " + coil_row.getString("PURCHASE_FLAG"));
//                System.out.println("鋼種表鋼種: " + grade_tb.getString("MATERIAL_GRADE_NO"));
//                System.out.println("鋼種表PURCHASE_FLAG: " + grade_tb.getString("PURCHASE_FLAG"));
                if ((coil_row.getString("廠內鋼種").equals(grade_tb.getString("MATERIAL_GRADE_NO"))) && ((coil_row.getString("PURCHASE_FLAG").equals(grade_tb.getString("PURCHASE_FLAG")))))
                {
                    new_tb.append(coil_row);
                    //剔除重複值
                    inventory = inventory.dropWhere(inventory.stringColumn("ID_NO").containsString(coil_row.getString("ID_NO")));
                }
            }
        }
        return new_tb; //返回符合結果之庫存
    }
}
