<jsp:directive.page contentType="text/html; charset=ISO-8859-1" />
<jsp:directive.page import="fr.esgi.model.User"/>

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>BeYourCoach</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

    <script src="js/bootstrap.min.js"></script>
  </head>
 <% User user = ((User)request.getSession().getAttribute("userSession")); %>