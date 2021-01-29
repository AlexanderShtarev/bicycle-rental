<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Footer</title>

    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>

<footer class="container-fluid text-center" style="background-color: #f2f2f2; padding: 25px">

    <p>Online Store Copyright</p>

    <form class="form-inline" action="<c:url value="/controller"/>" method="POST">Get deals:
        <input type="hidden" name="command" value="get_deals">
        <input type="email" name="email" class="form-control" size="50" placeholder="Email Address">
        <button type="submit" class="btn btn-danger">Sign Up</button>
    </form>

</footer>

</body>
</html>