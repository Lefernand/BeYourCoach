<jsp:directive.page import="fr.esgi.model.User"/>

<html>
<jsp:directive.include file="header.jsp" />
<body>
<jsp:directive.include file="navBar.jsp" />
<div class="alert alert-success" role="alert"> 
	<strong>Hello, vous �tes connect� en tant que <%= user.getLogin() %></strong>
</div>

	<!-- Button trigger modal -->
  <!-- Trigger the modal with a button -->
  	

	<h1><%= request.getAttribute("booleanPoids") %></h1>

	<%
        	if(request.getAttribute("booleanPoids").equals(true)) {
        		%>
        		<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#poids_du_jour">Poids du jour</button>
        		<!-- Modal -->
				  <div class="modal fade" id="poids_du_jour" role="dialog">
				    <div class="modal-dialog">
				    
				      <!-- Modal content-->
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title"> Poids du jour </h4>
				        </div>
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
				      </div>
        		<%
        	}else{
        		%>
        		<p>Votre poids du jour � bien �t� ajout� et est de : <%= request.getAttribute("poidDuJour") %> kg</p><%
        	} %>
        
  	<h3>Statistiques</h3>
      <jsp:directive.include file="stats.jsp" />
    </div>
  </div>

<script>

$( document ).ready(function() {
	

});



</script>

</body>
</html>