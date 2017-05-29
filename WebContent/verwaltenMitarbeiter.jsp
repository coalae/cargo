
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="logic.KundenMgmt"%>
<%@page import="java.util.*"%>
<%@page import="model.Kunde"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
<head>
<!--     String username = (String) request.getAttribute("username") ;  -->
<%
	String message = (String) request.getAttribute("message");
%>

<%
	ServletContext servletcontext = request.getServletContext();
%>
<%
	Kunde kunde = (Kunde) servletcontext.getAttribute("kunde");
%>


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
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
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
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'dd.mm.yy'
		});
	});
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
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- /responsive nav button -->

			<!-- logo -->
			<div class="navbar-brand">
				<a href="indexLoggedInAsKunde.jsp"> <img
					src="images/CargoLogo.JPG" alt="" align="right"> <br>
				</a>
			</div>
			<!-- /logo -->
		</div>
		<br> <br>

		<!-- main menu -->
		<nav class="collapse navbar-collapse navbar-right" role="navigation">
		<div class="main-menu">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="index.jsp">Zurück</a></li>





				<li><a href="LogoutKundeServlet">Logout</a></li>



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
								<button onclick="disphinzu();">Mitarbeiter
									hinzuf&uuml;gen</button>
								<button onclick="disploes();">Mitarbeiterdaten
									l&ouml;schen</button>
								<button onclick="dispaendern();">Mitarbeiterdaten
									&auml;ndern</button>
							</div>
						</center>

						<div style="display: none" id="anlegen">
						<h1> 
							<form action="MitarbeiterAdminController" method="post">
							<label>Vorname</label> <br> 
							<input name="vorname" id="vorname" requiert="requiert" type="text" placeholder="Vorname">
							<br>
							<label>Nachname</label> <br> 
							<input name="nachname"id="nachname" requiert="requiert" type="text" placeholder="Nachname"> 
							<br> 
							<label>Sozialversicherungsnummer</label><br> 
							<input name="svnr" id="svnr" requiert="requiert" type="text" placeholder="147258369"> 
							<br> 
							<label>Spezialisierung</label> <br>
								<select name="spezi" id="spezi">
									<option>Admin</option>
									<option>IT</option>
									<option>Fahrlehrer</option>
								</select> 
							<br> 
							<label>Mitarbeitertyp</label> <br> 
							<input type="number" name="typ" id="typ" placeholder="weiß ich noch nicht">
							<br> 
							<label>Geburtsdatum</label> <br> 
							<input type="text" name="gebdate" id="gebdate" requiert="requiert" placeholder="DD-MM-YYYY"> 
							<br> 
							<label>Username</label> <br> 
							<input type="text" name="user" id="user" requiert="requiert" placeholder="Username"> 
							<br> 
							<label>Passwort</label> <br> 
							<input type="password" name="pwd" id="pwd" requiert="requiert" placholder=""> 
							<br> 
							<label>Aktiv</label> <br> 
								<select name=status id=status>
									<option>Aktiv</option>
									<option>Inaktiv</option>
								</select> 
								<br> 
								<input type="hidden" name="pageName" value="anlegen">
								<button type="submit">Best&auml;tigen</button>
							</form>
						</h1>
						</div>

						<div style="display: none" id="loeschen">
							<h4>
								<center>
									<form action="MitarbeiterAdminController" method="post">
									<label>Sozialversicherungsnummer</label>
									<br>
									<input type="text" name="svnr" id="svnr" placeholder="Sozialversicherungsnummer" requiert="requiert">
									<input type="hidden" name="pageName" value="loeschen">
									<button type="submit">Best&auml;tigen</button>
									</form>
								</center>
							</h4>
						</div>
						
						<div style="display: none" id="aendern">
							<h4>
							<center>
									<form action="MitarbeiterAdminController" method="post">
									<label>Username</label>
									<br>
									<input type="text" name="user" id="user">
									<br>
									<label>altes Passwort</label>
									<br>
									<input type="password" name="oldpw" id="oldpw">
									<br>
									<label>neues Passwort</label>
									<br>
									<input type="password" name="newpw" id="newpw">
									<br>
									<input type="hidden" name="pageName" value="aendern">
									<button type="submit">Best&auml;tigen</button>
									</form>
								</center>
							</h4>
							
						</div>
						<form action="MitarbeiterAdminController" method="post">
							<input type=hidden name="pageName" value="back">
							<button type="submit">Zur&uuml;ck</button>
						</form>
						<script language="javascript">
							function disphinzu() {
								document.getElementById("anlegen").style.display = "inline";
								document.getElementById("loeschen").style.display = "none";
								document.getElementById("aendern").style.display = "none";
							}
							function disploes() {
								document.getElementById("anlegen").style.display = "none";
								document.getElementById("loeschen").style.display = "inline";
								document.getElementById("aendern").style.display = "none";
							}
							function dispaendern() {
								document.getElementById("anlegen").style.display = "none";
								document.getElementById("loeschen").style.display = "none";
								document.getElementById("aendern").style.display = "inline";
							}
							function dispreset() {
								document.getElementById("anlegen").style.display = "none";
								document.getElementById("loeschen").style.display = "none";
								document.getElementById("aendern").style.display = "none";
							}
						</script>
						<br>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!--/#main-slider-->
</body>
</html>