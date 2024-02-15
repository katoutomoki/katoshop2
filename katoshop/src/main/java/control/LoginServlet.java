package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Operation;

@WebServlet("/form-login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String id = request.getParameter("id");

        // ログイン処理
        HttpSession session = request.getSession();	
		Operation op = new Operation();
		boolean result = op.loginProc(name, id, session);

        // 転送先設定
        String url = "attendanceForm.jsp";
        if (!result) {
			request.setAttribute("errorMsg", "ユーザID または パスワードに 誤りがあります。");	
			url = "login.jsp";
		}

        // 転送
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
