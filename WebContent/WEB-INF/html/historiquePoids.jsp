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
		${myPoidsList.get(1).getDate()}
			totoooo
			<% for(int i = 0; i < $myPoidsList.size(); i+=1) { %>
	            toto
	            <tr>      
	                <td>${myPoidsList.get(i).getId()}</td>
	                <td>${myPoidsList.get(i).getpoids()}</td>
	                <td>${myPoidsList.get(i).getIMC()}</td>
	                <td>${myPoidsList.get(i).getMG()}</td>
	                <td>${myPoidsList.get(i).getDate()}</td>  
	            </tr>
        	<% } %>
			
		
		</tbody>
	</table>
	</body>
	</html>