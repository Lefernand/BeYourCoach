<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page import="fr.esgi.model.User"/>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="java.io.*"/>
<jsp:directive.page import="java.util.Date"/>

<html>
<jsp:directive.include file="header.jsp" />
<body class="login">
<div class="site-wrapper">

  <div class="site-wrapper-inner">

    <div class="cover-container">

		<jsp:directive.include file="navBar.jsp" />
		
		<div class="inner cover">
		  <h1 class="cover-heading">Editer mon profile</h1>
		  <div class="row">
		  	<div class="col-sm-offset-1 col-sm-10">
		  		<jsp:directive.include file="error.jsp" />
		  		
				<%
					String nom;
					String prenom;
					Integer taille;
					Integer obj_poids;
					String email;
					String password;
					Boolean sexe;
					
			
					Integer id = user.getId();
					
					String login = user.getLogin();
					
					if(user.getNom() == null){
						nom = "";
					}else{
						nom = user.getNom();
					}
					
					if(user.getPrenom() == null){
						prenom = "";
					}else{
						prenom = user.getPrenom();
					}
					
					if(user.getTaille() == null){
						taille = 0;
					}else{
						taille = user.getTaille();
					}
					
					if(user.getObjectif_poids() == null){
						obj_poids = 0;
					}else{
						obj_poids = user.getObjectif_poids();
					}
					
					Date date_naissance = user.getDate_naissance();
					
					if(user.getEmail() == null){
						email = "";
					}else{
						email = user.getEmail();
					}
					
					if(user.getPassword() == null){
						password = "";
					}else{
						password = user.getPassword();
					}
					
					if(user.getSexe() == null){
						sexe = true;
					}else{
						sexe = user.getSexe();
					}
					
				%>
		  		
		  		<jsp:directive.include file="profileForm.jsp" />
		  	</div>
		  </div>
		</div>
    </div>

  </div>

</div>

</body>
</html>