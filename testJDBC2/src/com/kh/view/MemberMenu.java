package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		MemberController mc = new MemberController();
		
		int select = 0;
		do {
			System.out.println("\n*** 회원 관리 프로그램 *** \n");
			System.out.println("1. 새 회원 등록");
			System.out.println("2. 모든 회원 조회");
			System.out.println("3. 특정 조건 회원 조회");
			System.out.println("4. 회원 정보 수정");
			System.out.println("5. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.println("번호 선택 : ");
			select = Integer.parseInt(sc.nextLine());
			
			switch(select) {
			case 1: mc.insertMember();break;
			case 2: mc.selectAll();break;
			case 3: break;
			case 4: break;
			case 5: break;
			case 0: System.out.println("프로그램을 종료합니다");break;
			default: System.out.println("잘못된 번호입니다. 다시입력해주세요.");
			}
			
		}while(select !=0);
	}

	public void displayError(String string) {
		System.out.println("서비스 요청 실패 : " + string);
	}

	public void displayMember(ArrayList<Member> list) {
		System.out.printf("%-10s %-10s %-5s %-5s %-20s %-15s %-4s %-20s %-15s\n",
				"ID","PWD","NAME","GENDER","EMAIL","PHONE","AGE","ADDRESS","ENROLLDATE");
		for(Member m : list) {
			System.out.printf("%-10s %-10s %-8s %-5c %-20s %-15s %-4d %-20s %-15s\n",
					m.getMemberId(), m.getMemberPwd(),m.getMemberName(),m.getGender(),m.getEmail(),
					m.getPhone(),m.getAge(),m.getAddress(),m.getEnrollDate());
		}
	}

	public Member insertMember() {
		System.out.print("아이디 : ");
		String memberId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String memberPwd = sc.nextLine();
		
		System.out.print("이름 : ");
		String memberName = sc.nextLine();
		
		System.out.print("성별(M/F) : ");
		char gender = sc.nextLine().toUpperCase().charAt(0);
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("전화번호(-포함) : ");
		String phone = sc.nextLine();
		
		System.out.print("나이 : ");
		int age = Integer.parseInt(sc.nextLine());
		
		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		Member m = new Member(memberId, memberPwd, memberName, gender, email, phone, age, address);
		
		return null;
	}
}
