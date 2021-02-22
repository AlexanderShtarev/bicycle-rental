<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="page_content"/>
<html>
<head>
    <title><fmt:message key="title.cart"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="/jspf/head.jspf" %>

</head>
<body>

<%@include file="/jspf/header.jspf" %>
<div class="container">
    <c:if test="${not empty error}">
        <div id="error" class="alert alert-danger">
                ${error}
        </div>
    </c:if>
    <c:if test="${not empty errors}">
        <div id="errors" class="alert alert-danger">
            <c:forEach var="error" items="${errors}">
                <li>${error}</li>
            </c:forEach>
        </div>
    </c:if>
    <c:choose>
        <c:when test="${empty cartBean.productMap}">
            <div class="text-center">
                <div class="col-md-offset-4 col-md-4">
                    <div class="alert alert-danger"><fmt:message key="cart.empty"/></div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col-sm-12 col-md-10 col-md-offset-1">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th><fmt:message key="product.name"/></th>
                            <th><fmt:message key="product.qty"/></th>
                            <th class="text-center"><fmt:message key="product.price"/></th>
                            <th class="text-center"><fmt:message key="total"/></th>
                            <th> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="map" items="${cartBean.productMap}">
                            <tr id="product_${map.getKey().id}">
                                <td class="col-sm-8 col-md-6">
                                    <div class="media">
                                        <a class="thumbnail pull-left" href="#">
                                            <img class="media-object"
                                                 src="#"
                                                 style="width: 72px; height: 72px;"> </a>
                                        <div class="media-body">
                                            <h4 class="media-heading"><a href="#">${map.getKey().name}</a></h4>
                                            <h5 class="media-heading"> by <a
                                                    href="#">${map.getKey().manufacturer.name}</a>
                                            </h5>
                                            <span>Status: </span><span
                                                class="text-success"><strong>In Stock</strong></span>
                                        </div>
                                    </div>
                                </td>
                                <td class="col-sm-1 col-md-1" style="text-align: center">
                                    <button type="button" role="button" class="btn btn-success"
                                            name="cart_decrement_button"
                                            productId="${map.getKey().id}">-
                                    </button>
                                    <button type="button" role="button" class="btn btn-success"
                                            name="cart_increment_button"
                                            productId="${map.getKey().id}">+
                                    </button>
                                    <span id="product_count_${map.getKey().id}">${map.value}</span>
                                </td>
                                <td class="col-sm-1 col-md-1 text-center">
                                    <strong>${map.getKey().price}</strong>
                                </td>
                                <td class="col-sm-1 col-md-1 text-center"><strong>
                                    <span id="product_cost_${map.getKey().id}">${map.getKey().price * map.value}</span>
                                </strong></td>
                                <td class="col-sm-1 col-md-1">
                                    <button type="button" class="btn btn-danger" name="cart_delete_button"
                                            productId="${map.getKey().id}">
                                        <span class="glyphicon glyphicon-remove"></span> Remove
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>  </td>
                            <td>  </td>
                            <td>  </td>
                            <td><h3>Total</h3></td>
                            <td class="text-right"><h3><strong><span id="total_price"></span></strong></h3></td>
                        </tr>
                        <tr>
                            <td>  </td>
                            <td>  </td>
                            <td>  </td>
                            <td>
                                <button onclick="chooseTime()" type="button" class="btn btn-success">
                                    Choose Time <span class="glyphicon glyphicon-play"></span>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script type="text/javascript">
    function chooseTime() {
        $("#getCodeModal").modal('show');
    }
</script>
<script src="${pageContext.servletContext.contextPath}/js/cart.js"></script>
<%@ include file="/jspf/footer.jspf" %>

</body>
</html>


<div class="modal fade" id="getCodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"> Rental Time </h4>
            </div>

            YOU NEED TO WAIT WHILE OUR ADMINS WILL CONFIRM YOUR REQUEST

            <div class="modal-body" name="message" id="getCode">
                Choose Time
            </div>

            <form action="<c:url value="/controller"/>" method="post">
                <input type="hidden" name="command" value="rent">
                <label for="rentFrom">Rent From</label>
                <input id="rentFrom" name="rentFrom" type="datetime-local">

                <label for="rentTo">Rent To</label>
                <input id="rentTo" name="rentTo" type="datetime-local">

                <button type="submit" class="btn btn-success">
                    Submit <span class="glyphicon glyphicon-play"></span>
                </button>
            </form>

        </div>
    </div>
</div>
