<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="transactions" scope="request" type="java.util.List<ru.levelp.entity.Message>"></jsp:useBean>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>

<h1>Topic name</h1>

<table>
    <thead>
    <tr>
        <th>nicName</th>
        <th>message</th>
    </tr>
    </thead>

    <tbody>

    <c:forEach items="${messages}" var="message">
        <tr>
            <td>${message.nicName}</td>
            <td>${message.message}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>
