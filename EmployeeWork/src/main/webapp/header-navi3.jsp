<%@ page pageEncoding="UTF-8"%>
<%@ page import="model.Employee" %>
<%@ page import="dao.EmployeeDAO" %>

<%
	EmployeeDAO headDao = new EmployeeDAO();
	Employee emp = (Employee)session.getAttribute("employee");
	int count = headDao.countMessage(emp.getName());
	if(count==0){
%>

	<p>未確認メッセージは、ありません。</p>
	
<%
	}else{
%>

	<p>未確認メッセージが、<span class="red"><%= count %></span>件あります。</p>
	
<%
	}
%>