package com.hyh.MigrateThread;

import com.hyh.utils.JdbcUtils;

import java.sql.*;
import java.util.*;

public class CoreThread implements Runnable{

    //source database linkage information
    private Map dataSourceMap;
    //target database linkage information
    private Map dataTargetMap;
    // sql statement
    private String sql = null;
    private String tableName;


    /*
     *
     * 查询所有条数
     *
     * */
    public static int getCount(Map map,String tableName) throws SQLException {
        Connection connection = getConnection(map);
        Statement st=connection.createStatement();
        ResultSet rs =st.executeQuery("select count(*) from "+tableName);
        //创建变量存取个数
        int count=0;
        while(rs.next())
        {
            count = Integer.parseInt(rs.getString(1));
        }
        JdbcUtils.close(rs,null,connection);
        return count;
    }

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



    @Override
    public void run() {
        Connection connection = getConnection(dataSourceMap);
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        ResultSetMetaData metaData = null;
        int columnCount = 0;
        List<Map> resullist= null;
        try {
            prepareStatement = connection.prepareStatement(sql);
            executeQuery = prepareStatement.executeQuery();
            metaData = executeQuery.getMetaData();
            columnCount = metaData.getColumnCount();
            Map resultmap=null;
            //添加结果集
            resullist = new ArrayList();
            while(executeQuery.next()){
                resultmap =new HashMap();
                for(int i = 1;i<=columnCount;i++){
                        String key = metaData.getColumnLabel(i);
                        System.out.println(key+""+executeQuery.getObject(i));
                        resultmap.put(key, executeQuery.getObject(i));
                }
                resullist.add(resultmap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StringBuffer sbuf=new StringBuffer();
        List<String> sqllist =new ArrayList();
        for (Map map : resullist) {
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

        try {
            connection = getConnection(dataTargetMap);
            connection.setAutoCommit(false);//手动提交事物
            Statement createStatement = connection.createStatement();
            for (String string : sqllist) {
                createStatement.addBatch(string);
            }
            int[] executeBatch = createStatement.executeBatch();
            connection.commit();//提交事物
            createStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(executeQuery, prepareStatement, connection);
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
