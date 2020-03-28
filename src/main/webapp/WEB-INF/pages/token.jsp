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
      <form id="token-form">

        <div class="p-3 bottom-split-line">
          <h4 class="page-title color-dark mb-1">Token</h4>
          <p class="page-desc color-light m-0">
            Please enter the token provided by HR and the corresponding email.
          </p>
        </div>

        <div class="p-3 bottom-split-line">
          <div class="form-group">
            <label for="email" class="color-dark">Email:</label>
            <input class="form-control" type="text" name="email" id="email">
          </div>

          <div class="form-group">
            <label for="aesToken" class="color-dark">Token:</label>
            <input class="form-control" type="text" name="aesToken" id="aesToken">
          </div>

          <p id="error" class="col-md-10 color-red mb-0 mb-md-2 p-0" style="display:none"></p>
        </div>

        <div class="p-3">
          <button type="submit" class="btn login-btn btn-block">Submit</button>

          <div class="mt-3 d-flex justify-content-center align-items-center">
            <span class="register-link mr-2 color-dark">Have an account?</span>
            <a href="login?redirect=${redirect}" class="register-link">Login Here</a>
          </div>
        </div>

      </form>
    </div>

  </div>
</body>
</html>
