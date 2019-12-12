package com.hyh.MigrateThread;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ControlThread {



    public static Map<String,String> sourceDateBase(){
        Map map=new HashMap();
        map.put("driver", "com.mysql.jdbc.Driver");
        map.put("url", "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true");
        map.put("username", "root");
        map.put("pass", "root");
        return map;
    }

    public static Map<String,String> targetDataBase(){
        Map maps=new HashMap();
        maps.put("driver", "com.mysql.jdbc.Driver");
        maps.put("url", "jdbc:mysql://127.0.0.1:3306/test2?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true");
        maps.put("username", "root");
        maps.put("pass", "root");
        return maps;
    }


    public static void main(String[] args) throws SQLException {



    }


    public void recursion () throws SQLException {
        String tablename ="miaosha_user";
        int count = CoreThread.getCount(sourceDateBase(), "seckill_goods");
        int pagesize = 20000;
        int page = count/pagesize;
        if(count%pagesize>0){
            page=page+1;
        }
        if(page>10){
            for (int i = 0; i < page; i++) {
                
                
            }
        }
    }






}
