<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="UserServlet?comm=upd" method="post">
<input type="hidden" name="id" value="${param.id}">
用户名：<input name="username" value="${param.name}"><br>
密码：<input name="password" value="${param.password}"><br>
<input type="submit" value="修改">
</form>
</body>
</html>