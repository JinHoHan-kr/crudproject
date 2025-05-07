package com.shinhan.emp;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.shinhan.common.CommonControllerInterface;

public class EmpController implements CommonControllerInterface {
	
	static Scanner sc = new Scanner(System.in);
	static EmpService empService = new EmpService();
	public void execute() {
		boolean isStop = false;
		while(!isStop) {
			menuDisplay();
			int job = sc.nextInt();
			switch(job) {
			case 1 -> {f_selectAll();}
			case 2 -> {f_selectById();}
			case 3 -> {f_selectByDept();}
			case 4 -> {f_selectByJob();}
			case 5 -> {f_selectByJobAndDept();}
			case 6 -> {f_selectByCondition();}
			case 7 -> {f_empDeleteById();}
			case 8 -> {f_empInsert();}
			case 9 -> {f_empUpdate();}
			case 10 -> {f_sp_call();}
			case 11 -> {isStop = true;}
			}
		}
		
		System.out.println("프로그램을 종료합니다.");

	}

	private static void f_sp_call() {
		System.out.println("조회할 직원 ID > ");
		int employee_id = sc.nextInt();
		EmpDTO emp = empService.execute_sp(employee_id);
		String message = "해당 직원은 존재하지 않습니다.";
		if(emp != null) {
			message = emp.getEmail() + " ----------- " + emp.getSalary();
		}
		EmpView.display(message);
		
	}

	private static void f_empUpdate() {
		System.out.println("수정할 직원 ID > ");
		int employee_id = sc.nextInt();
		EmpDTO exist_emp = empService.selectbById(employee_id);
		if(exist_emp == null) {
			EmpView.display("존재하지 않는 직원입니다.");
			return;
		}
		EmpView.display("============기존 직원의 정보입니다.============");
		EmpView.display(exist_emp);
		int result = empService.empUpdate(makeEmp(employee_id));
		EmpView.display(result + "건 수정");
	}

	private static void f_empInsert() {
		System.out.println("신규 직원 ID > ");
		int employee_id = sc.nextInt();
		 int result = empService.empInsert(makeEmp2(employee_id));
		 EmpView.display(result + "건 입력");
		
		
	}
	
	static EmpDTO makeEmp(int employee_id) {

		System.out.print("직원 first_name > ");
		 String first_name = sc.next();
		 System.out.print("직원 last_name > ");
		 String last_name = sc.next();
		 System.out.print("직원 email > ");
		 String email = sc.next();
		 System.out.print("직원 phone_number > ");
		 String phone_number = sc.next();
		 System.out.print("직원 hire_date > ");
		 String hdate = sc.next();
		 Date hire_date = null;
		 if(!hdate.equals("0")) hire_date = DateUtil.convertToSQLDate(DateUtil.convertToDate(hdate));     
		 System.out.print("직원 job_id(PK:IT_PROG) > ");
		 String job_id = sc.next();
		 System.out.print("직원 salary > ");
		 Double salary = sc.nextDouble();
		 System.out.print("직원 commission_pct(0.2) > ");
		 Double commission_pct = sc.nextDouble();
		 System.out.print("직원 manager_id(FK:100) > ");
		 Integer manager_id = sc.nextInt();
		 System.out.print("직원 department_id(PK:60,90) > ");
		 Integer department_id = sc.nextInt();
		 
		 if(first_name.equals("0")) first_name = null;
		 if(last_name.equals("0")) last_name = null;
		 if(email.equals("0")) email = null;
		 if(phone_number.equals("0")) phone_number = null;
		 if(job_id.equals("0")) job_id = null;
		 if(salary == 0) salary = null;
		 if(commission_pct == 0) commission_pct = null;
		 if(manager_id == 0) manager_id = null;
		 if(department_id == 0) department_id = null;
		 
		 
		 
		 EmpDTO emp = EmpDTO.builder()
				 .commission_pct(commission_pct)
				 .department_id(department_id)
				 .email(email)
				 .employee_id(employee_id)
				 .first_name(first_name)
				 .hire_date(hire_date)
				 .job_id(job_id)
				 .last_name(last_name)
				 .manager_id(manager_id)
				 .phone_number(phone_number)
				 .salary(salary)
				 .build();
		 return emp;
	}
	
	static EmpDTO makeEmp2(int employee_id) {

		System.out.print("직원 first_name > ");
		 String first_name = sc.next();
		 System.out.print("직원 last_name > ");
		 String last_name = sc.next();
		 System.out.print("직원 email > ");
		 String email = sc.next();
		 System.out.print("직원 phone_number > ");
		 String phone_number = sc.next();
		 System.out.print("직원 hire_date > ");
		 String hdate = sc.next();
		 Date hire_date = DateUtil.convertToSQLDate(DateUtil.convertToDate(hdate));     
		 System.out.print("직원 job_id(PK:IT_PROG) > ");
		 String job_id = sc.next();
		 System.out.print("직원 salary > ");
		 double salary = sc.nextDouble();
		 System.out.print("직원 commission_pct(0.2) > ");
		 double commission_pct = sc.nextDouble();
		 System.out.print("직원 manager_id(FK:100) > ");
		 int manager_id = sc.nextInt();
		 System.out.print("직원 department_id(PK:60,90) > ");
		 int department_id = sc.nextInt();
		 EmpDTO emp = EmpDTO.builder()
				 .commission_pct(commission_pct)
				 .department_id(department_id)
				 .email(email)
				 .employee_id(employee_id)
				 .first_name(first_name)
				 .hire_date(hire_date)
				 .job_id(job_id)
				 .last_name(last_name)
				 .manager_id(manager_id)
				 .phone_number(phone_number)
				 .salary(salary)
				 .build();
		 return emp;
	}

	private static void f_empDeleteById() {
		
		System.out.print("삭제할 직원의 Id > ");
		int empid = sc.nextInt();
		int result = empService.empDeleteById(empid);
		EmpView.display(result + "건 삭제");
		
	}

	private static void f_selectByCondition() {
		//부서, like 직책, >= 급여, >= 입사일
		System.out.print("조회할 부서의 코드(10,20,30)를 입력해주세요 > ");
		String[] str_deptid = sc.next().split(",");
		Integer[] deptidArr = Arrays.stream(str_deptid).map(Integer::parseInt).toArray(Integer[]::new);
		System.out.print("조회할 직책을 입력해주세요 > ");
		String jobid = sc.next();
		System.out.print("조회할 급여를 입력해주세요 > ");
		int salary = sc.nextInt();
		System.out.print("조회할 입사일을 입력해주세요(yyyy-mm-dd) > ");
		String hdate = sc.next();
		List<EmpDTO> emplist = empService.selectByCondition(deptidArr, jobid, salary, hdate);
		EmpView.display(emplist);
	}

	private static void f_selectByJobAndDept() {
		System.out.print("조회할 직책, 부서 코드를 입력해주세요 > ");
		String data = sc.next();
		String[] arr = data.split(",");
		String jobid = arr[0];
		int deptid = Integer.parseInt(arr[1]);
		List<EmpDTO> emplist = empService.selectByJobAndDept(jobid, deptid);
		EmpView.display(emplist);
	}

	private static void f_selectByJob() {
		System.out.print("조회할 직책을 입력해주세요 > ");
		String jobid = sc.next();
		List<EmpDTO> emplist = empService.selectByJob(jobid);
		EmpView.display(emplist);
	}

	private static void f_selectByDept() {
		System.out.print("조회한 부서의 코드를 입력해주세요 > ");
		int deptid = sc.nextInt();
		List<EmpDTO> emplist = empService.selectByDept(deptid);
		EmpView.display(emplist);
		
	}

	private static void f_selectById() {
		System.out.print("조회할 Id > ");
		int empid = sc.nextInt();
		EmpDTO emp = empService.selectbById(empid);
		EmpView.display(emp);
	}

	private static void f_selectAll() {
		List<EmpDTO> emplist = empService.selectAll();
		EmpView.display(emplist);
		
	}

	private static void menuDisplay() {
		
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("1.모두조회 2.조회(번호) 3.조회(부서) 4.조회(직책) 5.조회(직책,부서) 6.조회(상세) 7.삭제 8.직원 생성 9.직원 수정 10.sp call 11.종료");
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.print("작업을 선택해주세요. > ");
	}

}
