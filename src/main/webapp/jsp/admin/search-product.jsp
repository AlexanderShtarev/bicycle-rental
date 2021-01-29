<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/admin/style.css"/>

    <title>Search Product</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div align="center" style="padding-top:25px;">
    <form action="<c:url value="/controller"/>" method="GET">
        <input type="hidden" name="command" value="search_product">
        <label>Enter Product ID: </label>
        <input type="text" name="prodId" size="25" class="searchTextField"/>
        <button type="submit" class="actionBtn">Search</button>
    </form>
</div>
<table align="center" class="productTable">
    <thead>
    <tr>
        <th>Product ID</th>
        <th>Product Name</th>
        <th>Category</th>
        <th>Price</th>
        <th colspan="2">Actions</th>
    </tr>
    </thead>
    <tr>
        <td>${requestScope.product.id}</td>
        <td>${requestScope.product.model}</td>
        <td>
            <button class="actionBtn"
                    onclick="location.href = '<c:url
                            value="/controller?command=to_update_product_page&productId=${requestScope.product.id}"/>'">
                Edit
            </button>
        </td>
        <td>
            <button class="actionBtn"
                    onclick="location.href = '<c:url
                            value="/controller?command=delete_product&productId=${requestScope.product.id}"/>'">Delete
            </button>
        </td>
    </tr>
</table>

</body>
</html>