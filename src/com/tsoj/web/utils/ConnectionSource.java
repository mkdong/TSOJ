package com.tsoj.web.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionSource {
	private static final String url;
	private static final String user;
	private static final String passwd;
	static {
		url = "jdbc:mysql://localhost:3306/tsoj";//tmpoj";
		user = "tsoi";//"tmpoj";
		passwd = "tsoi";//"tmpoj";
	}
	public static final Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url, user, passwd);
	}
}
