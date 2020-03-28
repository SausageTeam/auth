<%@ page contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <%@include file="common/head.jsp" %>
  <title>Sausage Fire | Registration</title>
</head>

<body class="d-flex">

  <div class="fixed-top page-bg-container d-flex justify-content-center">
    <div class="d-flex mb-auto">
      <img class="logo" src="logo/colored-white-logo.svg">
      <div>
        <p class="m-0 title">Sausage Fire</p>
        <p class="m-0 subtitle">Onboarding System</p>
      </div>
    </div>
  </div>

  <div class="container-fluid d-flex m-auto">

    <div class="login-container m-auto box-shadow bg-white">
      <form id="registration-form">

        <div class="p-3 bottom-split-line">
          <h4 class="page-title color-dark mb-1">Registration</h4>
          <p class="page-desc color-light m-0">
            Please setup your username and password.
          </p>
        </div>

        <div class="p-3 bottom-split-line">

          <div class="form-group">
            <label for="username" class="color-dark">Username:</label>
            <input class="form-control" type="text" name="username" id="username">
          </div>

          <div class="form-group">
            <label for="password" class="color-dark">Password:</label>
            <input class="form-control" type="password" name="password" id="password">
          </div>

          <p id="error" class="col-md-10 color-red mb-0 mb-md-2 p-0" style="display:none"></p>
        </div>

          <div class="p-3">
            <button type="submit" class="btn login-btn btn-block">Register</button>
          </div>
      </form>
    </div>

  </div>

</body>
</html>