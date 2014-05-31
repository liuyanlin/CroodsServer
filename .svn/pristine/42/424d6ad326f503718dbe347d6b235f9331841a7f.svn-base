package com.thecroods.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * SQLServer工具类
 * @author LIUYANLIN
 * @since 2014-3-26
 */
public class DBUtil {

	
	private static final String DRIVER_NAME="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DBURL="jdbc:sqlserver://localhost:1433;DatabaseName=thecroods";
	private static final String USERNAME="thecroods";
	private static final String USERPWD="thecroods";
	
	//public static Connection conn = null;
	private static ConnectionPool pool = null;
	
	/**
	 * 数据库连接
	 * @return Connection
	 */
	public static Connection getConnection()
	{
		try {
			
			if (pool == null)
			{
				ConnectionParam param = new ConnectionParam(DRIVER_NAME, DBURL, USERNAME, USERPWD);
				
				pool = new ConnectionPool(param);
				pool.createPool();
			}
//			Class.forName(DRIVER_NAME);
//			conn=DriverManager.getConnection(DBURL,USERNAME,USERPWD);
//			System.out.println("Connection Successful!"); 
						
			return pool.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	//static PreparedStatement ps;
	//static ResultSet rs;

	/**
	 * 数据库关闭
	 */
	public static void ConnClose()
	{
        try {       
//        	if(rs != null){         
//        		rs.close();
//            }
//            if(ps != null){        
//            	ps.close();
//            }

        } catch (Exception e) {

        	e.printStackTrace();
        	System.out.println("关闭连接出错");
        	System.exit(0);
        }
	}
	
	/**
	 * 查询
	 * @param sql
	 * @return
	 */
	public static ResultSet get(String sql)
	{
		try {
			Connection conn = getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询
	 * @param sql
	 * @param arrays 参数数组
	 * @return ResultSet
	 * @example String sql = "select id,name,address from Student where id=?";
	 *          Object arrays[] = {"1"};
	 */

//	public static ResultSet get(String sql,Object arrays[])
//	{
//		try {
//			Connection conn = getConnection();
//			PreparedStatement ps = conn.prepareStatement(sql);
//			
//		    //设置参数
//			for (int i = 0; i < arrays.length; i++) 
//				ps.setObject(i+1, arrays[i]);
//			
//			ResultSet rs = ps.executeQuery();
//			
//			return rs;
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	/**
	 * 更新
	 * @param sql
	 */
	public static int update(String sql)
	{
		int i=0 ;
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			i = ps.executeUpdate();
			
			ps.close();
			
			return i;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	
	/**
	 * 更新
	 * @param sql
	 * @param arrays
	 * @example String sql = "insert into People values(?,?,?)";
	 *          Object arrays[] = {"1","2","3"};
	 */
	public static void update(String sql,Object arrays[])
	{
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 结果集转换为list
	 * @param rs
	 */
	public static void ResultSet2List(ResultSet rs)
	{		
		try {
			//List list = new ArrayList<>();
			while(rs.next())
			{
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
