package com.zjh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zjh.db.C3P0;
import com.zjh.db.Druid;
import com.zjh.vo.User;

public class UserDAOImpl implements IUserDAO{
	public User login(User user){
		//Connection conn=DBCP.getConnection();
		//Connection conn=C3P0.getConnection();
		Connection conn=Druid.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		User user2=null;
		String sql="select * from user where username=? and password=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			rs=pstmt.executeQuery();
			if(rs.next()){
				user2=new User();
				user2.setId(rs.getInt("id"));
				user2.setUsername(rs.getString("username"));
				user2.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
					pstmt.close();
					//DBCP.closeConnection(conn);
					//C3P0.closeConnection(conn);
					Druid.closeConnection(conn);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return user2;	
	}
}
