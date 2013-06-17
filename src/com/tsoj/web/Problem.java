package com.tsoj.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Problem {
	private int pid;
	private String ptitle;
	private String pcontent;
	private String pinput;
	private String poutput;
	private String psamplei;
	private String psampleo;
	private int ptime;
	private int pmemory;
	private int plevel;
	private String pcategory;
	
	public Problem() {
		ptitle = pcontent = pinput = poutput = psamplei = psampleo = pcategory = null;
		ptime = pmemory = plevel = 0;
	}
	
	public void setPid(int pid) { this.pid = pid; }
	public int getPid() { return pid; }
	public void setPtitle(String ptitle) { this.ptitle = ptitle; }
	public String getPtitle() { return ptitle; }
	public void setPcontent(String pcontent) { this.pcontent = pcontent; }
	public String getPcontent() { return pcontent; }
	public void setPinput(String pinput) { this.pinput = pinput; }
	public String getPinput() { return pinput; }
	public void setPoutput(String poutput) { this.poutput = poutput; }
	public String getPoutput() { return poutput; }
	public void setPsamplei(String psamplei) { this.psamplei = psamplei; }
	public String getPsamplei() { return psamplei; }
	public void setPsampleo(String psampleo) { this.psampleo = psampleo; }
	public String getPsampleo() { return psampleo; }
	public void setPtime(int ptime) { this.ptime = ptime; }
	public int getPtime() { return ptime; }
	public void setPmemory(int pmemory) { this.pmemory= pmemory; }
	public int getPmemory() { return pmemory; }
	public void setPlevel(int plevel) { this.plevel = plevel; }
	public int getPlevel() { return plevel; }
	public void setPcategory(String pcategory) { this.pcategory = pcategory; }
	public String getPcategory() { return pcategory; }
	
	public static final Problem fetchProblem(int pid) throws Exception {
		Connection conn = null;
		PreparedStatement stmt;
		ResultSet rs;
		Problem p = null;
		
		try {
			conn = ConnectionSource.getConnection();
			stmt = conn.prepareStatement(
					"select * from `Problems` where pid=(?);");
			stmt.setInt(1, pid);
			
			if (!stmt.execute()) {
				throw new Exception("database error");
			}
			rs = stmt.getResultSet();
			
			if (rs.next()) {
				p = new Problem();
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
		return p;
	}
	
	public static final void createProblem(Problem p) throws Exception {
		Connection conn = null;
		PreparedStatement stmt;
		
		if (null == p) throw new Exception("null user passed!");
		
		try {
			conn = ConnectionSource.getConnection();

			stmt = conn.prepareStatement(
					"insert into `Problems`(`ptitle`,`pcontent`,`pinput`,`poutput`," +
					"`psamplei`, `psampleo`, `ptime`, `pmemory`, `plevel`, `pcategory`)" +
					"values(?,?,?,?,?,?,?,?,?,?);");
			stmt.setString(1, p.getPtitle());
			stmt.setString(2, p.getPcontent());
			stmt.setString(3, p.getPinput());
			stmt.setString(4, p.getPoutput());
			stmt.setString(5, p.getPsamplei());
			stmt.setString(6, p.getPsampleo());
			stmt.setInt(7, p.getPtime());
			stmt.setInt(8, p.getPmemory());
			stmt.setInt(9, p.getPlevel());
			stmt.setString(10, p.getPcategory());
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
	
	public static final void updateUser(Problem p) throws Exception {
		Connection conn = null;
		PreparedStatement stmt;
		
		if (null == p) throw new Exception("null user passed!");
		try {
			conn = ConnectionSource.getConnection();
			stmt = conn.prepareStatement(
					"update `Problems`" +
					"set " +
					"`ptitle`=(?)," +
					"`pcontent`=(?)" +
					"`pinput`=(?)" +
					"`poutput`=(?)" +
					"`psmaplei`=(?)" +
					"`psmapleo`=(?)" +
					"`ptime`=(?)" +
					"`pmemory`=(?)" +
					"`plevel`=(?)" +
					"`pcategory`=(?)" +
					"where `uid`=(?);");
			stmt.setString(1, p.getPtitle());
			stmt.setString(2, p.getPcontent());
			stmt.setString(3, p.getPinput());
			stmt.setString(4, p.getPoutput());
			stmt.setString(5, p.getPsamplei());
			stmt.setString(6, p.getPsampleo());
			stmt.setInt(7, p.getPtime());
			stmt.setInt(8, p.getPmemory());
			stmt.setInt(9, p.getPlevel());
			stmt.setString(10, p.getPcategory());
			stmt.setInt(11, p.getPid());
			
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
