<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠フォーム</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@include file = "header-navi1.jsp"%>
	<%@include file = "header-navi3.jsp"%>

	<h2>勤怠フォーム</h2>
	
	<form action="form-attendance" method="POST">
		勤怠：
		<select name="attendance">
			<option value="出勤">出勤</option>
			<option value="退勤">退勤</option>
			<option value="休憩（開始）">休憩（開始）</option>
			<option value="休憩（終了）">休憩（終了）</option>
		</select><br><br>
		<input type="submit" value="送信">
	</form>

</body>
</html>