package com.hyh.thread;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlThread {



    public static void main(String[] args) throws SQLException {

        Map dataSourceMap=new HashMap();
        dataSourceMap.put("driver", "com.mysql.jdbc.Driver");
        dataSourceMap.put("url", "jdbc:mysql://127.0.0.1:3306/seckill?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true");
        dataSourceMap.put("username", "root");
        dataSourceMap.put("pass", "root");
        List<Map> show_columns_from_goods = CreateTables.showtables(dataSourceMap, "show columns from goods");
        Map dataTargetMap=new HashMap();
        dataTargetMap.put("driver", "com.mysql.jdbc.Driver");
        dataTargetMap.put("url", "jdbc:mysql://127.0.0.1:3306/world?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true");
        dataTargetMap.put("username", "root");
        dataTargetMap.put("pass", "root");
        boolean goods = CreateTables.CREATE_table(dataTargetMap, show_columns_from_goods, "goods");
        if(goods){
            System.out.println("创建成功");
        }



    }


}
