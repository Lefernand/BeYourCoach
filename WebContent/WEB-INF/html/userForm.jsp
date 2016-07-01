<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page import="fr.esgi.model.User"/>
<jsp:directive.page import="java.util.List"/>

<html>
<jsp:directive.include file="header.jsp" />
<body>
<jsp:directive.include file="navBar.jsp" />
	<br/><br/><br/><br/><br/><br/>
	<h1>${action}</h1>
	<form method="get" action="${action}">
		 <div class="form-group">
		    <label for="login">Login</label>
		    <input type="text" class="form-control" name="user" id="login" placeholder="Enter login">
		  </div>
		
		 <div class="form-group">
		    <label for="password">Password</label>
		    <input type="text" class="form-control" name="password" id="password" placeholder="Enter password">
		  </div>
		  
		  <div class="form-group">
		    <label for="email">email</label>
		    <input type="text" class="form-control" name="email" id="email" placeholder="Enter email">
		  </div>
		  
		  <div class="form-group">
		    <label for="role">Rôle</label>
		    <select type="text" class="form-control" name="role" id="role">
			  <option value="user"> Simple User</option> 
			  <option value="admin"> Admin User </option>
			</select>
		  </div>
		  
		  <button type="submit" class="btn btn-default">Submit</button>
	</form>
</body>
</html>