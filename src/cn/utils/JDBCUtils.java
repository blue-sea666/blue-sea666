package cn.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCUtils {
	public static Connection getConnection() {
		//加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//设置参数
			String url="jdbc:mysql:///testdb?useUnicode=true&characterEncoding=utf8";
			String user="root";
			String password="root";
			Connection conn=DriverManager.getConnection(url, user, password);
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	//关闭资源
	public static void closes(Statement st,Connection conn) {
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			st=null;
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn=null;
		}
		
	}
	//关闭资源
	public static void closes(ResultSet rs,Statement st,Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs=null;
		}
		closes( st, conn);
		
	} 

}
