package com.shinhan.dept;

import java.util.List;

public class DeptView {
	
	public static void display(List<DeptDTO> deptlist) {
		if(deptlist.size() == 0) {
			
			display("입력하신 부서가 존재하지 않습니다.");
			
		}
		System.out.println("========부서목록========");
		deptlist.stream().forEach(dept -> display(dept));
		}
			
	
	public static void display(DeptDTO dept) {
		System.out.println("부서번호 : " + dept.getDepartment_id());
		System.out.println("부서이름 : " + dept.getDepartment_name());
		System.out.println("매니저 코드 : " + dept.getManager_id());
		System.out.println("지역코드 : " + dept.getLocation_id());
		System.out.println("========================================");
	}

	public static void display(String message) {
		System.out.println("시스템 : " + message);
		
	}
	
	public static void menuDisplay() {
		System.out.println("=============================================================");
		System.out.println("1.부서 조회 2.부서 상세 조회 3.부서 수정 4.부서 삭제 5.부서 생성 6.종료");
		System.out.println("=============================================================");
		System.out.println("메뉴를 선택해 주세요 > ");
	}

}
