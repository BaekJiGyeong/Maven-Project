package kr.co.hucloud.security.code.example.common.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCloseUtil {

	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		
		if(rs != null) {
			try {
				rs.close();
			}
			catch(SQLException sqle) {}
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			}
			catch(SQLException sqle) {}
		}
		
		if(conn != null) {
			try {
				conn.close();
			}
			catch(SQLException sqle) {}
		}
		
	}
	
}
