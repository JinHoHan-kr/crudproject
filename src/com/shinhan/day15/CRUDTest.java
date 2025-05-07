package com.shinhan.day15;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//CRUD(Create Read Update Delete)
//Read(Select)
public class CRUDTest {
	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = """
				select department_id, max(salary), min(salary)
				from employees
				group by department_id
				having max(salary) <> min(salary)
								""";

		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int a = rs.getInt(1);
				int b = rs.getInt(2);
				int c = rs.getInt(3);
				System.out.println(a + "\t" + b + "\t" + c);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}

	}

	public static void f_2() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "hr", userpass = "hr";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = """
				select first_name, salary, hire_date
				from employees
				where length(first_name) <= 5
						 		""";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, userid, userpass);
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int deptid = rs.getInt(1);
				int cnt - rs.getInt(2);
				System.out.println("부서코드");
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

	}

	public static void f_1() throws ClassNotFoundException, SQLException {
		// 1.JDBC Driver 준비(class path 추가)
		// 2.JDBC Driver load
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("2.JDBC Driver load");
		// 3.Connection
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "hr", userpass = "hr";
		Connection conn = DriverManager.getConnection(url, userid, userpass);
		System.out.println("Connection");
		// 4.SQL 문을 보낼 통로 얻기
		Statement st = conn.createStatement();
		System.out.println("sql 통로");
		String sql = """
				select *
				from employees
				 where department_id = 80
				""";
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			int empid = rs.getInt("employee_id");
			String fname = rs.getString("first_name");
			Date hdate = rs.getDate("hire_date");
			double comm = rs.getDouble("commission_pct");
			System.out.printf("직원번호 : %d 이름 %s hdate : %s comm : %3.1f\n", empid, fname, hdate, comm);

		}
		rs.close();
		st.close();
		conn.close();

	}

}
