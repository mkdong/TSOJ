package com.tsoj.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class User {
	private String uid;
	private String upasswd;
	private char utype;
	private String uname;
	private String uinfo;
	
	public User(String uid, String upasswd, char utype, String uname, String uinfo) {
		this.uid = uid;
		this.upasswd = upasswd;
		this.utype = utype;
		this.uname = uname;
		this.uinfo = uinfo;
	}
	
	public User() {
		uid = upasswd = uname = uinfo = null;
		utype = 'N';
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUid() {
		return uid;
	}
	
	public void setUpasswd(String upasswd) {
		this.upasswd = upasswd;
	}
	public String getUpasswd() {
		return upasswd;
	}
	
	public void setUtype(char utype) {
		this.utype = utype;
	}
	public char getUtype() {
		return utype;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUname() {
		return uname;
	}
	
	public void setUinfo(String uinfo) {
		this.uinfo = uinfo;
	}
	public String getUinfo() {
		return uinfo;
	}
	public static final User fetchUser(String uid) throws Exception {
		Connection conn = null;
		PreparedStatement stmt;
		ResultSet rs;
		User usr = null;
		
		Logger logger = LogManager.getLogger(User.class.getName());
		logger.info("fetchUser:");
		try {
			conn = ConnectionSource.getConnection();
			stmt = conn.prepareStatement(
					"select * from `Users` where uid=(?);");
			stmt.setString(1, uid);
			
			logger.info("fetchUser=> uid:" + uid);
			if (!stmt.execute()) {
				throw new Exception("database error");
			}
			rs = stmt.getResultSet();
			
			logger.info("fetchUser=> getResultSet");
			if (rs.next()) {
				logger.info("fetchUser=> rs.next()");
				usr = new User();
				logger.info("fetchUser=> usr loading the info.");
				logger.info("fetchUser=> usr loading the uid:" + rs.getString("uid"));
				usr.setUid(rs.getString("uid"));
				logger.info("fetchUser=> usr loading the uname:" + rs.getString("uname"));
				usr.setUname(rs.getString("uname"));
				logger.info("fetchUser=> usr loading the upasswd:" + rs.getString("upasswd"));
				usr.setUpasswd(rs.getString("upasswd"));
				logger.info("fetchUser=> usr loading the uinfo:" + rs.getString("uinfo"));
				usr.setUinfo(rs.getString("uinfo"));
				logger.info("fetchUser=> usr loading the utype:" + rs.getString("utype"));
				usr.setUtype(rs.getString("utype").charAt(0));
				logger.info("fetchUser=> usr loaded the info.");
				if (rs.next()) {
					throw new Exception("multiple PK");
				}
				
			} else {
				//throw new Exception("User Not Found");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("database error");
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new Exception("database error");
				}
			}
		}
		return usr;
	}
	
	public static final void createUser(User usr) throws Exception {
		Connection conn = null;
		PreparedStatement stmt;
		
		if (null == usr) throw new Exception("null user passed!");
		
		try {
			conn = ConnectionSource.getConnection();

			stmt = conn.prepareStatement(
					"insert into `Users`(`uid`, `upasswd`,`uname`,`utype`,`uinfo`)" +
					"values(?,?,?,?,?);");
			stmt.setString(1, usr.getUid());
			stmt.setString(2, usr.getUpasswd());
			stmt.setString(3, usr.getUname());
			stmt.setString(4, String.valueOf(usr.getUtype()));
			stmt.setString(5, usr.getUinfo());
			stmt.execute();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("database error!");
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new Exception("database error!");
				}
			}
		}
	}
	
	public static final void updateUser(User usr) throws Exception {
		Connection conn = null;
		PreparedStatement stmt;
		
		if (null == usr) throw new Exception("null user passed!");
		try {
			conn = ConnectionSource.getConnection();
			stmt = conn.prepareStatement(
					"update `Users`" +
					"set `upasswd`=(?)," +
					"`uname`=(?)" +
					"`utype`=(?)" +
					"`uinfo`=(?)" +
					"where `uid`=(?);");
			stmt.setString(1, usr.getUpasswd());
			stmt.setString(2, usr.getUname());
			stmt.setString(3, String.valueOf(usr.getUtype()));
			stmt.setString(4, usr.getUinfo());
			stmt.setString(5, usr.getUid());
			
			stmt.execute();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("database error!");
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new Exception("database error!");
				}
			}
		}
	}
}
