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
<!-- 					  <form method="get" action="updatePoids"> -->
<%-- 					    <input type="hidden" class="update-input-poids" name="id_user" value="<%=user.getId() %>"> --%>
<%-- 					    <input type="hidden" class="update-input-poids" name="poids" value="<%=listPerf.get(i).getPoids()%>"> --%>
<!-- 						<button type="button" id="edit-poids" class="btn btn-info adminUser">Editer</button> -->
<!-- 					  </form> -->
					  <button type="button" id="edit-poids" class="btn btn-info">Editer</button>
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
	
		<script>
		$( document ).ready(function() {
		   
		});
		$(document).on("click", "#edit-poids", function(e) {
			var poids = $(this)[0].parentElement.parentElement.firstChild.nextElementSibling.innerHTML.split(" ")[0];
			$(this)[0].className = "btn btn-success";
			$(this)[0].id = "";
			
			$(this)[0].parentElement.parentElement.firstChild.nextElementSibling.innerHTML = "<input type=\"text\" class=\"form-control update-input-poids\" name=\"poids\" value=\""+poids+"\">";
		});
		</script>
	</body>
	</html>