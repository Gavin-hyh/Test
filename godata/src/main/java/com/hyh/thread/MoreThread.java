package com.hyh.thread;


import java.sql.*;
import java.util.*;

import com.mysql.jdbc.Connection;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MoreThread implements Runnable{

    //源数据库信息
    private Map dataSourceMap = null;
    //目标数据库信息
    private Map dataTargetMap = null;
    //基本sql
    private static String SQL = "select * from ";

    private Map pagemap = null;


    public void run() {

    }

    /**
     * 链接jdbc
     * @return
     */
    public static  Connection getConnection(Map dataSourceMap){

        String driver = (String) dataSourceMap.get("driver");;
        String url = (String) dataSourceMap.get("url");
        String username = (String) dataSourceMap.get("username");
        String pass = (String) dataSourceMap.get("pass");
        Connection connection=null;
        try {
            Class.forName(driver);
            connection = (Connection) DriverManager.getConnection(url,username,pass);
            DatabaseMetaData data = connection.getMetaData();
            System.out.println(data.getDriverName().toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }




    //拼接insert语句
    public static List<String> checkSql(List<Map> list, String tableName){
        StringBuffer sbuf=new StringBuffer();

        List<String> sqllist =new ArrayList();
        for (Map map : list) {
            sbuf.append("INSERT INTO ");
            sbuf.append(tableName);
            sbuf.append("(");
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                sbuf.append(key);
                sbuf.append(",");
            }
            String newString = sbuf.substring(0, sbuf.length()-1);
            sbuf.setLength(0);
            sbuf.append(newString);
            sbuf.append(") values (");

            for (String key : keySet) {
                sbuf.append("'");
                sbuf.append(map.get(key));
                sbuf.append("'");
                sbuf.append(",");
            }
            String newStringVlues = sbuf.substring(0, sbuf.length()-1);
            sbuf.setLength(0);
            sbuf.append(newStringVlues);
            sbuf.append(")");
            sqllist.add(sbuf.toString());
            sbuf.setLength(0);
        }
        return sqllist;
    }


    //查询
    public static List<Map> queryData(Map dataSourceMap,String tableName) throws SQLException{
        Connection connection = getConnection(dataSourceMap);
        PreparedStatement prepareStatement = connection.prepareStatement(SQL+tableName);
        ResultSet executeQuery = prepareStatement.executeQuery();
        ResultSetMetaData metaData = executeQuery.getMetaData();
        int columnCount = metaData.getColumnCount();
        Map resultmap=null;

        List<Map> resullist=new ArrayList();
        while(executeQuery.next()){
            resultmap =new HashMap();
            for(int i = 1;i<=columnCount;i++){
                String key = metaData.getColumnLabel(i);
                System.out.println(key);
                resultmap.put(key, executeQuery.getObject(i));

            }
            resullist.add(resultmap);
        }


        close(executeQuery, prepareStatement, connection);



        return resullist;
    }

    /**
     * jdbc关流
     * @param ex
     * @param pr
     * @param co
     */
    public static void close(ResultSet ex,PreparedStatement pr,Connection co){
        try {
            if (ex!=null) ex.close();
            if (pr!=null) pr.close();
            if (co!=null) co.close();


        } catch (Exception e2) {
            // TODO: handle exception
        }

    }
}
