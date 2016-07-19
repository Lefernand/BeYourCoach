<% if(request.getAttribute("errorMessage") != null) { %>
	<div class="alert alert-danger" role="alert">
		${ errorMessage }.
	</div>
<% } %>
<% if(request.getAttribute("success") != null) { %>
<div class="alert alert-success" role="alert">
	${ success }
</div>
<% } %>
<% if(request.getAttribute("infoMessage") != null) { %>
<div class="alert alert-info" role="alert">
	${ infoMessage }	
</div>
<% } %>