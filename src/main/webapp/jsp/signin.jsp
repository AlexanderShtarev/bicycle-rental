<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="page_content"/>
<html>
<head>
    <title><fmt:message key="title.login"/></title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <%@ include file="/jspf/head.jspf" %>
</head>
<body>

<!-- Navbar -->
<%@ include file="/jspf/header.jspf" %>

<div id="login">
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12 border border-primary shadow rounded pt-2">
                    <form action="<c:url value="/controller"/>" method="post" novalidate>
                        <input type="hidden" name="command" value="login">
                        <h3 class="text-center text-info"><fmt:message key="label.login"/></h3>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">
                                    ${error}
                            </div>
                        </c:if>
                        <c:if test="${not empty errors}">
                            <div class="alert alert-danger">
                                <c:forEach var="error" items="${errors}">
                                    <li>${error}</li>
                                </c:forEach>
                            </div>
                        </c:if>
                        <input type="hidden" name="">
                        <div class="form-group">
                            <label for="inputEmail"><fmt:message key="label.email"/></label>
                            <input type="email" name="email" class="form-control" id="inputEmail"
                                   placeholder="<fmt:message key="label.email"/>" value="${email}" required
                                   pattern="^\w+([-\.]\w+)*@\w+([-\.]\w+)*\.\w+([-\.]\w+)*$">
                        </div>
                        <div class="form-group">
                            <label for="inputPassword"><fmt:message key="label.password"/></label>
                            <input type="password" value="${password}" name="password" class="form-control"
                                   id="inputPassword"
                                   placeholder="<fmt:message key="label.password"/>" required>
                        </div>
                        <button type="submit" class="btn btn-primary" style="margin-bottom: 10px"><fmt:message
                                key="button.sign_in"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Footer -->
<%@ include file="/jspf/footer.jspf" %>

</body>
</html>
