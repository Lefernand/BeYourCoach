<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page import="fr.esgi.model.User"/>
<jsp:directive.page import="java.util.List"/>

<html>
<jsp:directive.include file="header.jsp" />
<body class="login">

<div class="site-wrapper">

  <div class="site-wrapper-inner">

    <div class="cover-container">

		<jsp:directive.include file="navBar.jsp" />

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

		<div class="inner cover">
		  <h1 class="cover-heading">Connexion</h1>
		  <div class="row">
		  	<div class="col-sm-offset-3 col-sm-6">
		  		<jsp:directive.include file="error.jsp" />
			  	<form method="get" action="${action}">
				 <div class="form-group">
				    <label for="login"></label>
				    <input type="text" class="form-control" name="user" id="login" placeholder="Entrez votre login">
				  </div>
				
				 <div class="form-group">
				    <label for="password"></label>
				    <input type="password" class="form-control" name="password" id="password" placeholder="Entrez votre password">
				  </div>
				  
				  <button type="submit" class="btn btn-default">Se connecter</button>
				  <a href="registerPage" type="button" class="btn btn-info">Créer un compte</a>
				</form>
		  	</div>
		  </div>
		</div>
    </div>

  </div>

</div>
	
</body>
</html>