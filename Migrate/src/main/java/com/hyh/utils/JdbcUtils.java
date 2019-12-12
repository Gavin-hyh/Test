package com.hyh.utils;

import java.sql.*;
import java.util.*;


public class JdbcUtils {

	/**
	 * 链接jdbc
	 * @return
	 */
	public static  Connection getConnection(Map dataSourceMap){
		
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
	
	
	
	
	//查询
	public static List<Map> queryData(Map dataSourceMap,String sql) throws SQLException{
		Connection connection = getConnection(dataSourceMap);
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		ResultSet executeQuery = prepareStatement.executeQuery();
		ResultSetMetaData metaData = executeQuery.getMetaData();
		int columnCount = metaData.getColumnCount();
		Map resultmap=null;
		
		List<Map> resullist=new ArrayList();
		while(executeQuery.next()){
			resultmap =new HashMap();
			for(int i = 1;i<=columnCount;i++){
				String key = metaData.getColumnLabel(i);
				System.out.println(key+""+executeQuery.getObject(i));
				resultmap.put(key, executeQuery.getObject(i));
			}
			resullist.add(resultmap);
		}
		
		close(executeQuery, prepareStatement, connection);
		
		return resullist;	
	}
	
	
	
	public static void affair(Map dataSourceMap,String sql) throws SQLException {
		
		Connection connection = getConnection(dataSourceMap);
		connection.setAutoCommit(false);
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		int executeUpdate = prepareStatement.executeUpdate();
		connection.commit();
		close(null, prepareStatement, connection);
		
		
	}
	//查询数据库表字段
	public static List<Map> showtables(Map dataSourceMap,String sql) throws SQLException {
		//show columns from city
		Connection connection = getConnection(dataSourceMap);
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
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
		close(executeQuery, prepareStatement, connection);
		return hashMaps;
	}
	
	public static void main(String[] args) throws SQLException {
		Map map=new HashMap();
		map.put("driver", "com.mysql.jdbc.Driver");
		map.put("url", "jdbc:mysql://127.0.0.1:3306/seckill?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true");
		map.put("username", "root");
		map.put("pass", "root");
		//List<Map> queryData = queryData(map, "show columns from city");
		List<Map> show_columns_from_city = showtables(map, "show columns from goods");
		CREATE_table(map,show_columns_from_city,"goodsss");
		System.out.printf(show_columns_from_city.toString());
		//affair(map, "INSERT INTO `seckill`.`seckill_user` (`id`, `nickname`, `password`, `salt`, `head`, `register_data`, `last_login_data`, `login_count`) VALUES ('13000099000', 'user99000', 'b7797cce01b4b131b433b6acf4add449', '1a2b3c', NULL, '2019-11-29 21:02:23', NULL, '1');\n");
	}

	public static boolean CREATE_table( Map dataSourceMap,List<Map> resultmap, String tablename) throws SQLException {
		//String sql = "CREATE TABLE '"+tablename+"' (";
		String sql = "DROP TABLE IF EXISTS `"+tablename+"`; CREATE TABLE `aaa` (";

		ListIterator<Map> mapListIterator = resultmap.listIterator();
		int size = resultmap.size();
		int i = 0;
		String iszhujian="";
		while (mapListIterator.hasNext()){
			i++;
			Map map = mapListIterator.next();
			String field =(String) map.get("Field");
			String Type =(String) map.get("Type");
			String Null =(String) map.get("Null");
			String Extra =(String) map.get("Extra");
			String Default =(String) map.get("Default");
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
		System.out.println(sql);
		Connection connection = getConnection(dataSourceMap);
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		int executeUpdate = prepareStatement.executeUpdate();
		if (executeUpdate == 1){
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
