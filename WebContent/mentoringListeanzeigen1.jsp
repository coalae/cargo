<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="logic.KursMgmt"%>
<%@page import="logic.MentoringMgmt"%>
<%@page import="logic.MitarbeiterMgmt"%>
<%@page import="model.Kurs"%>
<%@page import="model.Kunde"%>
<%@page import="model.Mitarbeiter"%>
<%@page import="model.Mentoring"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
    <head>
    <% String message = (String) request.getAttribute("message") ;%>
	<% String mentoringId = (String) request.getAttribute("mentoringId") ;%>    

 	<%ServletContext servletcontext=request.getServletContext(); %>
	<%Kunde kunde = (Kunde) servletcontext.getAttribute("kunde");%>
	<%Mitarbeiter mitarbeiter = (Mitarbeiter) servletcontext.getAttribute("mitarbeiter");%>
	    
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
                      <!--   <a href="indexLoggedInAsMitarbeiter.jsp" >  -->
                            <img src="images/CargoLogo.JPG" alt="" align="right">
                            <br>
                        </a>
                    </div>
                    <!-- /logo -->
                </div>
                                                                <br> <br> <br>  
                
                <!-- main menu -->
                <nav class="collapse navbar-collapse navbar-right" role="navigation">
                    <div class="main-menu">
                        <ul class="nav navbar-nav navbar-right">
 
                             <%if (kunde == null && mitarbeiter == null) {%>					 
                            <li>
                                <a href="index.jsp" >Home</a>
                            </li> 
                            <%}%>
  
                            <%if (kunde == null && mitarbeiter != null) {%>					 
                            <li>
                                <a href="indexLoggedInAsMitarbeiter.jsp" >Home</a>
                            </li> 
                            <%}%>
 
 
                            <%if (kunde != null && mitarbeiter == null) {%>					 
                            <li>
                                <a href="indexLoggedInAsKunde.jsp" >Home</a>
                            </li> 
                            <%}%>
                                  
                                  
                            <%if (kunde == null && mitarbeiter != null) {%>					                                                      
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Mitarbeiter<span class="caret"></span></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li><a href="mitarbeiterListe">Mitarbeiterliste</a></li>
                                        <li><a href="mitarbeiterHinzufuegen.jsp">Mitarbeiter hinzufuegen</a></li>
                                        <li><a href="mitarbeiterLoeschen.jsp">Mitarbeiter loeschen</a></li>
                                        <li><a href="mitarbeiterAendern">Mitarbeiter aendern</a></li>
                                    </ul>
                                </div>
                            </li>
   
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kunden<span class="caret"></span></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li><a href="kundenListe.jsp">Kundenliste</a></li>
                                    </ul>
                                </div>
                            </li>
                                                     
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Mentoring<span class="caret"></span></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li><a href="mentoringListe">Mentoringliste</a></li>
                                        <li><a href="mentoringHinzufuegen.jsp">Mentoring hinzufuegen</a></li>
                                        <li><a href="mentoringLoeschen.jsp">Mentoring loeschen</a></li>
                                        <li><a href="mentoringAendern">Mentoring aendern</a></li>
                                    </ul>
                                </div>
                            </li>
                            
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Immobilien<span class="caret"></span></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li><a href="immobilienListe">Immobilienliste</a></li>
                                        <li><a href="immobilieHinzufuegen.jsp">Immobilie hinzufuegen</a></li>
                                        <li><a href="immobilieAendern">Immobilie aendern</a></li>
                                    </ul>
                                </div>
                            </li>
                            
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Finanzen<span class="caret"></span></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li><a href="kontoAnzeigen.jsp">Konto anzeigen</a></li>
                                        <li><a href="transaktionsliste">Transaktionsliste</a></li>
                                        <li><a href="transaktionLoeschen.jsp">Transaktion loeschen</a></li>
                                    </ul>
                                </div>
                            </li>
                            
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Fahrzeuge<span class="caret"></span></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li><a href="fahrzeugListe">Fahrzeugliste</a></li>
                                        <li><a href="fahrzeugHinzufuegen.jsp">Fahrzeug hinzufuegen</a></li>
                                        <li><a href="fahrzeugLoeschen.jsp">Fahrzeug loeschen</a></li>
                                        <li><a href="fahrzeugAendern">Fahrzeug aendern</a></li>
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
                        <%}%>
                            
                            
                            <li><a href="kursListeAnzeigen.jsp">Kurskatalog</a></li>
                            
                           <%if (kunde != null && mitarbeiter == null) {%>					                           
                             <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Mein Profil<span class="caret"></span></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li><a href="meineKundendaten.jsp">Meine Daten</a></li>
                                        <li><a href="meineKundenKurse.jsp">Meine Kurse</a></li>
                                        <li><a href="meinKundenPasswortAendern.jsp">Passwort aendern</a></li>
                                    </ul>
                                </div>
                            </li>
                            <%}%>
							
                            <%if (kunde == null && mitarbeiter == null) {%>					                           							
							<li><a href="registrieren.jsp">Registrieren</a></li>
                            <%}%>
                           <%if (kunde == null && mitarbeiter == null) {%>					                           							
							<li><a href="login.jsp">Login</a></li>
                            <%}%>
                           <%if (kunde != null) {%>					                           							
							<li><a href="LogoutKundeServlet">Logout</a></li>
                            <%}%>
                           <%if (mitarbeiter != null) {%>					                           							
							<li><a href="LogoutMitarbeiterServlet">Logout</a></li>
                            <%}%>

                            
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
                            
                            <!-- Slider -->
                            <section class="cd-intro">
   
                                 <h2>
                                   <%if (mitarbeiter != null) {%>					
							        Sie sind eingeloggt als: <%=mitarbeiter.getUsername()%> 
							        <%}%>                                
                                </h2>
                                
                                <h1 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
   <br>
                                <span>Mentoring:</span>
                                <br>
                               
                      
                                </h1>
                                </section> <!-- cd-intro -->
                                <!-- /.slider -->

			<%MentoringMgmt mm = new MentoringMgmt();%>
			<%MitarbeiterMgmt mam = new MitarbeiterMgmt();%>
			<%Mentoring ausgewaehltesMentoring = mm.getMentoringById(Integer.parseInt(mentoringId));%>
			<% int mentor = ausgewaehltesMentoring.getMentorId(); %>
			<% int mentee = ausgewaehltesMentoring.getMenteeId(); %>
			<%Mitarbeiter ausgewaehlterMentor = mam.getMitarbeiterById(mentor); %>
			<%Mitarbeiter ausgewaehlterMentee = mam.getMitarbeiterById(mentee); %>
			
			<%SimpleDateFormat sdf = new SimpleDateFormat("yyyy");%> 

			 
						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Mentoringid:</span>
                          <br>
                          <span><%=ausgewaehltesMentoring.getMentoringId()%></span>
						  <br> </h2>
						
						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Mentorid:</span>
                          <br>
                          <span><%=ausgewaehltesMentoring.getMentorId()%></span>
						  <br> </h2>
						  
						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Mentorvorname: </span>
                          <br>
                          <span><%=ausgewaehlterMentor.getVorname()%></span>
                          <br> </h2>
						  
						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Mentornachname: </span>
                          <br>
                          <span><%=ausgewaehlterMentor.getNachname()%></span>
                          <br> </h2>
                          
                          <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Menteeid:</span>
                          <br>
                          <span><%=ausgewaehltesMentoring.getMenteeId()%></span>
						  <br> </h2>
                          
                          <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Menteevorname: </span>
                          <br>
                          <span><%=ausgewaehlterMentee.getVorname()%></span>
                          <br> </h2>
                          
                           <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Menteenachname: </span>
                          <br>
                          <span><%=ausgewaehlterMentee.getNachname()%></span>
                          <br> </h2>
                          
						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Thema: </span>
                          <br>
                          <span><%=ausgewaehltesMentoring.getThema()%></span><br>
                          <br> </h2>
                          
						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Beginnjahr: </span>
                          <br>
                          <span><%=sdf.format(ausgewaehltesMentoring.getBeginnJahr())%></span><br>
                          <br> </h2>
                          
		<a href="MentoringAendernServlet?mentoringid=<%=ausgewaehltesMentoring.getMentoringId()%>" class="btn btn-default btn-contact wow fadeInDown" data-wow-delay=".7s" data-wow-duration="500ms">�ndern</a> <br>
		<a href="mentoringListe.jsp" class="btn btn-default btn-contact wow fadeInDown" data-wow-delay=".7s" data-wow-duration="500ms">Zur�ck</a>
						                                
                            </div>
                        </div>
                    </div>
                </div>
            </section><!--/#main-slider-->				  

            
            
        </body>
</html>