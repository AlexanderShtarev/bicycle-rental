<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="page_content"/>
<html>
<head>
    <title><fmt:message key="error"/> 404</title>

    <%@ include file="/jspf/head.jspf" %>
</head>
<body>

<div class="error-404">
    <div class="error-code m-b-10 m-t-20">404 <i class="fa fa-warning"></i></div>
    <h2 class="font-bold"><fmt:message key="error.404.title"/></h2>

    <div class="error-desc">
        <fmt:message key="error.404.body"/><br/>
        <div><br/>
            <a href="<c:url value="/controller?command=to_home_page"/>" class="btn btn-primary"><span
                    class="glyphicon glyphicon-home"></span><fmt:message key="error.back-to-home-page"/></a>
        </div>
    </div>
</div>

</body>
</html>
