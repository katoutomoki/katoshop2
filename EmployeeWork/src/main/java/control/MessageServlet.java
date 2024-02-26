package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.Employee;
import model.Operation;

@WebServlet("/form-message")
public class MessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String recipient = request.getParameter("recipient");
        String message = request.getParameter("message");
        
        //不備の確認
        HttpSession session = request.getSession();	
		Operation op = new Operation();
		boolean result = op.messageProc(recipient, message, session);

		//転送先設定
		String url = "messageSendLog.jsp";
		
		//不備がなかった場合、メッセージテーブルに追加
		if(result) {
			EmployeeDAO dao = new EmployeeDAO();
			Employee emp = (Employee)session.getAttribute("employee");
	        String recipient_id = dao.searchId(recipient);
	        dao.addMessage(emp, recipient_id, message);
		} else {
			url = "messageForm.jsp";
		}
        
        // 転送
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}