<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page import="fr.esgi.model.User"/>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="java.util.Date"/>

<html>
<jsp:directive.include file="header.jsp" />
<body class="login">

<div class="site-wrapper">

  <div class="site-wrapper-inner">

    <div class="cover-container">

		<jsp:directive.include file="navBar.jsp" />

		<div class="inner cover" style="margin-bottom: 50px;">
		  <h1 class="cover-heading">Inscription</h1>
		  <div class="row">
		  	<div class="col-sm-offset-1 col-sm-10">
		  		<jsp:directive.include file="error.jsp" />
			  	
		  		<%
		  			Integer id = 0;
					String login = "";
					String nom = "";
					String prenom = "";
					Integer taille = 0;
					Integer obj_poids = 0;
					String email = "";
					String password = "";
					Boolean sexe = true;
					Date date_naissance = new Date();;
				%>
		  		
		  		<jsp:directive.include file="profileForm.jsp" />
		  		
		  	</div>
		  </div>
		</div>
    </div>

  </div>

</div>
	
</body>
</html>