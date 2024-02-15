<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Employee" %>
<%@ page import="dao.EmployeeDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員一覧</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@include file = "header-navi.jsp"%>
	
	<h2>従業員一覧</h2>
	<p>従業員一覧ページです。</p>
	<p>（勤怠ステータス、作成中）</p>
	
	<%
		EmployeeDAO dao = new EmployeeDAO();
		List<Employee> el = dao.getEmpList();
	%>
			<table class="table-list">
			<tr>
				<th>氏名</th><th>ID</th><th>部署</th><th>勤怠ステータス</th>
			</tr>
	<%
		for (int idx = 0; idx < el.size(); idx++) {
			Employee emp1 = el.get(idx);
	%>
			<tr>
				<td><%=emp1.getName() %></td>
				<td><%=emp1.getId() %></td>
				<td><%=emp1.getDepartment() %></td>
				<td><%=emp1.getAttendance() %></td>
			</tr>			
	<%
		}
	%>
			</table>
					
</body>
</html>