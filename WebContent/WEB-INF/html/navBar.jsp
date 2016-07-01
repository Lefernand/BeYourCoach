<jsp:directive.page contentType="text/html; charset=ISO-8859-1" />
<div class="masthead clearfix">
  <div class="inner">
  	<img class="masthead-brand" src="img/logo.png" style="max-width: 200px;">
    <nav>
      <ul class="nav masthead-nav">
        <li><a href="/BeYourCoach/home">Home</a></li>
        <%
        	if(request.getSession().getAttribute("userSession") == null) {
        		%>
        		<li class="active"><a href="login">Login</a></li>
        		<%
        	}
        %>
        <%
        	if(user != null && user.getRole().equals("admin")) {
        		%>
        		<li><a href="create">Create user</a></li>
        		<li><a href="list">List user</a></li>
        		<%
        	}
        %>
        
        <%
        	if(request.getSession().getAttribute("userSession") != null) {
        		%>
        		<li><a href="profile">Profile</a></li>
        		<li><a href="logout">Logout</a></li>
        		<%
        	}
        %>
      </ul>
    </nav>
  </div>
</div>

<jsp:directive.include file="error.jsp" />