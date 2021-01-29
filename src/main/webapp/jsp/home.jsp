<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="../styles/styles.css">

</head>
<body>

<!-- jumbotron import -->
<c:import url="blocks/jumbotron.jsp"/>
<!-- navbar import -->
<c:import url="blocks/navbar.jsp"/>


<!-- row -->
<div class="row">

    <c:forEach items="${requestScope.featuredProducts}" var="product">
        <!-- col -->
        <div class="col-md-3">
            <!-- card -->
            <figure class="card card-product">
                <div class="img-wrap">
                    <img src="${product.image.imageLink}">
                    <a class="btn-overlay" href="#"><i class="fa fa-search-plus"></i> Quick view</a>
                </div>
                <figcaption class="info-wrap">
                    <h6 class="title text-dots"><a href="#">${product.model}</a></h6>
                    <!-- action-wrap -->
                    <div class="action-wrap">
                        <a href="#" class="btn btn-primary btn-sm float-right"> Order </a>
                        <!-- price-wrap -->
                        <div class="price-wrap h5">
                            <span class="price-new">${product.pricePerHour}</span>
                                <%--<del class="price-old">$1980</del>--%>
                        </div>
                        <!-- price-wrap // -->
                    </div>
                    <!-- action-wrap // -->
                </figcaption>
            </figure>
            <!-- card // -->
        </div>
        <!-- col // -->
    </c:forEach>
</div>
<!-- row // -->

<!-- footer import -->
<c:import url="blocks/footer.jsp"/>

</body>
</html>
