<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
ERROR PAGE
_______________
<c:forEach items="${requestScope.error}" var="err">
    <c:out value="${err}"/>
</c:forEach>
</body>
</html>
