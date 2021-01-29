<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/admin/style.css"/>

    <title>Home</title>
</head>
<body>

<c:import url="header.jsp"/>

<div align="center">
    <h2>Product Management System</h2>
    <label>Welcome ${sessionScope.user.name}</label>
</div>

</body>
</html>