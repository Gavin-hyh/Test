package com.hyh.utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ShowDateNameUtils {

    /**
     * 链接jdbc
     * @return
     */
    public static Connection getConnection(Map dataSourceMap){

        String driver = (String) dataSourceMap.get("driver");
        String url = (String) dataSourceMap.get("url");
        String username = (String) dataSourceMap.get("username");
        String pass = (String) dataSourceMap.get("pass");

        Connection connection=null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,pass);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }


/*    public static String ShowName(Map dataSourceMap) throws SQLException {
        Connection connection = getConnection(dataSourceMap);
        PreparedStatement prepareStatement = connection.prepareStatement("SHOW DATABASES");
        ResultSet executeQuery = prepareStatement.executeQuery();
        ResultSetMetaData metaData = executeQuery.getMetaData();
        int columnCount = metaData.getColumnCount();
        String SHOWDATENAME = null;
        executeQuery.get
        return SHOWDATENAME;
    }*/

    public static void main(String[] args) {
        Map map=new HashMap();
        map.put("driver", "com.mysql.jdbc.Driver");
        map.put("url", "jdbc:mysql://127.0.0.1:3306/seckill?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true");
        map.put("username", "root");
        map.put("pass", "root");
/*        try {
            String s = ShowName(map);
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
    /**
     * jdbc关流
     * @param ex
     * @param pr
     * @param co
     */
    public static void close(ResultSet ex, PreparedStatement pr, Connection co){
        try {
            if (ex!=null) ex.close();
            if (pr!=null) pr.close();
            if (co!=null) co.close();


        } catch (Exception e2) {
            // TODO: handle exception
        }

    }

}

