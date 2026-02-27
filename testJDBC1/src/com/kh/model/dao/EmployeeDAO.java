package com.kh.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Employee;

public class EmployeeDAO {

	public ArrayList<Employee> selectAll() {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
			// 0. Class.forName()을 통한 Driver 등록 (필수 x)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//1. DriverManager.getConnection()을 통한 계정 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "SCOTT", "SCOTT");
			
			//System.out.println(conn);
			
			//2. 쿼리 작성
			String query = "select * from emp";
			
			//3. Connection을 통한 Statement객체 생성
			stmt = conn.createStatement();
			
			//4. 메소드를 통한 쿼리 전달 및 반환 값 받아오기
			rset = stmt.executeQuery(query);
			while(rset.next()) {
			  int empNo  = rset.getInt("EMPNO");
			  String empName = rset.getString("ENAME");
			  String job = rset.getString("JOB");
			  int mgr = rset.getInt("MGR");
			  Date hireDate = rset.getDate("HIREDATE");
			  int sal = rset.getInt("SAL");
			  int comm = rset.getInt("COMM");
			  int deptNo = rset.getInt("DEPTNO");
			  Employee emp = new Employee(empNo, empName, job, mgr, hireDate, sal, comm, deptNo);
			  list.add(emp);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public Employee selectEmployee(int empNo) {
		Connection conn = null;
		//Statement stmt =null;
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		
		Employee emp = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","SCOTT","SCOTT");
			
			//String query = "select * from emp where empno = " + empNo;//완성형 쿼리(Statement)
			//stmt = conn.createStatement();
			//rset = stmt.executeQuery(query);

			String query = "select * from emp where empno = ?";// 미완성형 쿼리(PreparedStatement)
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,empNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				emp = new Employee(empNo, rset.getString("ENAME")
						,rset.getString("JOB")
						,rset.getInt("MGR")
						,rset.getDate("HIREDATE")
						,rset.getInt("SAL")
						,rset.getInt("COMM") 
						,rset.getInt("DEPTNO"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return emp;
	}

	public int insertEmployee(Employee emp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SCOTT","SCOTT");
			conn.setAutoCommit(false);
			
			//insert into emp values(9999, 'rose', 'teacher',7788,sysdate,5000,100,10)
			//String query = "insert into emp values("+ emp.getEmpNo() + ", '"+ emp.getEmpName() +"', '"+emp.getJob()+"',"+emp.getMgr()+",sysdate,"+emp.getSal()+","+emp.getComm()+","+emp.getDeptNo()+")";
			
			String query = "insert into emp values(?,?,?,?,sysdate,?,?,?)";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, emp.getEmpNo());
	        pstmt.setString(2, emp.getEmpName());
	        pstmt.setString(3, emp.getJob());
	        pstmt.setInt(4, emp.getMgr());
	        pstmt.setInt(5, emp.getSal());
	        pstmt.setInt(6, emp.getComm());
	        pstmt.setInt(7, emp.getDeptNo());
	        
	        result = pstmt.executeUpdate();
	        if(result > 0 ) {
	        	conn.commit();
	        }else {
	        	conn.rollback();
	        }
	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
