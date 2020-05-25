<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Object obj=session.getAttribute("user");
	if(obj==null||obj==""){
		response.sendRedirect("login.jsp");
		return;
	}

%>
用户：${user.name}<span><a href="LoginOutServlet">退出</a></span><br/>
<a href="add_user.jsp">添加</a>

<table>
<tr>
	<td>编号</td>
	<td>用户名</td>
	<td>密码</td>
	<td>操作</td>
</tr>
<c:forEach items="${ulist}" var="ul">
<tr>
	<td>${ul.id}</td>
	<td>${ul.name}</td>
	<td>${ul.password}</td>
	<td><a href="UserServlet?comm=del&id=${ul.id}">删除</a><a href="up_user.jsp?id=${ul.id}&name=${ul.name}&password=${ul.password}">修改</a></td>
</tr>
</c:forEach>

</table>

</body>
</html>