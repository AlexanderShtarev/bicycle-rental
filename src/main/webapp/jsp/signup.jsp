<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="page_content"/>
<html>
<head>
    <title><fmt:message key="title.registration"/></title>

    <script src="${pageContext.request.contextPath}/js/validation.js"></script>

    <%@include file="/jspf/head.jspf" %>

</head>
<body>
<%@include file="/jspf/header.jspf" %>

<div class="container">
    <div id="login-row" class="row justify-content-center align-items-center">
        <div id="login-column" class="col-md-6">
            <div id="login-box" class="col-md-12 border border-primary shadow rounded pt-2">
                <form action="<c:url value="/controller"/>" method="post" name="registrationForm">
                    <input type="hidden" name="command" value="register">
                    <h3 class="text-center text-info"><fmt:message key="title.registration"/></h3>
                    <c:if test="${not empty error}">
                        <div id="error" class="alert alert-danger">
                                ${error}
                        </div>
                    </c:if>
                    <c:if test="${not empty errors}">
                        <div id="errors" class="alert alert-danger">
                            <c:forEach var="error" items="${errors}">
                                <li>${error}</li>
                            </c:forEach>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label for="firstname"><fmt:message key="label.name"/></label>
                        <input type="text" name="name" value="${formBean.name}" class="form-control" id="firstname"
                               placeholder="firstname"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="lastname"><fmt:message key="label.surname"/></label>
                        <input type="text" name="surname" value="${formBean.surname}" class="form-control"
                               id="lastname" placeholder="lastname"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="email"><fmt:message key="label.email"/></label>
                        <input type="email" name="email" value="${formBean.email}" class="form-control" id="email"
                               placeholder="Email"
                               required>
                        <small><fmt:message key="info.wi_will_sent_you_a_message"/></small>
                    </div>
                    <div class="form-group">
                        <label for="password"><fmt:message key="label.password"/></label>
                        <input type="password" name="password" class="form-control" id="password"
                               placeholder="Password" required>
                    </div>
                    <button type="submit" class="btn btn-primary submit-button" onclick="validate()" style="margin-bottom: 10px"><fmt:message
                            key="button.sign_in"/></button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/jspf/footer.jspf" %>

</body>
</html>