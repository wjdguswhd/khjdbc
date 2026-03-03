package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.MemberService;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

public class MemberController {
	//private MemberDAO md = new MemberDAO();
	private MemberService mService = new MemberService();
	private MemberMenu menu = new MemberMenu();
	
	public void selectAll() {
		ArrayList<Member> list = mService.selectAll();
		
		if(list.isEmpty()) {
			menu.displayError("조회 결과가 없습니다.");
		}else {
			menu.displayMember(list);
		}
	}

}
