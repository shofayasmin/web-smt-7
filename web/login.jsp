<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
    <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">

      <title>WebApplication</title>

      <!-- Bootstrap core CSS -->
      <link href="dist/css/bootstrap.min.css" rel="stylesheet">

      <!-- Optional Bootstrap theme -->
      <link href="dist/css/bootstrap-theme.min.css" rel="stylesheet">

      <!-- IE10 viewport hack -->
      <link href="assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

      <!-- IE emulation warning -->
      <script src="assets/js/ie-emulation-modes-warning.js"></script>
    </head>
    <body>
        <div class="container" style="max-width:480px; margin-top:40px;">
          <div class="panel panel-default">
            <div class="panel-heading text-center">
              <h3 class="panel-title">Login / Register</h3>
            </div>
            <div class="panel-body">

              <c:if test="${not empty message}">
                <div class="alert alert-info alert-dismissible fade in" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    ${message}
                </div>
              </c:if>

              <form action="auth" method="post" role="form">
                <div class="form-group">
                  <label for="firstName" class="control-label">First Name</label>
                  <input type="text" id="firstName" name="firstName" class="form-control" required>
                </div>

                <div class="form-group">
                  <label for="lastName" class="control-label">Last Name</label>
                  <input type="text" id="lastName" name="lastName" class="form-control" required>
                </div>

                <div class="form-group">
                  <label for="email" class="control-label">Email</label>
                  <input type="email" id="email" name="email" class="form-control" value="${savedEmail}"  required>
                </div>

                <div class="form-group">
                  <label for="password" class="control-label">Password</label>
                  <input type="password" id="password" name="password" class="form-control" required>
                </div>

                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="rememberMe" value="${savedEmail}"> Remember me
                    </label> 
                </div>
                
                <div class="row">
                  <div class="col-xs-6">
                    <input type="submit" name="action" value="Login" class="btn btn-primary btn-block">
                  </div>
                  <div class="col-xs-6">
                    <input type="submit" name="action" value="Register" class="btn btn-success btn-block">
                  </div>
                </div>
              </form>
            </div>
          </div>
      </div>

      <footer class="footer">
        <div class="container text-center">
          <p class="text-muted">&copy; 2025 WebApplication</p>
        </div>
      </footer>
        
      <!-- jQuery -->
      <script src="assets/js/vendor/jquery.min.js"></script>

      <!-- Bootstrap JS -->
      <script src="dist/js/bootstrap.min.js"></script>

      <!-- IE10 viewport hack -->
      <script src="assets/js/ie10-viewport-bug-workaround.js"></script>
    
    </body>
</html>