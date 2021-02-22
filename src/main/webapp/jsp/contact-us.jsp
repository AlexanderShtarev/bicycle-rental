<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="page_content"/>
<html>
<head>
    <title><fmt:message key="header.contact"/></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="../jspf/head.jspf" %>

    <style type="text/css">
        .jumbotron {
            background: #358CCE;
            color: #FFF;
            border-radius: 0px;
        }

        .jumbotron-sm {
            padding-top: 24px;
            padding-bottom: 24px;
        }
    </style>

</head>
<body>
<div class="jumbotron jumbotron-sm">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-lg-12">
                <h1 class="h1">
                    <fmt:message key="header.contact"/></h1>
            </div>
        </div>
    </div>
</div>

<%@include file="../jspf/header.jspf" %>

<div class="container">
    <div class="row">
        <div class="col-md-8">
            <div class="well well-sm">
                <form action="<c:url value="/controller"/>" method="GET">
                    <input type="hidden" name="command" value="contact_us">
                    <div class="row">
                        <div class="col-md-6">
                            <c:if test="${not empty message}">
                                <div id="error" class="alert alert-success">
                                        ${message}
                                </div>
                            </c:if>
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
                                <label for="name">
                                    <fmt:message key="label.name"/></label>
                                <input type="text" class="form-control" id="name" value="${user.name}" name="name"
                                       placeholder="<fmt:message key="label.name"/> " required="required"/>
                            </div>
                            <div class="form-group">
                                <label for="email">
                                    <fmt:message key="label.email"/></label>
                                <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span>
                                </span>
                                    <input type="email" value="${user.email}" name="email" class="form-control"
                                           id="email"
                                           placeholder="<fmt:message key="label.email"/> "
                                           required="required"/></div>
                            </div>

                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="name">
                                    <fmt:message key="label.message"/></label>
                                <textarea name="message" id="message" class="form-control" rows="9" cols="25"
                                          required="required"
                                          placeholder="<fmt:message key="label.message"/>"></textarea>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <button type="submit" class="btn btn-primary pull-right" id="btnContactUs">
                                <fmt:message key="button.send"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="../jspf/footer.jspf" %>

</body>
</html>
