<jsp:directive.page import="fr.esgi.model.User"/>

<html>
<jsp:directive.include file="header.jsp" />
<body class="profil">

<div class="site-wrapper">

  <div class="site-wrapper-inner">

    <div class="cover-container">

		<jsp:directive.include file="navBar.jsp" />
		
		<div class="inner cover">
		
			<div class="helloGuy" role="alert"> 
				<div>
					<strong>Remplissez votre profile avant de vous servir de l'outil.</strong><br><br>
					<button onclick='$(".helloGuy").hide();' type="button" class="btn btn-secondary" style="color: black;">Ok</button>
				</div>
			</div>
		  <h1 class="cover-heading">Journal de bord</h1>
		  <div class="row">
		  	<div class="col-sm-offset-0 col-sm-12">
		  		<% if(request.getSession().getAttribute("infoDuJour").equals("pasok")) { %>
        		<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#poids_du_jour">Poids du jour</button>
		  		<% } else { %>
		  		<button type="button" class="btn btn-success btn-lg disabled" data-toggle="modal" data-target="#poids_du_jour">Poids du jour <span class="glyphicon glyphicon-ok" aria-hidden="true"></span></button>
		  		<%}%>
			  	<a href="ajoutRepas" type="button" class="btn btn-warning" >Ajouter un repas du jour</a>
		  	
		  		<jsp:directive.include file="stats.jsp" />

		  	</div>
		  </div>
		</div>
    </div>
  </div>


</div>

		<!-- Button trigger modal -->
<!-- Trigger the modal with a button -->
<!-- Modal -->
  <div class="modal fade" id="poids_du_jour" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"> Poids du jour </h4>
        </div>
		<% if(request.getSession().getAttribute("infoDuJour").equals("pasok")) { %>
        <form method="get" action="home">
	        <div class="modal-body">
	          	
			 <input type="hidden" class="form-control" name="id" id="id" value="<%=user.getId()%>">
			 <input type="hidden" class="form-control" name="typeAction" id="typeAction" value="editPoids">
			 <div class="form-group">
			    <label for="poids">Poids</label>
			    <input type="number" class="form-control" name="poids" id="poids" placeholder="Entrez votre poids">
			  </div>
					  
	        </div>
	        <div class="modal-footer">
	          	<button type="submit" class="btn btn-success"> Ajouter </button>							
	        </div>
        </form>
    			<%}else{%>
    				<p>Votre poids du jour est de : <%= request.getSession().getAttribute("poidsDuJour") %> kg</p>
    				<p>Votre IMC du jour est de  : <%= request.getSession().getAttribute("IMCDuJour") %></p>
    				<p>Votre Masse graisseuse du jour est de : <%= request.getSession().getAttribute("MGDuJour") %> %</p>
    			<%}%>
    			
      </div>
   </div>
 </div>




</body>
</html>