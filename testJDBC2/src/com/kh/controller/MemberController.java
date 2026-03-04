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

	public void insertMember() {
		Member m = menu.insertMember();
		
		int result = mService.insertMember(m);
		if(result>0) {
			menu.displaySuccess(result + "개의 행이 추가되었습니다.");
		}else {
			menu.displayError("데이터 삽입 과정 중 오류 발생");
		}
	}

	public void selectMember() {
		//검색 조건 결정 : 아이디, 성별
		int sel = menu.selectMember();
		ArrayList<Member> list = null;
		switch(sel) {
		case 1:
			String id = menu.inputMemberId();
			list = mService.selectMemberId(id);// 부분 검색(포함 검색)
			break;
		case 2:
			char gen = menu.inputGender();
			list = mService.selectGender(gen);
			break;
		case 0: return;
		}
		if(list.isEmpty()) {
			menu.displayError("조회 결과가 없습니다.");
		}else {
			menu.displayMember(list);
		}
	}

}
