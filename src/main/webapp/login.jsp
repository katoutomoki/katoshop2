<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

	<h2>ログイン</h2>
	
	<form action="form-login" method="POST">
		氏名： <input type="text" class="short" name="name" required><br><br>
		ID  ：	<input type="text" class="short" name="id" required><br><br>
		<input type="submit" value="送信">
	</form>
	
	<% 
		String errorMsg = (String)request.getAttribute("errorMsg");
		if (errorMsg != null) {
	%>
			<p class="red"><%= errorMsg %></p>
	<%
			request.setAttribute("errorMsg", "");
		} 
	%>

</body>
</html>