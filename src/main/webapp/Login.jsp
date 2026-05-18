<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html lang="es">

	<head>
		<meta charset = "UTF-8">
		<title> WorkIT - Login </title>

		<link rel="icon" type="image/png" href="img/LogoVentana.png">

		<link rel="stylesheet" href="css/estilos.css">
			
	</head>

	<body>

		<div class="tarjeta">

			<img src="img/Logote.png" alt="Logo GestorTareas" class="logo">

			<!-- <h1> Bienvenido</h1> -->

			<form action = "ServletLogin" method = "POST">

				<div class="grupo">
					<label>Usuario: </label>
					<input type="text" name="txtUser" placeholder="Usuario">
				</div>

				<div class="grupo">
					<label>Contraseña: </label>
					<input type="password" name="txtPass" placeholder="•••••••">
				</div>

				<button type="submit"> Entrar </button>

			</form>	

			<a href="registro.html"> ¿No tienes cuenta? ¡Regístrate aquí! </a>

		</div>

	</body>

</html>
