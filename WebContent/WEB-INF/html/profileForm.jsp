
<form method="get" action="${action}">
 	<div class="col-sm-offset-1 col-sm-4">
  	<h3> Profile </h3>
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
		 
		 <div class="form-group">
		    <label for="sexe">Sexe </label>
		<select name="sexe">
		  <option value="1" <% if(sexe.equals(true)){%>selected<% } %>>Homme</option> 
		  <option value="0" <% if(sexe.equals(false)){%>selected<% } %>>Femme</option>
		</select>
		</div>
</div>
 	<div class="col-sm-offset-1 col-sm-4">
 		<h3> Compte </h3>
 		
		 <div class="form-group">
		    <label for="login">Login</label>
		    <input type="text" class="form-control" name="login" id="login" value="<%=login%>">
		  </div>
		  
		 <div class="form-group">
		    <label for="email">Email</label>
		    <input type="text" class="form-control" name="email" id="email" value="<%=email%>">
		  </div>
		
		 <div class="form-group">
		    <label for="password">Password</label>
		    <input type="password" class="form-control" name="password" id="password" value="<%=password%>">
		 </div>
					
		 <div class="form-group">
		    <label for="password2">Confirmer password</label>
		    <input type="password" class="form-control" name="password2" id="password2">
		 </div>
		  
		 <% if(request.getAttribute("action") == "registerForm"){ %>
		 	<button type="submit" class="btn btn-success">S'inscrire</button>
		 	<button type="button" class="btn btn-danger">Connexion</button>
		 <% } else { %>
		 	<button type="button" class="btn btn-success">Enregistrer</button>
		 <% } %>
 	</div>
</form>