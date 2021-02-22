<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="page_content"/>
<html>
<head>
    <title><fmt:message key="title.profile"/></title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <%@include file="/jspf/head.jspf" %>

    <script type="text/javascript">
        $(document).ready(function () {
            getRentals();

            $("#myBtn").click(function () {
                $("#myModal").modal();
            });

        })

        function myFunction() {
            var x = document.getElementById("myDIV");
            if (x.style.display === "none") {
                x.style.display = "block";
            } else {
                x.style.display = "none";
            }
        }

        function getRentals() {
            $.ajax({
                type: "POST",
                url: 'http://localhost:8080/final_war_exploded/test',
                data: {
                    action: 'view_user_rentals'
                },
                success: function (answer) {
                    console.log("user rentals received")
                    showRentals(answer)
                },
                error: function () {
                    alert("can't get user rentals");
                }
            });

            function showRentals(answer) {
                console.log('showing rentals')
                answer = $.parseJSON(answer)
                var content = "";
                $.each(answer, function (i, item) {
                    content = content + "<tr>";
                    content = content + showRow(item.rentalDate);
                    content = content + showRow(item.returnDate);
                    content = content + showRow(item.status);
                    content = content + "</tr>";
                    $('#rentals').html(content);
                })

                function showRow(value) {
                    return "<td>" + value + "</td>";
                }
            }

        }
    </script>
</head>
<body>

<%@include file="/jspf/header.jspf" %>

<hr>
<div class="container bootstrap snippet">
    <div class="row">
        <div class="col-sm-10"><h1>${sessionScope.user.name} ${user.surname}</h1></div>
    </div>
    <div class="row">

        <!--/col-3-->
        <div class="col-sm-9">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#home"><fmt:message key="header.home"/></a></li>
                <li><a data-toggle="tab" href="#settings"><fmt:message key="user.settings"/></a></li>
                <li><a data-toggle="tab" href="#cards"><fmt:message key="main.cards"/></a></li>
                <li><a data-toggle="tab" href="#rentals-tab"><fmt:message key="main.rentals"/></a></li>
            </ul>


            <div class="tab-content">
                <div class="tab-pane active" id="home">
                    <hr>
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                                ${error}
                        </div>
                    </c:if>
                    <c:if test="${not empty errors}">
                        <div class="alert alert-danger">
                            <c:forEach var="error" items="${errors}">
                                <li>${error}</li>
                            </c:forEach>
                        </div>
                    </c:if>

                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="email"><h4><fmt:message key="label.email"/>:</h4></label>
                            <label for="email"><h4>${sessionScope.user.email}</h4></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="email"><h4><fmt:message key="main.balance"/>:</h4></label>
                            <label for="email"><h4>${sessionScope.user.balance}</h4></label>
                        </div>
                    </div>

                    <hr>

                </div><!--/tab-pane-->

                <div class="tab-pane" id="settings">

                    <hr>

                    <form class="form" action="<c:url value="/controller"/>" method="post" id="registrationForm">
                        <input type="hidden" name="command" value="update_user">
                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="first_name"><h4><fmt:message key="label.name"/></h4></label>
                                <input type="text" class="form-control" name="name" id="first_name"
                                       placeholder="first name" title="enter your first name if any.">
                            </div>
                        </div>

                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="last_name"><h4><fmt:message key="label.surname"/></h4></label>
                                <input type="text" class="form-control" name="surname" id="last_name"
                                       placeholder="first name" title="enter your first name if any.">
                            </div>
                        </div>

                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="email"><h4><fmt:message key="label.email"/></h4></label>
                                <input type="email" class="form-control" name="email" id="email"
                                       placeholder="you@email.com" title="enter your email.">
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="password"><h4><fmt:message key="label.password"/></h4></label>
                                <input type="password" class="form-control" name="password" id="password"
                                       placeholder="password" title="enter your password.">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-12">
                                <br>
                                <button class="btn btn-lg btn-success" type="submit"><i
                                        class="glyphicon glyphicon-ok-sign"></i> <fmt:message key="button.save"/>
                                </button>
                            </div>
                        </div>

                    </form>

                </div>

                <div class="tab-pane" id="cards">
                    <%@include file="cards.jspf" %>
                </div><!--/tab-pane-->

                <div class="tab-pane" id="rentals-tab">
                    <div class="container">
                        <hr>
                        <br>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th><fmt:message key="tr.rentFrom"/></th>
                                <th><fmt:message key="tr.rentTo"/></th>
                                <th><fmt:message key="label.status"/></th>
                            </tr>
                            </thead>
                            <tbody id="rentals">
                            <%--JS products list--%>
                            </tbody>

                        </table>
                    </div>
                    <!-- JS rentals -->
                </div><!--/tab-pane-->

            </div><!--/tab-content-->

        </div><!--/col-9-->
    </div><!--/row-->
    <hr>

    <%@include file="/jspf/footer.jspf" %>

    <script src="${pageContext.servletContext.contextPath}/js/profile.js"></script>

</body>
</html>
