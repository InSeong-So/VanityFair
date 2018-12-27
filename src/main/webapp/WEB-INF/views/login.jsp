<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Login</title>
	<meta charset="UTF-8">
</head>
<body>
<h1>로그인</h1>
	<form action="userSelect" method="post">
		<input type="email" name="userEmail" placeholder="이메일을 입력하세요." maxlength="100" required="required"><br>
		<input type="password" name="userPassword" placeholder="비밀번호를 입력하세요." maxlength="10" required="required" ><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>
