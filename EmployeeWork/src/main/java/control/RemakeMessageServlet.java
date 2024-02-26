package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;

@WebServlet("/remake-message")
public class RemakeMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// checkboxのパラメータ取得
		request.setCharacterEncoding("UTF-8");
		String[] readMessages = request.getParameterValues("read");
		String[] deleteMessages = request.getParameterValues("delete");
		
		//データ更新用のDAO取得
		EmployeeDAO dao = new EmployeeDAO();
		
		//確認（read）の更新
		if(readMessages!=null) { 
			for(int i=0; i<readMessages.length; i++) {
				dao.readUpdate(readMessages[i]);
			}
		}
		
		//削除（delete）処理
		if(deleteMessages!=null) {
			for(int i=0; i<deleteMessages.length; i++) {
				dao.deleteMessage(deleteMessages[i]);
			}
		}
		
		// 転送先設定
		String url = request.getParameter("jsp");

		// 転送
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
