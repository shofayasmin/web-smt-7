<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <title>WebApplication</title>
  
  <link href="dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="dist/css/bootstrap-theme.min.css" rel="stylesheet">
  <link href="assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
  <script src="assets/js/ie-emulation-modes-warning.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="index.jsp">WebApplication</a>
      <button type="button" class="navbar-toggle collapsed"
              data-toggle="collapse" data-target="#mainNav">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
     <div class="collapse navbar-collapse" id="mainNav">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/login.jsp">Login</a></li>
        <li><a href="${pageContext.request.contextPath}/products">Products</a></li>
        <li><a href="${pageContext.request.contextPath}/cart">Cart</a></li>

      </ul>
    </div>
  </div>
</nav>
<div class="container">