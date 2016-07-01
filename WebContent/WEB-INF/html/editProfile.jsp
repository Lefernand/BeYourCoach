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
		
		<%
		String nom;
		String prenom;
		Integer taille;
		Integer obj_poids;
		String email;
		String password;
		

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
		
	%>
		<div class="inner cover">
		  <h1 class="cover-heading">Editer mon profile</h1>
		  <div class="row">
		  	<div class="col-sm-offset-1 col-sm-4">
			  	<h3> Profile </h3>
			  	<form method="get" action="${action}">
					 <input type="hidden" name="typeAction" id="typeAction" value="profile">
					 <input type="hidden" name="id" id="id" value="<%=id%>">
					 
					 <div class="form-group">
					    <label for="nom">Nom</label>
					    <input type="text" class="form-control" name="nom" id="nom" value="<%=nom%>">
					 </div>
					 <div class="form-group">
					    <label for="prenom">Prenom</label>
					    <input type="text" class="form-control" name="prenom" id="prenom" value="<%=prenom%>">
					 </div>
					 <div class="form-group">
					    <label for="taille">Taille (cm)</label>
					    <input type="number" class="form-control" name="taille" id="taille" value="<%= taille %>">
					 </div>
					 
					 <div class="form-group">
					    <label for="objectif_poids">Objectif poids (kg)</label>
					    <input type="number" class="form-control" name="objectif_poids" id="objectif_poids" value="<%=obj_poids%>">
					 </div>
					 
					 <div class="form-group">
					    <label for="date_naissance">Date de naissance</label>
					    <input type="date" class="form-control" name="date_naissance" id="date_naissance" value="<%=date_naissance %>" >
					 </div>
					 
					 <button type="submit" class="btn btn-success">Submit</button>
				</form>
			</div>
		  	<div class="col-sm-offset-1 col-sm-4">
		  		<h3> Compte </h3>
				<form method="get" action="${action}">
					<input type="hidden" name="id" id="id" value="<%=id%>">
					 <input type="hidden" name="typeAction" id="typeAction" value="compte">
					 <div class="form-group">
					    <label for="email">Email</label>
					    <input type="text" class="form-control" name="email" id="email" value="<%=email%>">
					  </div>
					
					 <div class="form-group">
					    <label for="password">Password</label>
					    <input type="text" class="form-control" name="password" id="password" value="<%=password%>">
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