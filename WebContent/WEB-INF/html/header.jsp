<jsp:directive.page contentType="text/html; charset=ISO-8859-1" />
<jsp:directive.page import="fr.esgi.model.User"/>

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>BeYourCoach</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/admin.css" rel="stylesheet">
    <link href="css/cover.css" rel="stylesheet">
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

    <script src="js/bootstrap.min.js"></script>
    <!-- ChartJS 1.0.1 -->
    <script src="js/Chart.min.js"></script>
    
  </head>
 <% User user = ((User)request.getSession().getAttribute("userSession")); %>