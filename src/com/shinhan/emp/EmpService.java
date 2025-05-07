package com.shinhan.emp;

import java.util.List;

//Service : business logic 수행
//ex : 이체업무 : 인출하기, 입금하기
//ex2 : 비밀번호 암호화
public class EmpService {
	
	EmpDAO empDAO = new EmpDAO();
	
	//모든 직원 조회
	public List<EmpDTO> selectAll() {
		return empDAO.selectAll();
	}
	
	//직원 번호로 조회
	public EmpDTO selectbById(int empid) {
		return empDAO.selectbById(empid);
	}
	
	//부서 번호로 조회
	public List<EmpDTO> selectByDept(int deptid) {
		return empDAO.selectByDept(deptid);
	}
	
	//직책으로 조회
	public List<EmpDTO> selectByJob(String job) {
		return empDAO.selectByJob(job);
	}
	
	//직책, 부서 번호로 조회
	public List<EmpDTO> selectByJobAndDept(String job, int dept) {
		return empDAO.selectByJobAndDept(job, dept);
	}

	//상세 조건으로 조회
	public List<EmpDTO> selectByCondition(Integer[] deptidArr, String jobid, int salary, String hdate) {
		return empDAO.selectByCondition(deptidArr, jobid, salary, hdate);
	}
	
	//직원 번호로 삭제
	public int empDeleteById(int empId) {
		return empDAO.empDeleteById(empId);
	}
	
	//직원 정보 삽입
	public int empInsert(EmpDTO emp) {
		return empDAO.empInsert(emp);
	}
	
	//직원 정보 수정
	public int empUpdate(EmpDTO emp) {
		return empDAO.empUpdate(emp);
	}
	
	public EmpDTO execute_sp(int empid) {
		return empDAO.execute_sp(empid);
	}
}
