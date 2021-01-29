<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>


</head>
<body>

<!-- NavBar Import -->
<c:import url="blocks/navbar.jsp"/>

ERROR PAGE
_______________
<c:forEach items="${requestScope.error}" var="err">
    <c:out value="${err}"/>
</c:forEach>

<!-- Footer Import -->
<c:import url="blocks/footer.jsp"/>

</body>
</html>
