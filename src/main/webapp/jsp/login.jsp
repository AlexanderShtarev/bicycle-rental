<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration Form</title>
</head>
<body>
<h1>Login Form</h1>
<form action="<c:url value="/controller"/>" method="post">
    <input type="hidden" name="command" value="login">
    <table style="with: 50%">
        <tr>
            <td>Email</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"/></td>
        </tr>
    </table>
    <input type="submit" value="Submit"/>
    <div>
        <h3>New User?<a href="<c:url value="/controller?command=to_register_page"/>">Register</a></h3>
    </div>
</form>
</body>
</html>
