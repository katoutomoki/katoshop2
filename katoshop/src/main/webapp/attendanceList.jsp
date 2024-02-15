<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Employee" %>
<%@ page import="dao.EmployeeDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理表</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@include file = "header-navi.jsp"%>
	
	<h2>勤怠管理表</h2>
	<p>勤怠管理表のページです。</p>
	
	<%
		EmployeeDAO dao = new EmployeeDAO();
		List<Employee> al = dao.getAttendList();
		if (al.size() > 0) {
	%>
			<table class="table-list">
			<tr>
				<th>氏名</th><th>ID</th><th>部署</th><th>勤怠ステータス</th><th>日時</th>
			</tr>
	<%
			for (int idx = 0; idx < al.size(); idx++) {
				Employee emp1 = al.get(idx);
	%>
				<tr>
					<td><%=emp1.getName() %></td>
					<td><%=emp1.getId() %></td>
					<td><%=emp1.getDepartment() %></td>
					<td><%=emp1.getAttendance() %></td>
					<td><%=emp1.getDate() %></td>
				</tr>			
	<%
			}
	%>
			</table>
				
	<%
		} else {
	%>
			<p>出退勤ログはありません。</p>
	<%
		}
	%>
	
</body>
</html>