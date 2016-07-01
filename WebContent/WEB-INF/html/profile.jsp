<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page import="fr.esgi.model.User"/>
<jsp:directive.page import="java.util.List"/>

<html>
<jsp:directive.include file="header.jsp" />
<body>
<jsp:directive.include file="navBar.jsp" />
	
	<h1>${action}</h1>
	
	<a href="profileEdition" class="btn btn-info"> Editer </a>
	
	<div>
		<h2> login : <%= user.getLogin() %>  </h2>
		<h2> email : <%= user.getEmail() %>  </h2>
		<h2> password : <%= user.getPassword() %>  </h2>
	</div>
	
	<hr>
	
	<div>
		<h2> nom : <%= user.getNom() %>  </h2>
		<h2> prenom : <%= user.getPrenom() %>  </h2>
		<h2> taille : <%= user.getTaille() %> cm  </h2>
		<h2> Votre objectif poids est : <%= user.getObjectif_poids() %> kg </h2>
		<h2> date de naissance : <%= user.getDate_naissance() %>  </h2>
		<h2> inscrit le : <%= user.getDate_creation() %>  </h2>
	</div>
	
	<hr>
</body>
</html>