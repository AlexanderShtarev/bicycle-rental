<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OTP</title>
</head>
<body>
<h1>User OTP</h1>
<form action="<c:url value="/controller"/>" method="POST">
    <input type="hidden" name="command" value="check_otp">
    <table style="with: 50%">
        <tr>
            <td>Token</td>
            <td><input type="text" name="token"/></td>
        </tr>
    </table>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
