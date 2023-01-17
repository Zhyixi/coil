package com.walsin.coil.common.utils;

import tech.tablesaw.api.Table;

import java.sql.*;
public class dataQuery {
    //目前不知道怎麼處理的數據都強制轉換成String 包含時間與空值，日後有時間再優化
    private String URL;
    private final String host;
    private final String db_info;
    private final String port;
    private final String USER;
    private final String PASSWORD;
    private String db_type = "mysql";
    private String[] columns;


    public Table DataFrame = null;

    public dataQuery(String host,String db_info, String USER, String PASSWORD, String port){
        this.USER = USER;
        this.db_info = db_info;
        this.port = port;
        this.PASSWORD = PASSWORD;
        this.host = host;
        init_url();
    }
    public dataQuery(String host,String db_info, String USER, String PASSWORD, String port, String db_type){
        this.USER = USER;
        this.db_info = db_info;
        this.port = port;
        this.PASSWORD = PASSWORD;
        this.host = host;
        this.db_type = db_type;
        init_url();
    }

    private void init_url(){
        if(this.db_type == "oracle"){
            this.URL = "jdbc:"+this.db_type+":thin:@" + this.host+":"+this.port+":"+this.db_info; //10.106.2.154:3306/ppsdb
        }else {
            this.URL = "jdbc:"+this.db_type+"://" + this.host+"/"+this.db_info; //10.106.2.154:3306/ppsdb
        }
    }


    public Table queryTable(String sql,String table_name) throws Exception{
        //1.加载驱动程序
        if (this.db_type == "oracle"){
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }else{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        //2. 获得数据库连接
        Connection conn;
        try {
            conn = DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
        }catch (java.sql.SQLException e) {
            throw new Exception("db_info不存在");
        }
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery(sql);
        this.DataFrame = Table.read().db(rs, table_name);
        return this.DataFrame;
    }

    public void exeSql(String sql,String table_name, boolean replace) throws Exception{
        //1.加载驱动程序
        if (this.db_type == "oracle"){
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }else{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        //2. 获得数据库连接
        Connection conn;
        try {
            conn = DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
        }catch (java.sql.SQLException e) {
            throw new Exception("db_info不存在");
        }
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        try {
            stmt.execute(sql);
        }catch (java.sql.SQLException e){
            if(replace){
                stmt.execute("DROP TABLE "+ table_name+ ";");
                stmt.execute(sql);
            }
        }
    }
}
