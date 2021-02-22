<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="page_content"/>
<html>
<head>

    <title><fmt:message key="title.home"/></title>
    <%@include file="/jspf/head.jspf" %>

</head>
<body id=main>
<c:if test="${not empty requestScope.error}">
    <div class="alert alert-danger" id='danger'>${requestScope.error}</div>
</c:if>

<div class="jumbotron" style=" margin-bottom: 0;">
    <div class="container text-center">

        <h1><fmt:message key="main.find_bicycle"/></h1>
        <p class="text-white text-center"><fmt:message key="main.search_title"/></p>

        <div class="card">
            <div class="card-body">
                <div class="row">
                    <form action="<c:url value="/controller"/>" method="GET">
                        <input type="hidden" name="command" value="to_products_page">
                        <div class="col-lg-3">
                            <span><fmt:message key="main.manufacturer"/></span>
                            <select name="manufacturers" class="mt-2" id="manufacturers">
                                <option selected disabled><fmt:message key="choose.manufacturer"/></option>
                            </select>
                        </div>

                        <div class="col-lg-3">
                            <span><fmt:message key="main.category"/></span>
                            <select name="categories" class="mt-2" id="categories">
                                <option selected disabled><fmt:message key="choose.category"/></option>
                            </select>
                        </div>

                        <div class="col-lg-3">
                            <span><fmt:message key="product.name"/></span>
                            <input name="name" placeholder="<fmt:message key="product.name"/>">
                        </div>

                        <div class="col-lg-3">
                            <input type="submit" class="btn1" value="<fmt:message key="main.search"/>">
                        </div>
                    </form>

                </div>
            </div>
        </div>

    </div>
</div>

<%@include file="/jspf/header.jspf" %>

<div class="row">
    <c:if test="${not empty error}">
        <div class="alert alert-danger" id='danger'>${error}</div>
    </c:if>

    <div class="container">
        <h3 class="text-center"><fmt:message key="latest.products"/></h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
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
<br>

<br><br>

<script src="${pageContext.servletContext.contextPath}/js/home.js"></script>

<%@include file="/jspf/cart-action.jspf" %>
<%@include file="/jspf/footer.jspf" %>

</body>
</html>
