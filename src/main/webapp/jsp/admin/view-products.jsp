<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="page_content"/>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <%@include file="/jspf/head.jspf" %>

    <title><fmt:message key="header.products"/></title>
</head>
<body>

<%@include file="header.jspf"%>

<div class="row">
    <c:if test="${not empty error}">
        <div class="alert alert-danger" id='danger'>${error}</div>
    </c:if>

    <div class="container">
        <h3 class="text-center"><fmt:message key="list.products"/></h3>
        <hr>
        <div class="container text-left">

            <button type="button" onclick="getAdditionalData()" class="btn btn-primary" data-toggle="modal"
                    data-target="#exampleModal"><fmt:message key="add.product"/>
            </button>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="label.name"/></th>
                <th><fmt:message key="main.manufacturer"/></th>
                <th><fmt:message key="main.category"/></th>
                <th><fmt:message key="product.price"/></th>
                <th><fmt:message key="main.action"/></th>
            </tr>
            </thead>
            <tbody id="products">
            <%--JS products list--%>
            </tbody>

        </table>
    </div>
</div>


<%@include file="new-product.jsp" %>

<script src="${pageContext.servletContext.contextPath}/js/view-product.js"></script>

</body>
</html>
