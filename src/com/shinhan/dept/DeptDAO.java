package com.shinhan.dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.shinhan.day15.DBUtil;
//1.select 모두보기 2. select 상세보기 3.insert 4. update 5. delete
public class DeptDAO {
	//DB연결, 해제
	Connection conn;
	//SQL문 DB에 전송
	Statement st;
	PreparedStatement pst;
	//select 결과
	ResultSet rs;
	//insert,delete,update 결과는 영향받은 건수
	int resultCount;
	
	//delete
	public int deleteDept(int deptid) {
		String sql = "delete from departments where department_id = ?";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return resultCount;
	}
	
	//update
	public int updateDept(DeptDTO dept) {
		String sql = """
				update departments set Department_name = ?,
				Manager_id = ?, Location_id = ? where department_id = ?
				""";

		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(4, dept.getDepartment_id());
			pst.setString(1, dept.getDepartment_name());
			pst.setInt(2, dept.getManager_id());
			pst.setInt(3, dept.getLocation_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return resultCount;
	}
	
	// inset
	public int insertDept(DeptDTO dept) {
		String sql = "insert into deptartments values(?,?,?,?)";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, dept.getDepartment_id());
			pst.setString(2, dept.getDepartment_name());
			pst.setInt(3, dept.getManager_id());
			pst.setInt(4, dept.getLocation_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return resultCount;
	}
	
	// 상세 조회
	public DeptDTO deptSelectTag(int department_id) {
		DeptDTO dept = null;
		conn = DBUtil.getConnection();
		String sql = "select * from deptartments where department_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, rs.getInt(department_id));
			rs = pst.executeQuery();
			while(rs.next()) {
			dept = makeDept(rs); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return dept;
	}
	
	// 전체 조회하기
	public List<DeptDTO> deptSelectAll() {
		List<DeptDTO> deptList = new ArrayList<>();
		conn = DBUtil.getConnection();
		String sql = "select * from deptartments";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				DeptDTO dept = makeDept(rs); 
				deptList.add(dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		
		return deptList;
		
	}

	private DeptDTO makeDept(ResultSet rs) throws SQLException {
		DeptDTO dept = DeptDTO.builder()
				.department_id(rs.getInt("department_id"))
				.department_name(rs.getString("department_id"))
				.location_id(rs.getInt("department_id"))
				.manager_id(rs.getInt("department_id"))
				.build();
		
		return dept;
	}

}
