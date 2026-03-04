package com.kh.model.service;

import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.rollback;


import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.MemberDAO;
import com.kh.model.vo.Member;

public class MemberService {
	// View  < - > Controller < - > Service  < - > DAO
	private MemberDAO mDAO = new MemberDAO();

	public ArrayList<Member> selectAll() {
		Connection conn = getConnection();
		ArrayList<Member> list = mDAO.selectAll(conn);
		return list;
	}

	public int insertMember(Member m) {
		Connection conn =getConnection();
		int result = mDAO.insertMember(conn,m);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}

	public ArrayList<Member> selectMemberId(String id) {
		Connection conn =getConnection();
		ArrayList<Member> list = mDAO.selectMemberId(conn,id);

		return list;
	}

	public ArrayList<Member> selectGender(char gen) {
		Connection conn =getConnection();
		ArrayList<Member> list = mDAO.selectGender(conn,gen);

		return list;
		
	}
}
