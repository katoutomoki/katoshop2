<%@ page pageEncoding="UTF-8"%>
<%@ page import="model.Employee" %>

<%
	Employee emp2 = (Employee)session.getAttribute("employee");
	if(emp2 != null) {
%>

	<h1>かとう商店</h1>
	<hr>
	氏名：<%=emp2.getName() %><br>
	 ID ：<%=emp2.getId() %>
	<hr>
	
		<a href="attendanceForm.jsp">勤怠フォーム</a> | 
		<a href="attendanceList.jsp">勤怠管理表</a> | 
		<a href="employeeList.jsp">従業員一覧</a> | 
		<a href="messageForm.jsp">メッセージフォーム</a>・
		<a href="messageLog.jsp">ログ</a> | 
		<a href="logout-servlet">ログアウト</a>
	<hr>
	
<%
    } else {
		System.out.println("emp2オブジェクトが作れませんでした。");
    }
%>