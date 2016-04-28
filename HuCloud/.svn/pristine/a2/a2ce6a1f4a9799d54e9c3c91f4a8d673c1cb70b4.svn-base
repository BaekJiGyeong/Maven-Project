package kr.co.hucloud.security.code.example.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.co.hucloud.security.code.example.common.util.DBCloseUtil;
import kr.co.hucloud.security.code.example.member.dao.MemberDAO;
import kr.co.hucloud.security.code.example.member.vo.LoginVO;
import kr.co.hucloud.security.code.example.member.vo.MemberRegistryVO;
import kr.co.hucloud.security.code.example.member.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void addMember(MemberRegistryVO memberVO) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO USERS ( " +
				"   USER_ID, USER_NAME, USER_PASSWORD,  " +
				"   IS_ADMIN_YN, CRT_DT, MDFY_DT)  " +
				"VALUES ( ?, ?, ?, ?, SYSDATE, SYSDATE ) "; 
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberVO.getUserId());
			pstmt.setString(2, memberVO.getUserName());
			pstmt.setString(3, memberVO.getUserPassword());
			pstmt.setString(4, "Y");
			pstmt.execute();
		}
		catch(SQLException sqle) {
			throw new RuntimeException(sqle.getMessage(), sqle);
		}
		finally {
			DBCloseUtil.close(conn, pstmt, null);
		}
		
	}
	
	@Override
	public MemberVO login(LoginVO loginVO) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//FIXME SQLInjection 방어하기
		String query = " SELECT USER_ID, USER_NAME, USER_PASSWORD "
				+ "FROM USERS WHERE USER_ID = '" + loginVO.getId() + "' AND USER_PASSWORD = '" + loginVO.getPassword() + "'";
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			MemberVO memberVO = null;
			
			if(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setId(rs.getString(1));
				memberVO.setUserName(rs.getString(2));
				memberVO.setPassword(rs.getString(3));
			}
			return memberVO;
		}
		catch(SQLException sqle) {
			throw new RuntimeException(sqle.getMessage(), sqle);
		}
		finally {
			DBCloseUtil.close(conn, stmt, rs);
		}
	}
	
	@Override
	public List<MemberVO> getUserInfo(String parameter) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		//FIXME SQLInjection 방어하기
		String query = " SELECT USER_ID, USER_NAME, USER_PASSWORD FROM USERS WHERE USER_ID = '" + parameter + "'";
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			MemberVO memberVO = null;
			
			while(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setId(rs.getString(1));
				memberVO.setUserName(rs.getString(2));
				memberVO.setPassword(rs.getString(3));
				memberList.add(memberVO);
			}
			return memberList;
		}
		catch(SQLException sqle) {
			throw new RuntimeException(sqle.getMessage(), sqle);
		}
		finally {
			DBCloseUtil.close(conn, stmt, rs);
		}
		
	}
	
	@Override
	public List<MemberVO> getAllMemberInfo() {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		String query = " SELECT USER_ID, USER_PASSWORD FROM USERS ";
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			MemberVO memberVO = null;
			
			while(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setId(rs.getString(1));
				memberVO.setPassword(rs.getString(2));
				memberList.add(memberVO);
			}
			return memberList;
		}
		catch(SQLException sqle) {
			throw new RuntimeException(sqle.getMessage(), sqle);
		}
		finally {
			DBCloseUtil.close(conn, stmt, rs);
		}
		
	}
	
	@Override
	public void updateMemberPassword(MemberVO memberVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE SYSTEM.USERS " +
						"SET    USER_PASSWORD = ? " +
						"       , SALT = ? " +
						"       , MDFY_DT       = SYSDATE " +
						"WHERE  USER_ID       = ? ";
				
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberVO.getPassword());
			pstmt.setString(2, memberVO.getSalt());
			pstmt.setString(3, memberVO.getId());
			pstmt.execute();
		}
		catch(SQLException sqle) {
			throw new RuntimeException(sqle.getMessage(), sqle);
		}
		finally {
			DBCloseUtil.close(conn, pstmt, null);
		}
	}
	
	@Override
	public String getSaltById(String id) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String query = " SELECT SALT FROM USERS WHERE USER_ID = ? ";
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			String salt = "";
			
			if(rs.next()) {
				salt = rs.getString(1);
			}
			
			return salt;
		}
		catch(SQLException sqle) {
			throw new RuntimeException(sqle.getMessage(), sqle);
		}
		finally {
			DBCloseUtil.close(conn, stmt, rs);
		}
	}
	
}
