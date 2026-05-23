<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Crear Cuenta</title>

	<link rel="icon" type="image/png" href="imagenes/LogoVentana.png">

	<link rel="stylesheet" href="css/estilos.css">

</head>

	<body>

		<div class="tarjeta2">

			<img src="imagenes/Logote.png" alt="Logo GestorTareas" class="logo">

			<!-- <h1>Registrate</h1> -->

			

				<form action="ServletRegistro" method="POST">
					
					<div class="dos-columnas">

						<div class="columna">

							<div class = "campo">
								<label>Nombre(s):</label>
								<input type="text" name="txtNombre" placeholder="Nombre">
							</div>

							<div class="campo">
								<label>Primer Apellido:</label>
								<input type="text" name="txtApellidoP" placeholder="Paterno">
							</div>

							<div class="campo">
								<label>Segundo Apellido:</label>
								<input type="text" name="txtApellidoM" placeholder="Materno">
							</div>

						</div>

						<div class="columna">

							<div class="campo">
								<label>Correo:</label>
								<input type="email" name="txtEmail" placeholder="ejemplo@gmail.com">
							</div>

							<div class="campo">
								<label>Usuario:</label>
								<input type="text" name="txtUsername" placeholder="Usuario">
							</div>

							<div class="campo">
								<label>Contraseña:</label>
								<input type="password" name="txtPassword_" placeholder="•••••••">
							</div>

						</div>

					</div>

					
						<div class="campo">
								<label>Fecha de Nacimiento:</label>
								<input type="date" name="txtDOB">
						</div>

					<button type="submit">Registrarse</button>
					
				</form>
                        
                                <div class="error-msg">${mensajeError}</div>

				<a href="Login.jsp"> ¿Ya tienes cuenta? ¡Inicia sesion!</a>
			
		</div>

	</body>

</html>