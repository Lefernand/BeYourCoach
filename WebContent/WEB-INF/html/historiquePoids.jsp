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
	
	
	<div class="row">
        <div class="col-md-offset-1 col-md-10 main" style="margin-top: 100px;">

          <h2 class="sub-header">Historique de pes�e</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
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
					  <button data-id="<%=listPerf.get(i).getId() %>" type="button" id="edit-poids" class="btn btn-info" style="display:inline-block;">Editer</button>
					  <form method="get" action="deletePoids" style="display:inline-block;">
						    <input type="hidden" class="delete-input-poids" name="id_poids" value="<%=listPerf.get(i).getId()%>">
							<button type="submit" class="btn btn-danger deleteUser">Effacer</button>
					  </form>
					</td>
	            </tr>
        	<% } %>
		
              </tbody>
            </table>
          </div>
        </div>
      </div>
	
		<script>
		$( document ).ready(function() {
		   
		});
		
		$(document).on("click", "#edit-poids", function(e) {
			var id = $(this)[0].dataset.id;
			alert(id);
			var poids = $(this)[0].parentElement.parentElement.firstChild.nextElementSibling.innerHTML.split(" ")[0];
			$(this)[0].className = "btn btn-success submitEdit";
			$(this)[0].id = "";
			
			$(this)[0].parentElement.parentElement.firstChild.nextElementSibling.innerHTML = "<form id=\"updatePoids-"+id+"\" method=\"get\" action=\"updatePoids\"><input type=\"hidden\" class=\"form-control update-input-poids\" name=\"idPoids\" value=\""+id+"\"><input type=\"number\" class=\"form-control update-input-poids\" name=\"poids\" value=\""+poids+"\"></form>";
		});
		
		$(document).on("click", ".submitEdit", function(e) {
			var id = $(this)[0].dataset.id;
			alert(id+"tototot submit");
			$("#updatePoids-"+id).submit();			
		});
		</script>
	</body>
	</html>