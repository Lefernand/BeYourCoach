<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page import="java.util.List"/>

<html>
<jsp:directive.page contentType="text/html; charset=ISO-8859-1" />
<jsp:directive.page import="fr.esgi.model.User"/>

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>BeYourCoach</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/admin.css" rel="stylesheet">
    <link href="css/cover.css" rel="stylesheet">
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

    <script src="js/bootstrap.min.js"></script>
    <!-- ChartJS 1.0.1 -->
    <script src="js/Chart.min.js"></script>
    <script src="js/main.js"></script>
    
  </head>
  
  <body class="profile">

<div class="site-wrapper">

  <div class="site-wrapper-inner">

    <div class="cover-container">

		<div class="inner cover">
		  <h1 class="cover-heading text-center"> Créer un compte sur  be your Coatch </h1>
		  <% if(request.getParameter("errorMessage") != null) { %>
	<div class="alert alert-danger" role="alert">
		${ errorMessage }
	</div>
<% } %>
<% if(request.getParameter("success") != null) { %>
<div class="alert alert-success" role="alert">
	${ success }
</div>
<% } %>
<% if(request.getParameter("infoMessage") != null) { %>
<div class="alert alert-info" role="alert">
	${ infoMessage }	
</div>
<% } %>
${ errorMessage }
		  <div class="row">
		  	<div class="col-sm-offset-3 col-sm-6">
		  		<form method="get" action="registerForm">
					 <div class="form-group">
					    <label for="login">Login</label>
					    <input type="text" class="form-control" name="user" id="login" placeholder="Enter login">
					  </div>
					
					 <div class="form-group">
					    <label for="password">Password</label>
					    <input type="text" class="form-control" name="password" id="password" placeholder="Enter password">
					  </div>
					  
					  <div class="form-group">
					    <label for="email">email</label>
					    <input type="text" class="form-control" name="email" id="email" placeholder="Enter email">
					  </div>
					  
					  <button type="submit" class="btn btn-success">Submit</button>
				</form>
		  	</div>
		  </div>
		</div>
    </div>

  </div>

</div>

</body>
</html>