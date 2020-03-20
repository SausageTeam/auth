<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="common/head.jsp" %>
    <title>Registration</title>
</head>

<body>
<div>
    <form method="post" id="registration-form">
        <label>Username:
            <input type="text" name="username" id="username">
        </label>
        <label>Password:
            <input type="password" name="password" id="password">
        </label>
        <p id="error" class="col-md-10 color-red mb-0 mb-md-2 p-0" style="display:none"></p>
        <div>
            <button type="submit" class="btn btn-primary btn-block">Submit</button>
        </div>
    </form>
</div>
</body>
</html>