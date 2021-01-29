<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/admin/style.css"/>

    <title>View Products</title>
</head>
<body>

<c:import url="header.jsp"/>

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
    <c:forEach items="${requestScope.products}" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.model}</td>
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
                                value="/controller?command=delete_product&productId=${requestScope.product.id}"/>'">
                    Delete
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>