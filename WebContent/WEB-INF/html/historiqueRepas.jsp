 <%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="fr.esgi.model.Repas"%>
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
	<form method="get" action="historiqueRepas" class="form-inline">
		<input type=date class="form-control" name="dateRepas" >
		<button type="submit" class="btn btn-success">Rechercher</button>
	</form>
	
	<% if(request.getAttribute("recapJournee") != null) { ArrayList<Repas> listRepas = (ArrayList<Repas>) request.getAttribute("recapJournee"); %>
		
		<ul class="list-group">
		  <li class="list-group-item">First item</li>
		  <li class="list-group-item">Second item</li>
		  <li class="list-group-item">Third item</li>
		</ul>
		
		<ul class="list-group">
		  <li class="list-group-item">First item</li>
		  <li class="list-group-item">Second item</li>
		  <li class="list-group-item">Third item</li>
		</ul>
		
		<ul class="list-group">
		  <li class="list-group-item">First item</li>
		  <li class="list-group-item">Second item</li>
		  <li class="list-group-item">Third item</li>
		</ul>

		
<!-- 	  <form method="get" action="deletePoids"> -->
<%-- 		    <input type="hidden" class="delete-input-poids" name="id_poids" value="<%=listPerf.get(i).getId()%>"> --%>
<!-- 			<button type="submit" class="btn btn-danger deleteUser">Effacer</button> -->
<!-- 	  </form> -->

		
		</tbody>
	</table>
	<%}else{%>Aucun repas pour le moment<%} %>
		<script>
		$( document ).ready(function() {
		   
		});
		
// 		$(document).on("click", ".submitEdit", function(e) {
					
// 		});
		</script>
	</body>
	</html>