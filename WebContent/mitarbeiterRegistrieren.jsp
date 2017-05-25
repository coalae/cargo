
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="logic.MitarbeiterMgmt"%>
<%@page import="model.Mitarbeiter"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
    <head>
    <% String message = (String) request.getAttribute("message") ;%>
    
        <!-- Basic Page Needs
        ================================================== -->
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>CarGo Driving School</title>
        <meta name="description" content="">
        <meta name="keywords" content="">
        <meta name="author" content="">
        <!-- Mobile Specific Metas
        ================================================== -->
        <meta name="format-detection" content="telephone=no">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!--  jquery  -->
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<!--  fuer datepicker -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
	
        <!-- Template CSS Files
        ================================================== -->
        <!-- Twitter Bootstrs CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Ionicons Fonts Css -->
        <link rel="stylesheet" href="css/ionicons.min.css">
        <!-- animate css -->
        <link rel="stylesheet" href="css/animate.css">
        <!-- Hero area slider css-->
        <link rel="stylesheet" href="css/slider.css">
        <!-- owl craousel css -->
        <link rel="stylesheet" href="css/owl.carousel.css">
        <link rel="stylesheet" href="css/owl.theme.css">
        <link rel="stylesheet" href="css/jquery.fancybox.css">
        <!-- template main css file -->
        <link rel="stylesheet" href="css/main.css">
        <!-- responsive css -->
        <link rel="stylesheet" href="css/responsive.css">
        
        <!-- Template Javascript Files
        ================================================== -->
        <!-- modernizr js -->
        <script src="js/vendor/modernizr-2.6.2.min.js"></script>
        <!-- owl carouserl js -->
        <script src="js/owl.carousel.min.js"></script>
        <!-- bootstrap js -->

        <script src="js/bootstrap.min.js"></script>
        <!-- wow js -->
        <script src="js/wow.min.js"></script>
        <!-- slider js -->
        <script src="js/slider.js"></script>
        <script src="js/jquery.fancybox.js"></script>
        <!-- template main js -->
        <script src="js/main.js"></script>

	<script>
	$( function() {
		$( "#datepicker" ).datepicker({ dateFormat: 'dd.mm.yy' });
	} );
	</script>
    </head>
    <body>
        <!--
        ==================================================
        Header Section Start
        ================================================== -->
        <header id="top-bar" class="navbar-fixed-top animated-header">
            <div class="container">
                <div class="navbar-header">
                    <!-- responsive nav button -->
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    </button>
                    <!-- /responsive nav button -->
                    
                    <!-- logo -->
                    <div class="navbar-brand">
                        <a href="index.jsp" >
                            <img src="images/CargoLogo.JPG" alt="" align="right">
                            <br>
                        </a>
                    </div>
                    <!-- /logo -->
                </div>
                <!-- main menu -->
                <nav class="collapse navbar-collapse navbar-right" role="navigation">
                    <div class="main-menu">
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a href="index.jsp" >Home</a>
                            </li> 
                            
                            <li><a href="kursListeAnzeigen.jsp">Kurskatalog</a></li>
                            
                            <li><a href="registrieren.jsp">Registrieren</a></li>
                            
                            <li><a href="login.jsp">Login</a></li>
							
                        </ul>
                    </div>
                </nav>
                <!-- /main nav -->
            </div>
        </header>
        
        
        
        
        
        <!--
        ==================================================
        Slider Section Start - KUNDENACCOUNT ERSTELLEN
        ================================================== -->
        <section id="hero-area" >
            <div class="container">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <div class="block wow fadeInUp" data-wow-delay=".3s">
                            
                            <!-- Slider -->
                            <section class="cd-intro">
                                <h1 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                                <span>Mitarbeiteraccount erstellen:</span><br><br>
                      <!--          <span class="cd-words-wrapper">
                                    <b class="is-visible">CarGo Driving School</b>
                                    <b>CarGo Fahrschule</b>
                                </span> -->
                                </h1>
                                </section> <!-- cd-intro -->
                                <!-- /.slider -->

			<%MitarbeiterMgmt mm = new MitarbeiterMgmt();%>

		 
					<form action="MitarbeiterRegistrierenServlet" Method="POST" >
						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Vorname:</span><br>
						  <input type="text" name="vorname" required>
						  <br> </h2>
						  
                          <h2><span>Nachname:</span><br>
						  <input type="text" name="nachname" required>
						  <br> </h2>						  
						
						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Sozialversicherungsnummer (SVNR):</span><br>
                          <input type="number" min = "1" step="1" name="svnr" required>
						  <br> </h2>	

                          <h2><span>Spezialisierung:</span><br>
						  <input type="text" name="spezialisierung" required>
						  <br> </h2>	
						  						  
  						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Mitarbeitertyp:</span><br>
						  <select name="mitarbeitertyp" required>
  						  <option value="Fahrlehrer">Admin</option> <!-- typ 1 fahrlehrer -->
  						  <option value="Admin">Fahrlehrer</option> <!-- typ 2 admin -->
                          </select> </h2>
						  <br>
						  
						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Geburtsdatum:</span><br> 
                          <input type="text" name="geburtsdatum" id="datepicker" required>
						  <br> </h2>						 
  
                          <h2><span>Username:</span><br>
						  <input type="text" name="username" required>
						  <br> </h2>						  
						  
                          <h2><span>Password:</span><br>
						  <input type="text" name="password" required>
						  <br> </h2>						  

						  						  					  
						  <br>
						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
						  <span>	  <input type="submit" value="Mitarbeiteraccount erstellen"> </span> 
						  </h2>
						  
					</form>	
					
					 
						<% if (message != null){%>
							  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
	                          <span> 
							<% 	out.println(message); %>
								</span><br>
						<%	} %>
							

						  <br>
		
		<a href="index.jsp" class="btn btn-default btn-contact wow fadeInDown" data-wow-delay=".7s" data-wow-duration="500ms">Home</a>
						                                
                            </div>
                        </div>
                    </div>
                </div>
            </section><!--/#main-slider-->

            
            
        </body>
</html>