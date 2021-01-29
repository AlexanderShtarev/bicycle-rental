<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NavBar</title>

    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


</head>
<body>

<nav class="navbar navbar-inverse" style="margin-bottom: 50px; border-radius: 0;">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Logo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <%--<li class="active"><a href="#">Home</a></li>--%>
                <li><a href="<c:url value="/controller?command=to_home_page"/>">Home</a></li>
                <li><a href="<c:url value="/controller?command=to_products_page"/>">Products</a></li>
                <li><a href="<c:url value="/controller?command=to_about_page"/>">About</a></li>
                <li><a href="<c:url value="/controller?command=to_contact_page"/>">Contact</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">

                <!-- if user not logged in -->
                <c:if test="${sessionScope.user == null}">
                    <li><a href="<c:url value="/controller?command=to_register_page"/>"><span
                            class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="<c:url value="/controller?command=to_login_page"/>"><span
                            class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </c:if>

                <!-- if user logged in -->
                <c:if test="${sessionScope.user != null}">
                    <li><a href="<c:url value="/controller?command=to_profile_page"/>"><span
                            class="glyphicon glyphicon-user"></span> Your Account</a></li>
                    <li><a href="<c:url value="/controller?command=to_cart_page"/>"><span
                            class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
                    <li><a href="<c:url value="/controller?command=logout"/>"><span
                            class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                </c:if>

            </ul>
        </div>
    </div>
</nav>

</body>
</html>
