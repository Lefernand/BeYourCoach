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
			</tr>
		</thead>
		<tbody>
		<%
			List<User> users = (List<User>) request.getAttribute("userList");
			for(User user1 : users)
			{
				out.print("<tr><td>");
				out.print(user1.getLogin() + "<br/>");
				out.print("</tr></td>");
			}
		%>
		</tbody>
	</table>
	</body>
	</html>