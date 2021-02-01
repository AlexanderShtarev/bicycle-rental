<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <title>My Account</title>

    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="../../styles/styles.css">


</head>
<body>

<!-- NavBar import -->
<c:import url="../blocks/navbar.jsp"/>

<hr>
<div class="container bootstrap snippet">
    <div class="row">
        <div class="col-sm-10"><h1>${sessionScope.user.name}</h1></div>
        <div class="col-sm-2"><a href="/users" class="pull-right"><img title="profile image" class="img-circle img-responsive" src="http://www.gravatar.com/avatar/28fd20ccec6865e2d5f0e1f4446eb7bf?s=100"></a></div>
    </div>
    <div class="row">
        <div class="col-sm-3"><!--left col-->


            <div class="text-center">
                <form>
                <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail" alt="avatar">
                <h6>Upload a different photo...</h6>
                <input type="file" class="text-center center-block file-upload">
                </form>
            </div>
            <br>

        </div><!--/col-3-->
        <div class="col-sm-9">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#home">Home</a></li>
                <li><a data-toggle="tab" href="#settings">Settings</a></li>
            </ul>


            <div class="tab-content">
                <div class="tab-pane active" id="home">
                    <hr>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label><h4>Full name: </h4></label>
                            <label><h4>${sessionScope.user.name}</h4></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-6">
                            <label><h4>Mobile</h4></label>
                            <label><h4>//todoGetPhone</h4></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="email"><h4>Email: </h4></label>
                            <label for="email"><h4>${sessionScope.user.email}</h4></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="first_name"><h4>Balance: </h4></label>
                            <label for="first_name"><h4>${sessionScope.user.balance}</h4></label>
                        </div>
                    </div>
                    <hr>

                </div><!--/tab-pane-->

                <div class="tab-pane" id="settings">

                    <hr>

                    <form class="form" action="##" method="post" id="registrationForm">

                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="first_name"><h4>Full name</h4></label>
                                <input type="text" class="form-control" name="first_name" id="first_name" placeholder="first name" title="enter your first name if any.">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-6">
                                <label for="mobile"><h4>Mobile</h4></label>
                                <input type="text" class="form-control" name="mobile" id="mobile" placeholder="enter mobile number" title="enter your mobile number if any.">
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="email"><h4>Email</h4></label>
                                <input type="email" class="form-control" name="email" id="email" placeholder="you@email.com" title="enter your email.">
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="password"><h4>Password</h4></label>
                                <input type="password" class="form-control" name="password" id="password" placeholder="password" title="enter your password.">
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="password2"><h4>Verify</h4></label>
                                <input type="password" class="form-control" name="cPassword" id="password2" placeholder="password2" title="enter your password2.">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-12">
                                <br>
                                <button class="btn btn-lg btn-success" type="submit"><i class="glyphicon glyphicon-ok-sign"></i> Save</button>
                            </div>
                        </div>

                    </form>

                </div>

            </div><!--/tab-pane-->
        </div><!--/tab-content-->

    </div><!--/col-9-->
</div><!--/row-->
<hr>

<c:import url="../blocks/footer.jsp"/>

</body>
</html>