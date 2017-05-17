<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
</head>
<body>
	
	<h2>Mitarbeiter Verwaltung</h2>
	
	<div style="display:inline">
		<button onclick="disphinzu();">Mitarbeiter hinzuf&uuml;gen</button>
		<button onclick="disploes();">Mitarbeiterdaten l&ouml;schen</button>
		<button onclick="dispaendern();">Mitarbeiterdaten &auml;ndern</button>
	</div>
	
	
	
	<div style="display: none" id="anlegen">
		<form action="MitarbeiterAdminController" method="post">
			<label>Vorname</label>
			<input name="vorname" id="vorname" requiert="requiert" type="text" placeholder="Vorname">
			<br>
			<label>Nachname</label>
			<input name="nachname" id="nachname" requiert="requiert" type="text"placeholder="Nachname">
			<br>
			<label>Sozialversicherungsnummer</label>
			<input name="svnr" id="svnr" requiert="requiert" type=number placeholder="147258369">
			<br>
			<label>Spezialisierung</label>
			<select>
			<option>Admin</option>
			<option>IT</option>
			<option>Fahrlehrer</option>
			</select>
			<br>
			<label>Mitarbeitertyp</label>
			<input type="number" name="typ" id="typ" placeholder="weiß ich noch nicht">
			<br>
			<label>Geburtsdatum</label>
			<input type="text" name ="gebdate" id="gebdate" requiert="requiert" placeholder="DD-MM-YYYY" >
			<br>
			<label>Username</label>
			<input type="text" name="user" id="user" requiert="requiert" placeholder="Username">
			<br>
			<label>Passwort</label>
			<input type="password" name="pwd" id="pwd" requiert="requiert" placholder="">
			<br>
			<label>Aktiv</label>
			<select>
				<option>Aktiv</option>
				<option>Inaktiv</option>
			</select>
			<br>
			<input type="hidden" name="pageName" value="anlegen">
			<button type="submit">Bestätigen</button>
		</form>.
	</div>
	
	<div style="display: none" id="loeschen">
		
	</div>
	
	<div style="display: none" id="aendern">
		<h4>Test 3</h4>
	</div>
	
	<form action="MitarbeiterAdminController" method="post">
		<input type=hidden name="pageName" value="back">
		<button type="submit">Zur&uuml;ck</button>
	</form>
		<script language="javascript">
		function disphinzu(){
			document.getElementById("anlegen").style.display="initial";
			document.getElementById("loeschen").style.display="none";
			document.getElementById("aendern").style.display="none";
		}
		function disploes(){
			document.getElementById("anlegen").style.display="none";
			document.getElementById("loeschen").style.display="inline";
			document.getElementById("aendern").style.display="none";
		}
		function dispaendern(){
			document.getElementById("anlegen").style.display="none";
			document.getElementById("loeschen").style.display="none";
			document.getElementById("aendern").style.display="inline";
		}
		function dispreset(){
			document.getElementById("anlegen").style.display="none";
			document.getElementById("loeschen").style.display="none";
			document.getElementById("aendern").style.display="none";
		}
	</script>
</body>
</html>