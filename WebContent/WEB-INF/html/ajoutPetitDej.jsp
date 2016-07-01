<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page import="fr.esgi.model.User"/>
<jsp:directive.page import="java.util.List"/>

<html>
<jsp:directive.include file="header.jsp" />
<body class="profile">



<div class="site-wrapper">

  <div class="site-wrapper-inner">

    <div class="cover-container">

		<jsp:directive.include file="navBar.jsp" />

		<div class="inner cover">
		  <h1 class="cover-heading text-center">Ajouter un petit dej</h1>
		  <div class="row">
		  	<div class="col-sm-offset-1 col-sm-10">
				
				<div class="form-group">
				    <label for="search">Recherche</label>
					<input list="aliments" type="text" class="form-control" name="recherche" id="recherche" placeholder="Recherche">
					
					<button id="recherche-btn" type="submit" class="btn btn-success">Rechercher</button>
				</div>
				
				<div id="choix">
				</div>
				
				<form method="get" action="ajoutPetitDej" id="form-ajoutMeal" >
					 <table class="table">
						 <thead>
						  <tr>
						     <th>Nom</th>
						     <th>Image</th>
						     <th>Energie</th>
						  </tr>
						 </thead>
						 
						 <tbody id="tbody-form">
						 </tbody>
					</table>
					<button type="submit" class="btn btn-default">Enregistrer le repas</button>
				</form>
	
	
		  	</div>
		  </div>
		</div>
    </div>

  </div>

</div>
	
	
	<script>
		$( document ).ready(function() {
		    count = 0;
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
	        
	        
	        $(document).on("click", ".choix-product", function(e) {
	        	count++;
	        	
	        	var nom = $(this)[0].dataset.nom;
		        var img = $(this)[0].dataset.url_path_img;
		        var energie = $(this)[0].dataset.energie;
				
		        console.log("nom : "+ nom);
		        console.log("img : "+ img );
		        console.log("energie : "+ energie);
		   
		        
			  	nouveauTrTbody = document.createElement("tr");
			  	nouveauTdTbody1 = document.createElement("td");
			  	nouveauTdTbody2 = document.createElement("td");
			  	nouveauTdTbody3 = document.createElement("td");
			  	
			  	inputNom                 = document.createElement("input");
			  	inputNom.type            = "text";
			  	inputNom.className       = "form-control";
			  	inputNom.name            = "nom-"+count;
			  	inputNom.value           = nom;
			  	
			  	inputImgPath                 = document.createElement("input");
			  	inputImgPath.type            = "text";
			  	inputImgPath.className       = "form-control";
			  	inputImgPath.name            = "image-"+count;
			  	inputImgPath.value           = img;
			  	
			  	inputEnergie                 = document.createElement("input");
			  	inputEnergie.type            = "text";
			  	inputEnergie.className       = "form-control";
			  	inputEnergie.name            = "energie-"+count;
			  	inputEnergie.value           = energie;
			  
               		
               	$("#tbody-form").append(nouveauTrTbody);
               	nouveauTrTbody.appendChild(nouveauTdTbody1);
               	nouveauTrTbody.appendChild(nouveauTdTbody2);
               	nouveauTrTbody.appendChild(nouveauTdTbody3);
               	
               	nouveauTdTbody1.appendChild(inputNom);
               	nouveauTdTbody2.appendChild(inputImgPath);
               	nouveauTdTbody3.appendChild(inputEnergie);
	    });

	</script>
</body>
</html>