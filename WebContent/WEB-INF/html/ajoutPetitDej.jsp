<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page import="fr.esgi.model.User"/>
<jsp:directive.page import="java.util.List"/>

<html>
<jsp:directive.include file="header.jsp" />
<body class="profile">



<div class="site-wrapper">

    <div class="cover-container">
		<jsp:directive.include file="navBar.jsp" />
		<div class="inner cover">
		  <h1 class="cover-heading text-center">Ajouter un Repas</h1>
		  <div class="row">
		  	<div class="col-sm-offset-1 col-sm-10">
				<form method="get" action="ajoutRepas" id="form-ajoutMeal" >
				<div class="form-inline">
				    <label for="search">Recherche</label>
					<input list="aliments" type="text" class="form-control" id="recherche" placeholder="Recherche">
					<button id="recherche-btn" type="button" class="btn btn-success">Rechercher</button>
					
					<button type="submit" class="btn btn-info pull-right">Enregistrer le repas</button>
					
					<select name="time" id="time" class="form-control pull-right" style="margin-right:10px !important;">
					  <option value="PDJ">Petit Déjeuner</option> 
					  <option value="DEJ">Déjeuner</option>
					  <option value="DIN">Dinner</option>
					</select>
				</div>
					 <table class="table">
						 <thead>
						  <tr>
						     <th>Image</th>
						     <th>nom</th>
						     <th>Energie</th>
						     <th>Quantité</th>
						     <th></th>
						  </tr>
						 </thead>
						 
						 <tbody id="tbody-form">
						 </tbody>
					</table>
				</form>
				<div id="choix">
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
                    	console.log(data.products[i].image_url+" imageeeeeeeee!!!");
                    	
                    	console.log("-------------");

                    	if(data.products[i].image_url == "undefined"){alert("undefined connard");}
                    	
                		if(data.products[i].image_url != "undefined")
                		{
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
	        	
	        	$(this)[0].remove();
	        	
	        	
	        	var img = $(this)[0].dataset.url_path_img;
		        
		        if($(this)[0].dataset.nom == "undefined"){
		        	console.log("nom = undifined et je prends donc la valeur dans l inpiut : " +  $('#recherche')[0].value);
		        	var nom = $('#recherche')[0].value;
		        }else{
		        	var nom = $(this)[0].dataset.nom;
		        }
		        
		        if($(this)[0].dataset.energie == "undefined"){
		        	var energie = Math.round(Math.random() * (900 - 100) + 100);
		        }else{
		        	var energie = $(this)[0].dataset.energie;
		        }
		        
				
		        console.log("nom : "+ nom);
		        console.log("img : "+ img );
		        console.log("energie : "+ energie);
		        
			  	nouveauTrTbody = document.createElement("tr");
			  	
			  	//image
			  	nouveauTdTbody1 = document.createElement("td");
			  	//nom
			  	nouveauTdTbody2 = document.createElement("td");
			  	//energie
			  	nouveauTdTbody3 = document.createElement("td");
			  	//quantité
			  	nouveauTdTbody4 = document.createElement("td");
			  	//bouton
			  	nouveauTdTbody5 = document.createElement("td");
			  	
			  	inputNom                 = document.createElement("input");
			  	inputNom.type            = "hidden";
			  	inputNom.className       = "form-control";
			  	inputNom.name            = "nom-"+count;
			  	inputNom.value           = nom;
			  	
			  	inputImgPath                 = document.createElement("input");
			  	inputImgPath.type            = "hidden";
			  	inputImgPath.className       = "form-control";
			  	inputImgPath.name            = "image-"+count;
			  	inputImgPath.value           = img;
			  	
			  	inputEnergie                 = document.createElement("input");
			  	inputEnergie.type            = "hidden";
			  	inputEnergie.className       = "form-control";
			  	inputEnergie.name            = "energie-"+count;
			  	inputEnergie.value           = energie;
			  	
			  	inputQuantite                 = document.createElement("input");
			  	inputQuantite.type            = "number";
			  	inputQuantite.className       = "";
			  	inputQuantite.name            = "quantite-"+count;
			  	inputQuantite.value           = 1;
			  	
			  	image = document.createElement("img");
            	image.src = img;
            	image.style.cssText = "display:block;max-height: 150px;max-width: 150px;margin: 0 auto;"
            	
            	nomSpan = document.createElement("span");
            	nomSpan.innerHTML = nom;
            	
            	energieSpan = document.createElement("span");
            	energieSpan.innerHTML = energie + " calories";
            	
            	deleteButton = document.createElement("button");
            	deleteButton.type = "button";
            	deleteButton.className = "btn btn-danger btn-effacer-aliment";
            	deleteButton.innerHTML = "Effacer"
               		
               	$("#tbody-form").append(nouveauTrTbody);
               	nouveauTrTbody.appendChild(nouveauTdTbody1);
               	nouveauTrTbody.appendChild(nouveauTdTbody2);
               	nouveauTrTbody.appendChild(nouveauTdTbody3);
               	nouveauTrTbody.appendChild(nouveauTdTbody4);
               	nouveauTrTbody.appendChild(nouveauTdTbody5);
               	
               	//create input form
               	nouveauTdTbody1.appendChild(inputNom);
               	nouveauTdTbody1.appendChild(inputImgPath);
               	nouveauTdTbody1.appendChild(inputEnergie);
               	//create ligne du tableau
               	nouveauTdTbody1.appendChild(image);
               	nouveauTdTbody2.appendChild(nomSpan);
               	nouveauTdTbody3.appendChild(energieSpan); 
               	nouveauTdTbody4.appendChild(inputQuantite); 
               	nouveauTdTbody5.appendChild(deleteButton); 
	    });
	        
	        $(document).on("click", ".btn-effacer-aliment", function(e) {
	        	$(this)[0].parentNode.parentNode.remove();
	        	$(this)[0].parentNode.parentNode.firstChild;
	        	
	        	var nom = $(this)[0].parentNode.parentNode.firstChild.childNodes[0].value;
	        	var img = $(this)[0].parentNode.parentNode.firstChild.childNodes[1].value;
	        	var energie = $(this)[0].parentNode.parentNode.firstChild.childNodes[2].value;
	        	
	        	div = document.createElement("div");
            	div.dataset.nom = nom;
            	div.dataset.url_path_img = img;
            	div.dataset.energie = energie;
            	
            	div.className = "choix-product";
            	div.style.cssText = "display:inline-block;width:200px;margin: 10px;padding: 10px;background-color:white;cursor:pointer;"
            	
            	image = document.createElement("img");
            	image.src = img;
            	image.style.cssText = "display:block;max-height: 150px;max-width: 150px;margin: 0 auto;"
            
            	titre = document.createElement("span");
            	titre.style.cssText = "display:block;color:black;text-align:center"
            	titre.innerHTML = nom;
            		
            	$("#choix").prepend(div);
            	div.appendChild(image);
            	div.appendChild(titre);
	        	
	        });

	</script>
</body>
</html>