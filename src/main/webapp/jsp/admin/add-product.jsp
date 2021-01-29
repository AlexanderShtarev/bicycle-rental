<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/admin/style.css"/>

    <title>Add Product</title>

</head>
<body>

<c:import url="header.jsp"/>

<div align="center">
    <form action="<c:url value="/controller"/>" method="POST">
        <input type="hidden" name="command" value="add_product">
        <table class="productTable">
            <thead>
            <tr>
                <th colspan="2">
                    Product Details
                </th>
            </tr>
            </thead>
            <tr>
                <td>Product Name</td>
                <td><input type="text" name="prodName" size="20"
                           class="productTextField"/></td>
            </tr>
            <tr>
                <td>Type</td>
                <td><input type="text" name="prodCategory" size="20"
                           class="productTextField"/></td>
            </tr>
            <tr>
                <td>Producer</td>
                <td><input type="text" name="prodCategory" size="20"
                           class="productTextField"/></td>
            </tr>
            <tr>
                <td>Model</td>
                <td><input type="text" name="prodCategory" size="20"
                           class="productTextField"/></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" name="prodPrice" size="20"
                           class="productTextField"/></td>
            </tr>
        </table>
        <button type="submit" class="actionBtn" style="margin-top:10px">Add</button>
    </form>
</div>

</body>
</html>