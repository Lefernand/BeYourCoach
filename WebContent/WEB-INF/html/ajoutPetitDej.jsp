<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page import="fr.esgi.model.User"/>
<jsp:directive.page import="java.util.List"/>

<html>
<jsp:directive.include file="header.jsp" />
<body>
<jsp:directive.include file="navBar.jsp" />
	<h1>Ajouter un petit dej</h1>
	
	<div class="form-group">
	    <label for="search">Recherche</label>
		<input list="aliments" type="text" class="form-control" name="recherche" id="recherche" placeholder="Recherche">
		
		<button id="recherche-btn" type="submit" class="btn btn-success">Rechercher</button>
	</div>
	
	<div id="choix">
	</div>
	
	<form method="get" action="ajoutPetitDej" id="ajoutPetitDej" >
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
		  
		  <div class="form-group">
		    <label for="role">Rôle</label>
		    <select type="text" class="form-control" name="role" id="role">
			  <option value="user"> Simple User</option> 
			  <option value="admin"> Admin User </option>
			</select>
		  </div>
		  
		  <button type="submit" class="btn btn-default">Submit</button>
	</form>
	
	
	<script>
		$(document).on("click", "#recherche-btn", function(e) {
	        var val = $("#recherche")[0].value;
			
	        console.log(val);
	        
	        $.ajax({
                url: 'http://world.openfoodfacts.org/cgi/search.pl?search_terms='+val+'&search_simple=1&action=process&json=1',
                type: 'GET',
                dataType: 'json', // JSON
                success: function(data, textStatus, jqXHR){
                	$("#choix").empty();
                	for (var i = 0; i < data.products.length; i++) {
                    	
            
                    	console.log(data.products[i]);
                    	
                    	console.log(data.products[i].id);
                    	console.log(data.products[i].nutriments.energy_value);
                    	console.log(data.products[i].generic_name_fr);
                    	console.log(data.products[i].image_url);
                    	
                    	console.log("-------------");

                		
                    	div = document.createElement("div");
                    	div.dataset.nom = data.products[i].generic_name_fr;
                    	div.dataset.url_path_img = data.products[i].image_url;
                    	div.dataset.energie = data.products[i].nutriments.energy_value;
                    	
                    	div.className = "choix-product";
                    	div.style.cssText = "display:inline-block;width:200px;margin: 10px;padding: 10px;background-color:white;cursor:pointer;"
                    	
                    	image = document.createElement("img");
                    	image.src = data.products[i].image_url;
                    	image.style.cssText = "display:block;max-height: 150px;max-width: 150px;margin: 0 auto;"
                    
                    	titre = document.createElement("span");
                    	titre.style.cssText = "display:block;color:black;text-align:center"
                    	titre.innerHTML = data.products[i].generic_name_fr;
                    		
                    	$("#choix").append(div);
                    	div.appendChild(image);
                    	div.appendChild(titre);
					}
                },
                error: function(jqXHR, textStatus, errorThrown){
                    alert('erreur: ' + jqXHR + ', ' + textStatus + ', ' + errorThrown);
                    console.log('erreur: ' + jqXHR + ', ' + textStatus + ', ' + errorThrown);
                    console.log(jqXHR);
                }
            });
	        
	        
	        $(document).on("click", "#recherche-btn", function(e) {
		        var val = $("#recherche")[0].value;
				
		        console.log(val);
		        
		        $.ajax({
	                url: 'http://world.openfoodfacts.org/cgi/search.pl?search_terms='+val+'&search_simple=1&action=process&json=1',
	                type: 'GET',
	                dataType: 'json', // JSON
	                success: function(data, textStatus, jqXHR){
	                	$("#choix").empty();
	                	for (var i = 0; i < data.products.length; i++) {
	                    	
	            
	                    	console.log(data.products[i]);
	                    	
	                    	console.log(data.products[i].id);
	                    	console.log(data.products[i].nutriments.energy_value);
	                    	console.log(data.products[i].generic_name_fr);
	                    	console.log(data.products[i].image_url);
	                    	
	                    	console.log("-------------");

	                		
	                    	div = document.createElement("div");
	                    	div.dataset.nom = data.products[i].generic_name_fr;
	                    	div.dataset.url_path_img = data.products[i].image_url;
	                    	div.dataset.energie = data.products[i].nutriments.energy_value;
	                    	
	                    	div.className = "choix-product";
	                    	div.style.cssText = "display:inline-block;width:200px;margin: 10px;padding: 10px;background-color:white;cursor:pointer;"
	                    	
	                    	image = document.createElement("img");
	                    	image.src = data.products[i].image_url;
	                    	image.style.cssText = "display:block;max-height: 150px;max-width: 150px;margin: 0 auto;"
	                    
	                    	titre = document.createElement("span");
	                    	titre.style.cssText = "display:block;color:black;text-align:center"
	                    	titre.innerHTML = data.products[i].generic_name_fr;
	                    		
	                    	$("#choix").append(div);
	                    	div.appendChild(image);
	                    	div.appendChild(titre);
						}
	                },
	                error: function(jqXHR, textStatus, errorThrown){
	                    alert('erreur: ' + jqXHR + ', ' + textStatus + ', ' + errorThrown);
	                    console.log('erreur: ' + jqXHR + ', ' + textStatus + ', ' + errorThrown);
	                    console.log(jqXHR);
	                }
	            });
	        
	        
	        
	    });

	</script>
</body>
</html>