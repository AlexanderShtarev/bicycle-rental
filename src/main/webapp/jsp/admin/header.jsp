<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/admin/style.css"/>

</head>
<body>

<nav class="navbar">
    <ul class="navbar-nav">
        <li><a href="<c:url value="/controller?command=to_home_page"/>">Home</a></li>
        <li><a href="<c:url value="/controller?command=to_add_product_page"/>">Add Product</a></li>
        <li><a href="<c:url value="/controller?command=to_view_products_page"/>">View Products</a></li>
        <li><a href="<c:url value="/controller?command=to_search_product_page"/>">Search Product</a></li>
        <li style="float:right;margin-right:10px"><a href="<c:url value="/controller?command=logout"/>">Logout</a></li>
    </ul>
</nav>

</body>
</html>