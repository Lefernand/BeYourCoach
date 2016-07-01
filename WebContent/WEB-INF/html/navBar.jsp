<jsp:directive.page contentType="text/html; charset=ISO-8859-1" />
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li class="active"><a href="home">Home</a></li>
        <%
        	if(request.getSession().getAttribute("userSession") == null) {
        		%>
        		<li><a href="login">Login</a></li>
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
    </div><!--/.nav-collapse -->
  </div>
</nav>
<br><br>
<jsp:directive.include file="error.jsp" />