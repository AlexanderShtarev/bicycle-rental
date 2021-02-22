<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="page_content"/>
<html>
<head>
    <title>Update product</title>

    <%@ include file="/jspf/head.jspf" %>
</head>
<body>

<%@include file="header.jspf" %>

<form action="<c:url value="/controller"/>" role="form" method="get">
    <input type="hidden" name="command" value="update_product">
    <input type="hidden" name="productId" value="${product.id}">
    <div class="col-xs-12">
        <p class="lead"></p>
        <div class="form-group">
            <label for="name"><fmt:message key="label.name"/></label>
            <div class="controls">
                <input type="text" class="form-control" id="name" name="name"
                       value="${product.name}">
            </div>
        </div>


        <div class="row">
            <div class="controls col-xs-12">
                <label for="to"><fmt:message key="product.price"/>:</label>
                <div class="controls">
                    <input min=1 type="text" class="form-control" id="to" name="price"
                           pattern="\d+(\.\d+)?" value="${product.price}">
                </div>
            </div>
        </div>

        <div class="btn-group">
            <select name="categoryId">
                <c:forEach var="category" items="${categories}">
                    <c:choose>
                        <c:when test="${product.category.id == category.id}">
                            <option value="${category.id}" selected>${category.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${category.id}">${category.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="btn-group">
            <select name="manufacturerId">
                <c:forEach var="manufacturer" items="${manufacturers}">
                    <c:choose>
                        <c:when test="${product.manufacturer.id == manufacturer.id}">
                            <option value="${manufacturer.id}" selected>${manufacturer.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${manufacturer.id}">${manufacturer.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>

    </div>

    <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="button.close"/></button>
        <button type="submit" class="btn btn-primary"><fmt:message key="button.save"/></button>
    </div>
</form>

<%@ include file="/jspf/footer.jspf" %>

</body>
</html>
