<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="common/head.jsp" %>
    <title>Sausage Fire | Token</title>
</head>

<body>
<div>
    <form method="post" id="token-form">
        <label>Email:
            <input type="text" name="email" id="email">
        </label>
        <label>Token:
            <input type="text" name="aesToken" id="aesToken">
        </label>
        <p id="error" class="col-md-10 color-red mb-0 mb-md-2 p-0" style="display:none"></p>
        <div>
            <button type="submit" class="btn btn-primary btn-block">Submit</button>
        </div>
    </form>
</div>
</body>
</html>
