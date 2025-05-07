package com.shinhan.day15;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDTest2 {
	
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		Statement st = null;
		Statement st2 = null;
		String sql = """
				insert into GAME_USER values('gameer2', 141900, 6.131903793, to_date('2016-11-21 07:00:38', 'yyyy-mm-dd hh24:mi:ss'))
				""";
		String sql2 = """
				update game_user set id = 'jinho' where distance = 141900
				""";
		conn = DBUtil.getConnection();
		conn.setAutoCommit(false);
		st = conn.createStatement();
		int result1 = st.executeUpdate(sql);
		st2 = conn.createStatement();
		int result12 = st.executeUpdate(sql2);
		
		if(result1 >= 1 && result12 >= 1) {
			conn.commit();
			System.out.println("ok");
		} else {
			conn.rollback();
			System.out.println("fail");
		}
	}
	
	public static void f_4() throws SQLException {
		Connection conn = null;
		Statement st = null;
		int result = 0;
		String sql = """
			delete from emp1 where employee_id = 1
								""";
		conn = DBUtil.getConnection();
		st = conn.createStatement();
		result = st.executeUpdate(sql);
		System.out.println(result >= 0 ? "delete O" : "delete X");
	}

	public static void f_3() throws SQLException {
		Connection conn = null;
		Statement st = null;
		int result = 0;
		String sql = """
			update emp1
				set department_id =
					(select department_id
					from employees
					where employee_id = 100)
				, salary =
					(select salary
					from employees
					where employee_id = 101)
				where employee_id = 1
								""";
		conn = DBUtil.getConnection();
		st = conn.createStatement();
		result = st.executeUpdate(sql);
		System.out.println(result > 1 ? "update O" : "update X");
	}

	public static void f_2() throws SQLException {
		Connection conn = null;
		Statement st = null;
		int result = 0;
		String sql = """
				insert into emp1 values(4,'한','진호','www@naver.com', '폰', sysdate, 'job', 100, 0.2, 100, 20)
				""";
		conn = DBUtil.getConnection();
		st = conn.createStatement();
		result = st.executeUpdate(sql);
		System.out.println(result > 1 ? "s" : "f");
	}

	public static void f_1() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = """
				select ename, sal, mgr
				from emp
				where mgr = (
				select empno from emp where ename = 'KING')
				""";
		conn = DBUtil.getConnection();
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		while (rs.next()) {
			String a = rs.getString(1);
			int b = rs.getInt(2);
			int c = rs.getInt(3);
			System.out.println(a + "\t" + b + "\t" + c);
		}
		DBUtil.dbDisconnect(conn, st, rs);
	}

}
