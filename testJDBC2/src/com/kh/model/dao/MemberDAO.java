package com.kh.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

public class MemberDAO {
	/*
	  이전 프로젝트에서 DAO가 맡은 업무 
	  1. JDBC 드라이버 등록
	  2. DB 연결 Connection 객체 생성
	  3. SQL 실행
	  4. 처리 결과에 따른 트랜잭션 처리
	  5. 자원 반납
	 --> 실제로 DAO가 해야하는 업무는 SQL문을 DB로 전달해서 실행하고 반환 값을 받아오는 것만 하면 됨
	 --> 1,2,4,5,번 업무 분리 (JDBCTemplate, Service단 도입)
	 
	 +JDBCTemplate? JDBC에 필요한 공통 업무(중복 코드)를 모아둔 곳
	 +Service단? model에 묶여있는 곳으로 DAO보조 	 
	 */

	public ArrayList<Member> selectAll(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		
		String query = "select * from member";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				char gender = rset.getString("gender").charAt(0);
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				int age = rset.getInt("age");
				Date enrollDate = rset.getDate("enroll_date");
				
				Member m = new Member(memberId, memberPwd,memberName,gender,email,phone,age,address,enrollDate);
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,sysdate)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
	        pstmt.setString(2, m.getMemberPwd());
	        pstmt.setString(3, m.getMemberName());
	        pstmt.setString(4, m.getGender()+"");
	        pstmt.setString(5, m.getEmail());
	        pstmt.setString(6, m.getPhone());
	        pstmt.setString(7, m.getAddress());
	        pstmt.setInt(8, m.getAge());
	        
	        result = pstmt.executeUpdate();
	       
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;

	}

	public ArrayList<Member> selectMemberId(Connection conn, String id) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		
		String query = "select * from member where member_id like '%" + id + "%'";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				char gender = rset.getString("gender").charAt(0);
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				int age = rset.getInt("age");
				Date enrollDate = rset.getDate("enroll_date");
				
				Member m = new Member(memberId, memberPwd,memberName,gender,email,phone,age,address,enrollDate);
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		
		return list;
	}

	public ArrayList<Member> selectGender(Connection conn, char gen) {
		
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		
		String query = "select * from member where gender = '" + gen + "'";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				char gender = rset.getString("gender").charAt(0);
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				int age = rset.getInt("age");
				Date enrollDate = rset.getDate("enroll_date");
				
				Member m = new Member(memberId, memberPwd,memberName,gender,email,phone,age,address,enrollDate);
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return list;
	}
}
