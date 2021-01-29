<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>OTP Page</title>

    <!-- Required meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS Scripts -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css"/>

</head>
<body id="auth">

<section id="cover" class="min-vh-100">
    <div id="cover-caption">
        <div class="container">
            <div class="row text-white">
                <div class="col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
                    <h1 class="display-4 py-2 text-truncate">OTP Form</h1>
                    <div class="px-2">
                        <form action="<c:url value="/controller"/>"
                              class="needs-validation justify-content-center" method="POST" novalidate>
                            <input type="hidden" name="command" value="check_otp">
                            <div class="form-group">
                                <input type="text" class="form-control" id="inputPassword"
                                       placeholder="Password" name="otp" required>
                                <div class="invalid-feedback">Please enter your otp to continue.</div>
                            </div>
                            <button type="submit" class="btn btn-primary">Confirm</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="<c:url value="/js/auth.js"/>"></script>

</body>
</html>