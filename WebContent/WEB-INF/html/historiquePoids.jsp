 <%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="fr.esgi.model.PerfUser"%>
<%@page import="java.util.ArrayList"%>
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
				<th>Poids</th>
				<th>IMC</th>
				<th>Masse graisseuse</th>
				<th>Date</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<% ArrayList<PerfUser> listPerf = (ArrayList<PerfUser>) request.getAttribute("myPoidsList");
			for(int i = 0; i < listPerf.size(); i+=1) { %>
	            <tr>
	                <td><%=listPerf.get(i).getPoids()%> Kg </td>
	                <td><%=listPerf.get(i).getIMC()%></td>
	                <td><%=listPerf.get(i).getMG()%> %</td>
	                <td><%=listPerf.get(i).getDate()%></td>
	                <td>
					  <form method="get" action="updatePoids">
					    <input type="hidden" class="update-input-role" name="id_user" value="<%=user.getId() %>">
					    <input type="hidden" class="update-input-role" name="poids" value="<%=user.getId() %>">
						<button type="submit" class="btn btn-success adminUser">Administrateur</button>
					  </form>
					</td>
					<td>
					  <form method="get" action="deletePoids">
						    <input type="hidden" class="delete-input-role" name="id_user" value="<%=listPerf.get(i).getId()%>">
							<button type="submit" class="btn btn-danger deleteUser">Effacer</button>
					  </form>
					</td>
	            </tr>
        	<% } %>
		
		</tbody>
	</table>
	</body>
	</html>