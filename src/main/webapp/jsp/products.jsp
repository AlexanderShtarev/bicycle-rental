<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="page_content"/>
<html>
<head>
    <title><fmt:message key="title.products"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="/jspf/head.jspf" %>

</head>
<body>

<%@include file="/jspf/header.jspf" %>

<%@include file="/jspf/filter.jspf" %>

<div class="container">
    <c:if test="${empty products}">
        <div class="text-center">
            <div class="col-md-offset-4 col-md-4">
                <div class="alert alert-danger"><fmt:message key="product.not_found"/></div>
            </div>
        </div>
    </c:if>
</div>

<div class="container">
    <div class="row">

        <c:forEach items="${products}" var="product">

            <div class="col-sm-3 col-lg-3 col-md-3">
                <div class="thumbnail">
                    <div class="caption">
                        <h4 class="pull-right">$ ${product.price}</h4>
                        <h4>
                            <a href="#" style="outline: none;">${product.name}</a>
                        </h4>

                        <p>
                            <fmt:message key="main.category"/>: ${product.category.name}<br/>
                            <fmt:message key="main.manufacturer"/>: ${product.manufacturer.name}<br/>
                                ${product.description}
                        </p>
                        <button type="button" class="btn btn-info" role="button" name="cart_add_button"
                                productId="${product.id}">
                            <fmt:message key="button.add_to_cart"/>
                        </button>
                    </div>
                </div>
            </div>

        </c:forEach>

        <%@include file="/jspf/cart-action.jspf" %>

    </div>
</div>

<script src="${pageContext.servletContext.contextPath}/js/products.js"></script>
<%@include file="/jspf/footer.jspf" %>
</body>
</html>
