package com.hyh.crate;

import java.sql.*;
import java.util.*;

import static com.hyh.utils.JdbcUtils.getConnection;

public class CrateTables {
    public static boolean CREATE_table(String sqlll, Map dataSourceMap, String tablename,Map dataTargetMap ) throws SQLException {
        //show columns from city
        Connection connection = getConnection(dataSourceMap);
        PreparedStatement prepareStatement = connection.prepareStatement(sqlll);
        ResultSet executeQuery = prepareStatement.executeQuery();
        ResultSetMetaData metaData = executeQuery.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<Map> hashMaps = new ArrayList();
        Map resultmap=null;
        while(executeQuery.next()){
            resultmap =new HashMap<String,String>();
            for(int i = 1;i<=columnCount;i++){
                String key = metaData.getColumnLabel(i);
                System.out.println(key+""+executeQuery.getObject(i));
                String object = (String) executeQuery.getObject(i);
                if ("".equals(object)){
                    resultmap.put(key,"null");
                }else{
                    resultmap.put(key,object);
                }
            }
            hashMaps.add(resultmap);
        }
        //String sql = "CREATE TABLE '"+tablename+"' (";
        String sql = "DROP TABLE IF EXISTS `"+tablename+"`; CREATE TABLE `"+tablename+"`(";
        ListIterator<Map> mapListIterator = hashMaps.listIterator();
        int size = hashMaps.size();
        int i = 0;
        String iszhujian="";
        while (mapListIterator.hasNext()){
            i++;
            Map map = mapListIterator.next();
            String field =(String) map.get("Field");
            String Type =(String) map.get("Type");
            String Null =(String) map.get("Null");
            String Extra =(String) map.get("Extra");
            String Default =(String) map.get("Default");;
            String Key =(String) map.get("Key");
            if("NO".equals(Null)){
                Null = "NOT NULL";
            }else{
                Null = "DEFAULT NULL";
            }
            if ("auto_increment".equals(Extra)){
                Extra = "AUTO_INCREMENT";
            }
            if ("null".equals(Extra)){
                Extra = "";
            }
            if(Key.equals("PRI")){
                iszhujian = "PRIMARY KEY (`"+field+"`) ";
            }
            String sqll = "`"+field +"` "+Type + " " +Null +" " + Extra  + ",";

            sql += sqll;

        }
        if(iszhujian == null){
            sql = sql.substring(0, sql.length() - 1);
        }
        sql += iszhujian+")";

        connection = getConnection(dataSourceMap);
        prepareStatement = connection.prepareStatement(sqlll);
        int iscrate = prepareStatement.executeUpdate();
        System.out.println(sql);

        if(iscrate ==1){
            return true;
        }else{
            return false;
        }
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
