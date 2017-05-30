<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
    <head>
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
                        <a href="indexLoggedInAsMitarbeiter.jsp" >
                            <img src="images/CargoLogo.JPG" alt="" align="right">
                            <br>
                        </a>
                    </div>
                    <!-- /logo -->
                </div>
                
                <br> <br> <br> <br> <br> 
                
                <!-- main menu -->
                <nav class="collapse navbar-collapse navbar-right" role="navigation">
                    <div class="main-menu active">
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a href="indexLoggedInAsMitarbeiter.jsp" >Home</a>
                            </li> 
                            
                            <li>
                                <a href="verwaltenMitarbeiter.jsp" >Mitarbeiter</a>
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
                                        <li><a href="immobilienListe.jsp">Verwaltung</a></li>
                                        <li><a href="ListeImmobilie.jsp">Liste</a></li>
                                    </ul>
                                </div>
                            </li>
                            
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Fahrzeuge<span class="caret"></span></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li><a href="fahrzeugListe.jsp">Verwaltung</a></li>
                                        <li><a href="ListeFahrzeug.jsp">Liste</a></li>
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
 
 <!-- TODO: PROFIL HIER AN MITARBEITER ANPASSEN -->
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
							
	                  		<li><a href="LogoutMitarbeiterServlet">Logout</a></li>
                                                       
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
	<section id="hero-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12 text-center">
				<div class="block wow fadeInUp" data-wow-delay=".3s">

					<!-- Slider -->
					<section class="cd-intro">

					<h1 class="wow fadeInUp animated cd-headline slide"
						data-wow-delay=".4s">
						<center>
							<div style="display: inline">
								<button onclick="fahrzeughinzu();">Fahrzeug
									hinzuf&uuml;gen</button>
								<button onclick="fahrzeugloes();">Fahrzeug
									l&ouml;schen</button>
								<button onclick="fahrzeugaendern();">FahrzeugDaten
									&auml;ndern</button>
							</div>
						</center>

						<div style="display: none" id="anlegen">
						<h4> 
						<center>
							<form action="FahrzeugAdminController" method="post">
							<label>Marke</label> <br> 
							<input name="Marke" id="marke" required="requiert" type="text" placeholder="Marke">
							<br>
							<label>Modell</label> <br> 
							<input name="Modell"id="modell" required="requiert" type="text" placeholder="Modell"> 
							<br> 
							<label>Baujahr</label><br> 
							<input name="Baujahr" id="baujahr" required="requiert" type="int" placeholder="Baujahr"> 
							<br> 
							<label>Farbe</label> <br> 
							<input type="Farbe" name="farbe" id="farbe" placeholder="Farbe">
							<br> 
						
								<input type="hidden" name="pageName" value="anlegen">
								<button type="submit">Best&auml;tigen</button>
							</form>
							</center>
						</h4>
					</div>

						<div style="display: none" id="loeschen">
							<h4>
								<center>
									<form action="FahrzeugAdminController" method="post">
									<label>FahrzeugID</label>
									<br>
									<input type="int" name="fahrzeugid" id="fahrzeugid" placeholder="fahrzeugid" required="requiert">
									<input type="hidden" name="pageName" value="loeschen">
									<button type="submit">Best&auml;tigen</button>
									</form>
								</center>
							</h4>
						</div>
						
						<div style="display: none" id="aendern">
							<h4>
							<center>
									<form action="FahrzeugAdminController" method="post">
									<label>ID</label>
									<br>
									<input type="text" name="id" id="id">
									<br>
									<label>Marke</label>
									<br>
									<input type="text" name="marke" id="marke">
									<br>
									
									<label>Modell</label>
									<br>
									<input type="text" name="modell" id="modell">
									<br>
									<label>Baujahr</label>
									<br>
									<input type="text" name="baujahr" id="baujahr">
									<br>
									<label>Farbe</label>
									<br>
									<input type="text" name="farbe" id="farbe">
									<br>
									
									<input type="hidden" name="pageName" value="aendern">
									<button type="submit">Best&auml;tigen</button>
									
									</form>
								</center>
							</h4>
							
						</div>
						<form action=FahrzeugAdminController method="post">
							<input type=hidden name="pageName" value="back">
							<button type="submit">Zur&uuml;ck</button>
						</form>
						<script language="javascript">
							function fahrzeughinzu() {
								document.getElementById("anlegen").style.display = "inline";
								document.getElementById("loeschen").style.display = "none";
								document.getElementById("aendern").style.display = "none";
							}
							function fahrzeugloes() {
								document.getElementById("anlegen").style.display = "none";
								document.getElementById("loeschen").style.display = "inline";
								document.getElementById("aendern").style.display = "none";
							}
							function fahrzeugaendern() {
								document.getElementById("anlegen").style.display = "none";
								document.getElementById("loeschen").style.display = "none";
								document.getElementById("aendern").style.display = "inline";
							}
							function fahrzeugreset() {
								document.getElementById("anlegen").style.display = "none";
								document.getElementById("loeschen").style.display = "none";
								document.getElementById("aendern").style.display = "none";
							}
						</script>
					
				</div>
			
			</div>
		
		</div>
	</div>
	
	</section>
	<!--/#main-slider-->
</body>
</html>
        