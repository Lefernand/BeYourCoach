<%@page import="fr.esgi.model.User"%>
<jsp:directive.page contentType="text/html; charset=ISO-8859-1" />
<div class="masthead clearfix">
  <div class="inner clearfix">
  	<img class="masthead-brand" src="img/logo.png" style="max-width: 200px;">
    <nav>
      <ul class="nav masthead-nav">
        
        <%if(request.getSession().getAttribute("userSession") == null) {%>
        	<li class="active"><a href="login">Login</a></li>
        <%}%>
        <% if(request.getSession().getAttribute("userSession") != null && user.getRole() != "Admin") {%>
        	<li><a href="list">Liste des utilisateurs</a></li>
       	<%}%>
        <% if(request.getSession().getAttribute("userSession") != null) {%>
      		<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
      		<li><a href="profile">Profile</a></li>
      		<li><a href="logout">Logout</a></li>
     	<%}%>
     	
        
      </ul>
    </nav>
  </div>
</div>

<jsp:directive.include file="error.jsp" />