package com.tsoj.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProblemSet {
	private ArrayList<Problem> problems;
	
	public ProblemSet() {
		problems = new ArrayList<Problem>();
	}
	
	public ArrayList<Problem> getProblems() {
		return problems;
	}
	
	public void fetchProblems(int psid) throws Exception {
		Connection conn = null;
		PreparedStatement stmt;
		ResultSet rs;
		problems.clear();
		
		try {
			conn = ConnectionSource.getConnection();
			stmt = conn.prepareStatement(
					"select * from `Problems` where pid>=(?) and pid<(?);");
			stmt.setInt(1, psid * 100 + 1);
			stmt.setInt(2, psid * 100 + 101);
			
			if (!stmt.execute()) {
				throw new Exception("database error");
			}
			rs = stmt.getResultSet();
			
			while (rs.next()) {
				Problem p = new Problem();
				p.setPid(rs.getInt("pid"));
				p.setPtitle(rs.getString("ptitle"));
				p.setPcontent(rs.getString("pcontent"));
				p.setPinput(rs.getString("pinput"));
				p.setPoutput(rs.getString("poutput"));
				p.setPsamplei(rs.getString("psamplei"));
				p.setPsampleo(rs.getString("psampleo"));
				p.setPtime(rs.getInt("ptime"));
				p.setPmemory(rs.getInt("pmemory"));
				p.setPlevel(rs.getInt("plevel"));
				p.setPcategory(rs.getString("pcategory"));
				problems.add(p);
			}
			
		} catch (SQLException e) {
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
	}
}
