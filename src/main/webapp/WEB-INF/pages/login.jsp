<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="common/head.jsp" %>
    <title>Login</title>
</head>

<body class="d-flex-column">
<div class="container-fluid d-flex-column flex">

    <div class="login-container m-auto d-flex">
        <form id="login-form" class="m-auto px-md-0 px-3">
            <div class="form-group d-flex pb-3">
                <h4 class="login-title m-auto">Login</h4>
            </div>

            <div class="form-group row">
                <span class="col-md-1"></span>
                <label for="username" class="col-md-3 col-form-label auth-label font-medium">USERNAME:</label>
                <div class="col-md-7">
                    <input class="form-control" name="username" id="username">
                </div>
                <span class="col-md-1"></span>
            </div>

            <div class="form-group row">
                <span class="col-md-1"></span>
                <label for="password" class="col-md-3 col-form-label auth-label font-medium">PASSWORD:</label>
                <div class="col-md-7">
                    <input type="password" class="form-control" name="password"
                           id="password">
                </div>
                <span class="col-md-1"></span>
            </div>

            <div class="form-group row m-0">
                <span class="col-md-1"></span>
                <p id="error" class="col-md-10 color-red mb-0 mb-md-2 p-0" style="display:none"></p>
                <span class="col-md-1"></span>
            </div>

            <div class="form-group row pt-2">
                <span class="col-md-1"></span>
                <div class="col-md-10">
                    <button type="submit" class="btn btn-primary btn-block">Login</button>
                </div>
                <span class="col-md-1"></span>
            </div>

            <div class="form-group row pt-1">
                <span class="col-md-1"></span>
                <div class="col-md-10">
                    <a href="token?redirect=${redirect}" class="btn btn-primary btn-block">Register</a>
                </div>
                <span class="col-md-1"></span>
            </div>
        </form>
    </div>
</div>
</body>