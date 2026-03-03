package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.getConnection;
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
}
