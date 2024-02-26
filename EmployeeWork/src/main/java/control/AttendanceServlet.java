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

@WebServlet("/form-attendance")
public class AttendanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String attendance = request.getParameter("attendance");

        //Employeeオブジェクトのsessionとテーブルに、attendance情報の追加
        HttpSession session = request.getSession();	
        Employee emp = (Employee)session.getAttribute("employee");
        emp.setAttendance(attendance);
        session.setAttribute("employee", emp);
        
        //勤怠管理表に、Employeeオブジェクトを追加
        EmployeeDAO dao = new EmployeeDAO();
        dao.addAttend(emp, session);
        
        //従業員一覧の勤怠データ更新
        dao.updateNewAtttend(emp, session);
        
        // 転送
        String url = "attendanceList.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}