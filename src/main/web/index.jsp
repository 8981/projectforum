<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <style type="text/css">
        .error{
            color: red;
        }
    </style>
</head>
<>
<h1>Hello</h1>

<c:if test="${empty sessionScope['topicId']}">
<form method="post" action="/login">
    <p>nicName: <input type="nicName" name="nicName"></p>
    <p>Login: <input type="text" name="login" value="${param['login']}"></p>
    <p>Password: <input type="password" name="password"></p>
    <p><input type="submit"></p>
</form>
</c:if>

<c:if test="${not empty sessionScope['topicId']}">
    <p><a href="/dashboard">Dashboard</a></p>
</c:if>

<c:if test="${not empty param['login']}">
<p class="error">
    Login or password incorrect!
</p>
</c:if>

</body>
</html>
