package cn.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.dao.IUser;
import cn.domain.User;
import cn.utils.JDBCUtils;

public class UserImpl implements IUser {

	@Override
	//登录
	public User login(String name, String password) {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtils.getConnection();
			st=conn.createStatement();
			String sql="select * from user where name='"+name+"' and password='"+password+"'";
			rs=st.executeQuery(sql);
			while(rs.next()) {
				User u=new User();
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closes(rs, st, conn);
		}
		return null;
	}

	@Override
	public boolean delete(int ids) {
		Connection conn=null;
		Statement st=null;
		try {
			conn=JDBCUtils.getConnection();
			st=conn.createStatement();
			String sql="delete from user where id="+ids;
			int num=st.executeUpdate(sql);
			if(num>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closes(st, conn);
		}
		return false;
	}

	@Override
	public ArrayList<User> getlist() {
		ArrayList<User> list= new ArrayList<User>();
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtils.getConnection();
			st=conn.createStatement();
			String sql="select *from user";
			rs=st.executeQuery(sql);
			while(rs.next()) {
				User u=new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				list.add(u);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closes(rs,st, conn);
		}
		return null;
	}

	@Override
	public boolean update(User u) {
		Connection conn=null;
		Statement st=null;
		try {
			conn=JDBCUtils.getConnection();
			st=conn.createStatement();
			String sql="update  user set name='"+u.getName()+"',password='"+u.getPassword()+"' where id="+u.getId();
			int num=st.executeUpdate(sql);
			if(num>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closes(st, conn);
		}
		return false;
	}

	@Override
	//添加
	public boolean inset(User u) {
		Connection conn=null;
		PreparedStatement st=null;
		try {
			conn=JDBCUtils.getConnection();
			st=conn.prepareStatement("insert into user(name,password) values(?,?)");
			st.setString(1, u.getName());
			st.setString(2, u.getPassword());
			int num=st.executeUpdate();
			if(num>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closes(st, conn);
		}
		return false;
	}



}
