<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/admin/style.css"/>

    <title>Edit Product</title>
</head>
<body>

<c:import url="header.jsp"/>

<div align="center">
    <form action="<c:url value="/controller"/>" method="post">
        <input type="hidden" name="command" value="update_product">

        <table class="productTable">
            <thead>
            <tr>
                <th colspan="2">
                    Product Details
                </th>
            </tr>
            </thead>
            <tr>
                <td>Product ID</td>
                <td><input type="text" name="prodId" size="20"
                           value="${requestScope.product.id}" class="productTextField" readonly/></td>
            </tr>
            <tr>
                <td>Product Type</td>
                <td><input type="text" name="prodType" size="20"
                           value="${requestScope.product.type.name}" class="productTextField"/></td>
            </tr>
            <tr>
                <td>Producer</td>
                <td><input type="text" name="prodProducer" size="20"
                           value="${requestScope.product.producer.name}" class="productTextField"/></td>
            </tr>
            <tr>
                <td>Model</td>
                <td><input type="text" name="model" size="20"
                           value="${requestScope.product.model}" class="productTextField"/></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" name="prodPrice" size="20"
                           value="${requestScope.product.pricePerHour}" class="productTextField"/></td>
            </tr>
        </table>
        <button type="submit" class="actionBtn" style="margin-top:10px">Save</button>
    </form>
</div>
</body>
</html>