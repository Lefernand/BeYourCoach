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


		<div class="inner cover">
		  <h1 class="cover-heading">Mon compte&nbsp;<a href="profileEdition" class="btn btn-xs btn-info">Editer</a></h1>
		  <h4 class="cover-heading">Voici les informations de votre compte</h4>
				
		  <div class="row">
		  	<div class="col-sm-offset-1 col-sm-4">
			  		
				
				<div>
					<p>Login : <%= user.getLogin() %>  </p>
					<p>Nom : <%= user.getNom() %>  </p>
					<p>Prenom : <%= user.getPrenom() %>  </p>
					<p>Email : <%= user.getEmail() %>  </p>
				</div>
				
			</div>
		  	<div class="col-sm-offset-1 col-sm-4">
				
				<div>
					<p>Ma taille : <%= user.getTaille() %> cm</p>
					<p>Mon poids : A AJOUTER kg</p>
					<p>Mon objectif poids: <%= user.getObjectif_poids() %> kg</p>
					<p> date de naissance : <%= user.getDate_naissance() %></p>
					<p> inscrit le : <%= user.getDate_creation() %></p>
				</div>
			  	
		  	</div>
		  </div>
		</div>
    </div>

  </div>

</div>
	
</body>
</html>