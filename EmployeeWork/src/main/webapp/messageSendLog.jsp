<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Message" %>
<%@ page import="model.Employee" %>
<%@ page import="dao.EmployeeDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メッセージ</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@include file = "header-navi1.jsp"%>
	<%@include file = "header-navi2.jsp"%>
	<%@include file = "header-navi3.jsp"%>
	
	<h2>メッセージ/送信履歴</h2>
	<p>送信履歴ページ。</p>
	
	<%
		EmployeeDAO dao = new EmployeeDAO();
		List<Message> ml = dao.getMessageList();
		if (ml.size() > 0) {
	%>
	
	<form action="remake-message" method="POST">
		<table class="table-list">
			<tr>
				<th>宛先</th><th>内容</th><th>日時</th><th>確認</th><th>削除</th>
			</tr>
			
	<%
			Employee em = (Employee)session.getAttribute("employee");
			String name = em.getName();
			
			for (int idx = 0; idx < ml.size(); idx++) {
				Message mess = ml.get(idx);
				if(name.equals(mess.getSenderName())){
	%>
	
			<tr>
				<td class="tableSize15"><%=mess.getRecipientName() %></td>
				<td class="tableSize50"><%=mess.getMessage() %></td>
				<td class="tableSize15"><%=mess.getDate() %></td>
				<td class="tableSize10">
					<input type="checkbox" name="read" value="<%=mess.getNumber() %>"
						<% if(mess.getReadNumber().equals("1")){ %> checked <% } %> disabled>
				</td>
				<td class="tableSize10">
					<input type="checkbox" name="delete" value="<%=mess.getNumber() %>" />
				</td>
			</tr>
			
	<%
				}
			}
	%>
	
		</table><br>
		<input type="hidden" name="jsp" value="messageSendLog.jsp">
		<input type="submit" value="更新">
	</form>
				
	<%
		} else {
	%>
	
	<p>送信履歴はありません。</p>
	
	<%
		}
	%>
	
</body>
</html>