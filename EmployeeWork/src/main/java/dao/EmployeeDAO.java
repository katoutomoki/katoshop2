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
import model.Message;

public class EmployeeDAO{
	
	//ーーーテーブル作成ーーーーー
	
	//①従業員一覧のテーブル作成
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
				String newAttendance = res.getString("new_attendance");
				Employee emp = new Employee(name, id, department, newAttendance);
				
				empList.add(emp);
			}
		}
		return empList;
	}
	
	
	//②勤怠管理表のテーブル作成
	public List<Employee> getAttendList(){
		List<Employee> empAttendList = new ArrayList<Employee>();
		
		String sql = "SELECT name, id, department, attendance, DATE_FORMAT(update_date, '%m/%d %k:%i') FROM t_emp_attend  ORDER BY update_date DESC";
		
		try (Connection con = MyConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(sql)) {

			while (res.next()) {
				String name = res.getString("name");
				String id = res.getString("id");
				String department = res.getString("department");
				String attendance = res.getString("attendance");
				String date = res.getString("DATE_FORMAT(update_date, '%m/%d %k:%i')");
				Employee empAttend = new Employee(name, id, department, attendance, date);
				
				empAttendList.add(empAttend);
			}
		} catch (SQLException e) {
			System.out.println("\nEmployeeDAOのテーブル②で、エラーが発生しました。");
		}
		return empAttendList;
	}
	
	
	//③メッセージログのテーブル作成
	public List<Message> getMessageList(){
		List<Message> messageList = new ArrayList<Message>();
		
		String sql = "SELECT number, sender_name, recipient_name, message, DATE_FORMAT(update_date, '%m/%d %k:%i'), read_number FROM t_message  ORDER BY update_date DESC";
		
		try (Connection con = MyConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(sql)) {

			while (res.next()) {
				String number = res.getString("number");
				String sender_name = res.getString("sender_name");
				String recipient_name = res.getString("recipient_name");
				String message = res.getString("message");
				String update_date = res.getString("DATE_FORMAT(update_date, '%m/%d %k:%i')");
				String read_number = res.getString("read_number");
				Message messageInfo = new Message(number, sender_name, recipient_name, message, update_date, read_number);
				
				messageList.add(messageInfo);
			}
		} catch (SQLException e) {
			System.out.println("\nEmployeeDAOのテーブル③で、エラーが発生しました。");
		}
		return messageList;
	}
	
	
	//ーーー各テーブルへの操作ーーーーー
	
	//①勤怠管理表テーブルへの、要素追加
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
	        System.out.println("\n勤怠リスト登録時(DAO①)に、エラーが発生しました。");
	        e.printStackTrace(); 
	    }
	}
	
	
	//②従業員テーブルの、勤怠データ更新
	public void updateNewAtttend(Employee emp, HttpSession session){
	    String sql = "UPDATE t_emp SET new_attendance=? WHERE id=?";
	    
	    try (Connection con = MyConnection.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, emp.getAttendance());
	        pstmt.setString(2, emp.getId());
	        
	        int res = pstmt.executeUpdate();
	        System.out.println(res + "件、勤怠更新しました。");
	    } catch (SQLException e) {
	        System.out.println("\n勤怠更新時(DAO②)に、エラーが発生しました。");
	        e.printStackTrace(); 
	    }
	}
	
	
	//③メッセージログのテーブルに、メッセージ追加
	public void addMessage(Employee emp, String recipient_id, String message){
	    String sql = "INSERT INTO t_message (sender_id, recipient_id, sender_name, recipient_name, message) VALUES (?, ?, ?, ?, ?)";
	    
	    try (Connection con = MyConnection.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, emp.getId());
	        pstmt.setString(2, recipient_id);
	        pstmt.setString(3, emp.getName());
	        pstmt.setString(4, selectNameById(recipient_id));
	        pstmt.setString(5, message);
	        
	        int res = pstmt.executeUpdate();
	        System.out.println(res + "件、メッセージ登録しました。");
	    } catch (SQLException e) {
	        System.out.println("\nメッセージ登録時(DAO③)に、エラーが発生しました。");
	        e.printStackTrace(); 
	    }
	}
	
	
	//④IDによる名前検索
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
            System.out.println("名前の取得中(DAO④)にエラーが発生しました: " + e.getMessage());
        }
		return name;
	}
	
	
	//⑤IDによる部署検索
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
            System.out.println("部署の取得中(DAO⑤)にエラーが発生しました: " + e.getMessage());
        }
		return department;
	}
	
	//⑥ID,名前が存在するかの検索
	public String searchId(String nameId) {
		String recipient_id = null;
		String sql = "SELECT id FROM t_emp WHERE name = ? OR id = ?";
		
		try (Connection con = MyConnection.getConnection();
			   PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, nameId);
			pstmt.setString(2, nameId);
			try (ResultSet res = pstmt.executeQuery()) {	
				if (res.next()) {
					recipient_id = res.getString("id");
				}
			}
        } catch (SQLException e) {
            System.out.println("DAO⑥で、従業員が見つかりませんでした。: " + e.getMessage());
        }
		return recipient_id;
	}
	
	//⑦メッセージの確認を更新
	public void readUpdate(String number) {
		String sql = "UPDATE t_message SET read_number='1', update_date=update_date WHERE number = ? ";
		
		try (Connection con = MyConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, number);
			int num = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("メッセージ確認の更新中(DAO⑦)にエラーが発生しました: " + e.getMessage());
        }
	}
	
	//⑧メッセージを削除
	public void deleteMessage(String number) {
		String sql = "DELETE FROM t_message WHERE number = ? ";
		
		try (Connection con = MyConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, number);
			int num = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("メッセージ削除中(DAO⑧)にエラーが発生しました: " + e.getMessage());
        }
	}
	
	//⑨未確認メッセージのカウント
	public int countMessage(String recipient_name) {
		int count = 0;
		String sql = "SELECT number FROM t_message WHERE recipient_name = ? AND read_number =  '0'";
		
		try (Connection con = MyConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
					
			pstmt.setString(1, recipient_name);
			try (ResultSet res = pstmt.executeQuery()) {	
				while (res.next()) {
					count++;
				}
			}
		} catch (SQLException e) {
            System.out.println("未確認メッセージカウント中(DAO⑨)にエラーが発生しました: " + e.getMessage());
        }
		return count;
	}
	
}
