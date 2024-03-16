package model;

import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;

public class Operation {
	
	//ーーーログイン認証ーーーーー
	// ユーザ情報取得用のDAO（インスタンス）
	private static final EmployeeDAO dao = new EmployeeDAO();
	
	//ログイン認証
	private boolean loginCheck(String name, String id) {
		boolean result = name.equals(dao.selectNameById(id));
		return result;
	}
	
	//処理
	public boolean loginProc(String name, String id, HttpSession session) {
		boolean result = loginCheck(name, id);
		if (result) {
			String department = dao.selectDepartmentById(id);
			Employee loginUser = new Employee(name, id, department);
			session.setAttribute("employee", loginUser);
		}
		return result;
	}
	
	
	//ーーーメッセージフォームの認証ーーーーー
	public boolean messageProc(String recipient, String message, HttpSession session) {
		boolean result = true;
		if(dao.searchId(recipient)==null) {
			result = false;
			session.setAttribute("errorMsg", "従業員が見つかりませんでした。");
			System.out.println("従業員が見つかりませんでした。");
		}
		return result;
	}
	
	 //ーーーログアウト時の処理ーーーーー
	public void logoutProc(HttpSession session) {
		session.invalidate();
	}

}
