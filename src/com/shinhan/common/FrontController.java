package com.shinhan.common;

import java.util.Scanner;

import com.shinhan.dept.DeptController;
import com.shinhan.emp.EmpController;

//MVC2 모델
//frontcontroller패턴 : controller가 여러개인 경우 사용자의 요청과 응답은 출구가 여러개
//FrontController -> contTRoller -> service -> dao -> db
public class FrontController {
	
	public static void main(String[] args) {
		//사용자가 emp, dept 선택하게 제공
		Scanner sc = new Scanner(System.in);
		boolean isOver = false;
		CommonControllerInterface controller = null;
		while(!isOver) {
			System.out.println("1. emp || 2. dept");
			System.out.print(">> ");
			String job = sc.next();
			
			switch(job) {
			case "emp" -> {controller = ControllerFactory.make("emp");}
			case "dept" -> {controller = ControllerFactory.make("dept");}
			case "job" -> {controller = ControllerFactory.make("job");}
			case "end" -> {isOver = true; continue;}
			default -> {continue;}
			
			}
			controller.execute(); //작업이 달라져도 사용법은 같다. (전략패턴)
		}
		sc.close();
		System.out.println("main end");
	}

}
