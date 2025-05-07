package com.shinhan.dept;

import java.util.Scanner;

import com.shinhan.common.CommonControllerInterface;


public class DeptController implements CommonControllerInterface {
	static Scanner sc = new Scanner(System.in);
	static DeptService deptService = new DeptService();
	public void execute() {
		
		boolean isOver = false;
		while(!isOver) {
			DeptView.menuDisplay();
			String sel = sc.nextLine();
			switch (sel) {
			case "1" -> {deptSelectAll();}
			case "2" -> {deptSelectTag();}
			case "3" -> {updateDept();}
			case "4" -> {deleteDept();}
			case "5" -> {insertDept();}
			case "6" -> {isOver = true;}
				
			
			}
			
		}
		DeptView.display("프로그램을 종료합니다.");
	}

	private static void insertDept() {
		
		int deptid = parseInt(dataInsert("부서 번호 > "));
		String deptname = dataInsert("부서 이름 > ");
		int mgrid = parseInt(dataInsert("매니저 코드 > "));
		int locid = parseInt(dataInsert("지역 코드 > "));
		DeptDTO dept = DeptDTO.builder()
				.department_id(deptid)
				.department_name(deptname)
				.manager_id(mgrid)
				.location_id(locid)
				.build();
		DeptView.display(deptService.insertDept(dept) + "건 입력");
		
	}

	private static int parseInt(String dataInsert) {
		return Integer.parseInt(dataInsert);
	}

	private static String dataInsert(String column) {
		System.out.println(column);
		return sc.nextLine();
	}

	private static void deleteDept() {
		int deptid = parseInt(dataInsert("삭제할 부서 번호 > "));
		deptService.deleteDept(deptid);
		DeptView.display(deptService.deleteDept(deptid) + "건 삭제");
		
	}

	private static void updateDept() {
		int deptid = parseInt(dataInsert("수정 부서 번호 > "));
		String deptname = dataInsert("부서 이름 > ");
		int mgrid = parseInt(dataInsert("매니저 코드 > "));
		int locid = parseInt(dataInsert("지역 코드 > "));
		DeptDTO dept = DeptDTO.builder()
				.department_id(deptid)
				.department_name(deptname)
				.manager_id(mgrid)
				.location_id(locid)
				.build();
		DeptView.display(deptService.updateDept(dept) + "건 수정");
		
	}

	private static void deptSelectTag() {
		int deptid = parseInt(dataInsert("부서 번호 > "));
		DeptView.display(deptService.deptSelectTag(deptid));
		
	}

	private static void deptSelectAll() {
		DeptView.display(deptService.deptSelectAll());
		
	}


	

}
