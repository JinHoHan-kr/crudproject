package com.shinhan.dept;

import java.util.List;
import lombok.extern.java.Log;

@Log
public class DeptService {
	DeptDAO deptDAO = new DeptDAO();
	
	public int deleteDept(int deptid) {
		int result = deptDAO.deleteDept(deptid);
		log.info("DeptService에서 로그 출력 : " + result + " 건 delete");
		return result;
	}
	
	public int updateDept(DeptDTO dept) {
		int result = deptDAO.updateDept(dept);
		log.info("DeptService에서 로그 출력 : " + result + " 건 update");
		return result;
	}
	
	public int insertDept(DeptDTO dept) {
		int result = deptDAO.insertDept(dept);
		log.info("DeptService에서 로그 출력 : " + result + " 건 insert");
		return result;
	}
	
	public DeptDTO deptSelectTag(int department_id) {
		DeptDTO dept = deptDAO.deptSelectTag(department_id);
		log.info("DeptService에서 로그 출력 : " + dept.toString() + " 건");
		return dept;
	}
	
	public List<DeptDTO> deptSelectAll() {
		List<DeptDTO> deptList = deptDAO.deptSelectAll();
		log.info("DeptService에서 로그 출력 : " + deptList.size() + " 건");
		return deptList;
	}

}
