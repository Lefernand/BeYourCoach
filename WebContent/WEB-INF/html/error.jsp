<div class="error">
	<% if(request.getAttribute("errorMessage") != null) { %>
		<div class="alert alert-danger" role="alert">
			${errorMessage}
		</div>
	<% } else if(request.getAttribute("success") != null) { %>
	<div class="alert alert-success" role="alert">
		${success}
	</div>
	<% } else if(request.getAttribute("infoMessage") != null) { %>
	<div class="alert alert-info" role="alert">
		${infoMessage}	
	</div>
	<% } %>
</div>