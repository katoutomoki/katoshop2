package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import model.Employee;

public class EmployeeDAO{
	
	//従業員一覧のリスト作成
	public List<Employee> getEmpList() throws SQLException {
		List<Employee> empList = new ArrayList<Employee>();

		String sql = "SELECT * FROM t_emp  ORDER BY department DESC";

		try (Connection con = MyConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(sql)) {

			while (res.next()) {
				String name = res.getString("name");
				String id = res.getString("id");
				String department = res.getString("department");
				Employee emp = new Employee(name, id, department);
				
				empList.add(emp);
			}
		}
		return empList;
	}
	
	
	//勤怠管理表のリストの作成
	public List<Employee> getAttendList(){
		List<Employee> empAttendList = new ArrayList<Employee>();
		
		String sql = "SELECT * FROM t_emp_attend  ORDER BY update_date DESC";
		
		try (Connection con = MyConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(sql)) {

			while (res.next()) {
				String name = res.getString("name");
				String id = res.getString("id");
				String department = res.getString("department");
				String attendance = res.getString("attendance");
				String date = res.getString("update_date");
				Employee empAttend = new Employee(name, id, department, attendance, date);
				
				empAttendList.add(empAttend);
			}
		} catch (SQLException e) {
			System.out.println("\nエラーが発生しました。");
		}
		return empAttendList;
	}
	
	
	//勤怠リストへの登録
	public void addAttend(Employee emp, HttpSession session){
	    String sql = "INSERT INTO t_emp_attend (name, id, department, attendance) VALUES (?, ?, ?, ?)";
	    
	    try (Connection con = MyConnection.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, emp.getName());
	        pstmt.setString(2, emp.getId());
	        pstmt.setString(3, emp.getDepartment());
	        pstmt.setString(4, emp.getAttendance());
	        
	        int res = pstmt.executeUpdate();
	        System.out.println(res + "件、勤怠登録しました。");
	    } catch (SQLException e) {
	        System.out.println("\n勤怠リスト登録時に、エラーが発生しました。");
	        e.printStackTrace(); 
	    }
	}
	
	
	//IDによる名前検索
	public String selectNameById(String id) {
		String name = null;
		String sql = "SELECT name FROM t_emp WHERE id = ? ";
		try (Connection con = MyConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			try (ResultSet res = pstmt.executeQuery()) {	
				if (res.next()) {
					name = res.getString("name");
				}
			}
        } catch (SQLException e) {
            System.out.println("名前の取得中にエラーが発生しました: " + e.getMessage());
        }
		return name;
	}
	
	
	//IDによる部署検索
	public String selectDepartmentById(String id) {
		String department = null;
		String sql = "SELECT department FROM t_emp WHERE id = ? ";
		try (Connection con = MyConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			try (ResultSet res = pstmt.executeQuery()) {	
				if (res.next()) {
					department = res.getString("department");
				}
			}
        } catch (SQLException e) {
            System.out.println("部署の取得中にエラーが発生しました: " + e.getMessage());
        }
		return department;
	}
	
}
