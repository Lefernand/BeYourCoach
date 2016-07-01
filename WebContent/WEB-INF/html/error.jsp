<% if(request.getParameter("errorMessage") != null) { %>
	<div class="alert alert-danger" role="alert">
		${ errorMessage }
	</div>
<% } %>
<% if(request.getParameter("success") != null) { %>
<div class="alert alert-success" role="alert">
	${ success }
</div>
<% } %>
<% if(request.getParameter("infoMessage") != null) { %>
<div class="alert alert-info" role="alert">
	${ infoMessage }	
</div>
<% } %>