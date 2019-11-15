<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="transactions" scope="request" type="java.util.List<ru.levelp.entity.Topic>"></jsp:useBean>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>

<h1>List topics</h1>

<table>
    <thead>
    <tr>
        <th>name</th>
    </tr>
    </thead>

    <tbody>

    <c:forEach items="${topics}" var="topic">
        <tr>
            <td>${topic.name}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>
