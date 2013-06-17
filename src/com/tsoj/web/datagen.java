package com.tsoj.web;

import java.sql.*;
import java.util.Random;

public class datagen {

	public static String genStr(int size) {
		size = genInt(size - 1) + 1;
		char[] charset = new char[62];
		for (int i=0; i<26; ++i) {
			charset[i] = (char) ('a' + i);
			charset[26+i] = (char) ('A' + i);
		}
		for (int i=0; i<10; ++i) {
			charset[52+i] = (char) ('0'+i);
		}
		
		String s = "";
		Random rand = new Random();
		for (int i=0; i<size; ++i) {
			s += charset[rand.nextInt(62)];
		}
		return s;
	}
	
	public static int genInt(int size) {
		Random rand = new Random();
		return rand.nextInt(size);
	}
	
	public static boolean genBool() {
		Random rand = new Random();
		return rand.nextBoolean();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt;
		try {
			conn = ConnectionSource.getConnection();
			stmt = conn.prepareStatement(
					"insert into Users(uid, uname, upasswd, utype, uinfo)" +
					"values(?,?,?,?,?);");
			stmt.setString(1, genStr(32));
			stmt.setString(2, genStr(32));
			stmt.setString(3, genStr(32));
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
