package model;

import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;

public class Operation {
	
	// ユーザ情報取得用のDAO（インスタンス）
	private static final EmployeeDAO dao = new EmployeeDAO();
	
	//ログイン認証
	private boolean check(String name, String id) {
		boolean result = name.equals(dao.selectNameById(id));
		return result;
	}
	
	//ログイン認証と、その後の処理
	public boolean loginProc(String name, String id, HttpSession session) {
		boolean result = check(name, id);
		if (result) {
			String department = dao.selectDepartmentById(id);
			Employee loginUser = new Employee(name, id, department);
			session.setAttribute("employee", loginUser);
		}
		return result;
	}
	
	 //ログアウト時の処理
	public void logoutProc(HttpSession session) {
		session.invalidate();
	}

}
