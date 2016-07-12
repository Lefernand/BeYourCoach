<jsp:directive.page import="fr.esgi.model.User"/>

<html>
<jsp:directive.include file="header.jsp" />
<body class="profil">

<div class="site-wrapper">

  <div class="site-wrapper-inner">

    <div class="cover-container">

		<jsp:directive.include file="navBar.jsp" />
		
		<div class="inner cover">
		
			<div class="helloGuy" role="alert"> 
				<div>
					<strong>Hello, vous êtes connecté en tant que <%= user.getLogin() %></strong><br><br>
					<button onclick='$(".helloGuy").hide();' type="button" class="btn btn-secondary" style="color: black;">Ok</button>
				</div>
			</div>
		
		  <h1 class="cover-heading">Mes statistiques</h1>
		  <div class="row">
		  	<div class="col-sm-offset-0 col-sm-12">
		  	
      			<jsp:directive.include file="stats.jsp" />
      			
		  	</div>
		  </div>
		</div>
    </div>
  </div>

  <jsp:directive.include file="btn-footer.jsp" />

</div>

</body>
</html>