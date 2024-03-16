<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
	
	<h2>メッセージ/フォーム</h2>
	<p>メッセージフォームです。</p>
	
	<form action="form-message" method="POST">
		▼宛先（氏名またはID）<br><input type="text" class="short" name="recipient" required><br><br>
		▼内容<br><textarea class="long" name="message" required></textarea><br><br>
		<input type="submit" value="送信">
	</form>
	
	<% 
		String errorMsg = (String)session.getAttribute("errorMsg");
		if (errorMsg != null) {
	%>
			<p class="red"><%= errorMsg %></p>
	<%
			session.setAttribute("errorMsg", "");
		} 
	%>

</body>
</html>