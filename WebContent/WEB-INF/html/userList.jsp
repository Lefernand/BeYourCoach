 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page import="fr.esgi.model.User" />
<jsp:directive.page import="java.util.List" />


<html>
<jsp:directive.include file="header.jsp"/>

	<body>
	<jsp:directive.include file="navBar.jsp"/>
	<br/><br/><br/><br/><br/><br/>
	<table class="table table-stripped">
		<thead>
			<tr>
				<!-- ${variable} = même chose que getAttribute en java mais permet d'éviter
				d'avoir du java	 dans du HTML -->
				<th>${title}</th>
				<th>Rôle</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			
		<%
			List<User> users = (List<User>) request.getAttribute("userList");
			for(User user1 : users)
			{%>
			  <tr>
				<td>
					<%=user1.getLogin() %>
				</td>
				<td>
					<%if(user1.getRole().equals("admin")){ %>
					  <form method="get" action="updateRoleUser">
					    <input type="hidden" class="update-input-role" name="id_user" value="<%=user1.getId() %>">
						<button type="submit" class="btn btn-success adminUser">Administrateur</button>
					  </form>
					<%}else{%>
					  <form method="get" action="updateRoleAdmin">
					  <input type="hidden" class="update-input-role" name="id_user" value="<%=user1.getId() %>">
						<button type="submit" class="btn btn-default simpleUser">Simple Utilisateur</button>
					  </form>
					<%} %>
				  
				</td>
				<td>
				  <form method="get" action="deleteUser">
					    <input type="hidden" class="delete-input-role" name="id_user" value="<%=user1.getId() %>">
						<button type="submit" class="btn btn-danger deleteUser">Effacer</button>
				  </form>
				</td>
			  </tr>
			<% }
		%>
		
		</tbody>
	</table>
	</body>
	</html>