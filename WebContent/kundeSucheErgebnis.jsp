
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="logic.KundenMgmt"%>
<%@page import="model.Kunde"%>
<%@page import="model.Mitarbeiter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>


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
        <!-- jquery -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
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
        	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
                                                                                <br> <br> <br>  <br> <br>
                
                <!-- main menu -->
                <nav class="collapse navbar-collapse navbar-right" role="navigation">
                    <div class="main-menu">
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a href="index.jsp" >Home</a>
                            </li> 
                            
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Mitarbeiter<span class="caret"></span></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li><a href="mitarbeiterListe.jsp">Mitarbeiterliste</a></li>
                                        <li><a href="mitarbeiterHinzufuegen.jsp">Mitarbeiter hinzufuegen</a></li>
                                        <li><a href="mitarbeiterLoeschen.jsp">Mitarbeiter loeschen</a></li>
                                        <li><a href="mitarbeiterAendern.jsp">Mitarbeiter aendern</a></li>
                                    </ul>
                                </div>
                            </li>
                            
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Mentoring<span class="caret"></span></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li><a href="mentoringListe.jsp">Mentoringliste</a></li>
                                        <li><a href="mentoringHinzufuegen.jsp">Mentoring hinzufuegen</a></li>
                                        <li><a href="mentoringLoeschen.jsp">Mentoring loeschen</a></li>
                                        <li><a href="mentoringAendern.jsp">Mentoring aendern</a></li>
                                    </ul>
                                </div>
                            </li>
                            
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Immobilien<span class="caret"></span></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li><a href="immobilienListe.jsp">Immobilienliste</a></li>
                                        <li><a href="immobilieHinzufuegen.jsp">Immobilie hinzufuegen</a></li>
                                        <li><a href="immobilieAendern.jsp">Immobilie aendern</a></li>
                                    </ul>
                                </div>
                            </li>
                            
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Finanzen<span class="caret"></span></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li><a href="kontoAnzeigen.jsp">Konto anzeigen</a></li>
                                        <li><a href="transaktionsliste.jsp">Transaktionsliste</a></li>
                                        <li><a href="transaktionLoeschen.jsp">Transaktion loeschen</a></li>
                                    </ul>
                                </div>
                            </li>
                            
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Fahrzeuge<span class="caret"></span></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li><a href="fahrzeugListe.jsp">Fahrzeugliste</a></li>
                                        <li><a href="fahrzeugHinzufuegen.jsp">Fahrzeug hinzufuegen</a></li>
                                        <li><a href="fahrzeugLoeschen.jsp">Fahrzeug loeschen</a></li>
                                        <li><a href="fahrzeugAendern.jsp">Fahrzeug aendern</a></li>
                                    </ul>
                                </div>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kurse <span class="caret"></span></a>
                                <div class="dropdown-menu active">
                                    <ul>
                                        <li><a href="kursHinzufuegen.jsp">Kurs hinzufuegen</a></li>
                                        <li><a href="kursLoeschen.jsp">Kurs loeschen</a></li>
                                        <li><a href="kursAendern.jsp">Kursdaten abfragen</a></li>
                                    </ul>
                                </div>
                            </li>
                            
                            <li><a href="kursListeAnzeigen.jsp">Kurskatalog</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Mein Profil<span class="caret"></span></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li><a href="meinProfil.jsp">Meine Daten</a></li>
                                        <li><a href="meineKurse.jsp">Meine Kurse</a></li>
                                        <li><a href="passwortAendern.jsp">Passwort aendern</a></li>
                                    </ul>
                                </div>
                            </li>
							
					<!-- CHECK IF LOGGED IN  -->		
							<li><a href="LogoutServlet">Logout</a></li>
                            
                        </ul>
                    </div>
                </nav>
                <!-- /main nav -->
            </div>
        </header>
        
        
        
        
        
        <!--
        ==================================================
        Slider Section Start
        ================================================== -->
        <section id="hero-area" >
            <div class="container">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <div class="block wow fadeInUp" data-wow-delay=".3s">
      			<% String keyword = (String) request.getAttribute("keywordSuche") ;%>
                            
                            <!-- Slider -->
                            <section class="cd-intro">
                                <h1 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
 <br>
                                <span>Suche nach: <%=keyword%></span><br>
                      <!--          <span class="cd-words-wrapper">
                                    <b class="is-visible">CarGo Driving School</b>
                                    <b>CarGo Fahrschule</b>
                                </span> -->
                                </h1>
                                </section> <!-- cd-intro -->
                                <!-- /.slider -->

			<%KundenMgmt km = new KundenMgmt();%>
		    <%SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");%>
			<%ArrayList<Kunde> suchliste=km.getKundeByKeyword(keyword); %>    

						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Suchergebnisliste: </span><br><br>				
                    
                          	<table class="table table-striped">
								  <tr>
								  	<th><div align="center"><h3>ID</h3></div></th>
								  	<th><div align="center"><h3>Vorname</h3></div></th>
								  	<th><div align="center"><h3>Nachname</h3></div></th>
								  	<th><div align="center"><h3>IBAN</h3></div></th>
								  	<th><div align="center"><h3>BIC</h3></div></th> 
								  	<th><div align="center"><h3>Username</h3></div></th> 								  	 
								  	<th><div align="center"><h3>Password</h3></div></th> 
								  </tr>

								 <%for(int i = 0; i<suchliste.size(); i++){%>
								  	<tr>
								  		<td><div align="center"><h3><%=suchliste.get(i).getId()%></h3></div></td>    
								  		<td><div align="center"><h3><%=suchliste.get(i).getVorname()%></h3></div></td>
								  		<td><div align="center"><h3><%=suchliste.get(i).getNachname()%></h3></div></td>
								  		<td><div align="center"><h3><%=suchliste.get(i).getIban()%></h3></div></td>
								  		<td><div align="center"><h3><%=suchliste.get(i).getBic()%></h3></div></td>
								  		<td><div align="center"><h3><%=suchliste.get(i).getUsername()%></h3></div></td>
								  		<td><div align="center"><h3><%=suchliste.get(i).getPassword()%></h3></div></td>
								  	</tr>
							<%}%>
							</table>			
					 
						<% if (message != null){%>
							  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
	                          <span> 
							<% 	out.println(message); %>
								</span><br>
						<%	} %>
							

						  <br>
					<a href="indexLoggedInAsMitarbeiter.jsp" class="btn btn-default btn-contact wow fadeInDown" data-wow-delay=".7s" data-wow-duration="500ms">Home</a>
						                                
                            </div>
                        </div>
                    </div>
                </div>
            </section><!--/#main-slider-->

            
            
        </body>
</html>