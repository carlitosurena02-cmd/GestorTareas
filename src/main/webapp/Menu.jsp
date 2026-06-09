<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="modelo.Usuario" %>
<%@page import ="java.util.List" %>
<%@page import ="modelo.Workspace" %>

<%
    HttpSession sesionActual = request.getSession(false);
    Usuario usuarioActual = (sesionActual != null) ? 
                            (Usuario) sesionActual.getAttribute("usuarioLogueado") : null;
    if(usuarioActual == null){
        response.sendRedirect("Login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Principal</title>
        <style>
            body {
                font-family: 'Segoe UI', sans-serif;
                margin: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                background-image: url('../imagenes/Fondo.png');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                padding: 20px;
                box-sizing: border-box;
            }

            h1 {
                color: #023e8a;
                text-align: center;
                margin-top: 0;
                margin-bottom: 25px;
                font-size: 24px;
            }

            .tarjeta {
                background: white;
                padding: 40px;
                border-radius: 20px;
                width: 100%;
                max-width: 440px;
                box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
                box-sizing: border-box;
            }

            .tarjeta > a[href*="idWorkspace"] {
                display: block;
                background: #f8f9fa;
                color: #0077b6;
                padding: 14px 20px;
                border-radius: 10px;
                text-align: left;
                margin-top: 10px;
                margin-bottom: 10px;
                font-weight: 600;
                border: 2px solid #e0e1dd;
                text-decoration: none;
                transition: all 0.2s ease-in-out;
            }

            .tarjeta > a[href*="idWorkspace"]:hover {
                background: #eef1f6;
                border-color: #0077b6;
                color: #023e8a;
                transform: translateX(4px);
                text-decoration: none;
            }

            p label {
                display: block;
                color: #0077b6;
                font-weight: bold;
                margin-top: 20px;
                margin-bottom: 10px;
                text-align: center;
            }

            form {
                margin-top: 20px;
                border-top: 1px solid #e0e1dd;
                padding-top: 20px;
            }

            form div {
                margin-bottom: 15px;
            }

            label {
                display: block;
                color: #0077b6;
                font-weight: bold;
                margin-bottom: 8px;
                font-size: 14px;
            }

            input, select {
                width: 100%;
                padding: 12px;
                border: 2px solid #e0e1dd;
                border-radius: 10px;
                font-size: 15px;
                box-sizing: border-box;
                transition: border 0.2s;
                background-color: white;
            }

            input:focus, select:focus {
                border: 2px solid #0077b6;
                outline: none;
            }

            button {
                background: #023e8a;
                color: white;
                border: none;
                padding: 14px;
                border-radius: 10px;
                width: 100%;
                font-size: 16px;
                font-weight: bold;
                cursor: pointer;
                transition: background 0.3s, transform 0.2s;
                margin-top: 10px;
            }

            button:hover {
                background: #0077b6;
                transform: translateY(-2px);
            }

            button:active {
                transform: scale(0.97);
            }

            .btn-toggle {
                background: #0077b6;
                margin-bottom: 15px;
            }

            .btn-toggle:hover {
                background: #023e8a;
            }

            .form-oculto {
                display: none;
            }

            a[href="ServletCerrarSesion"] {
                display: block;
                text-align: center;
                margin-top: 25px;
                color: #d90429;
                font-weight: bold;
                text-decoration: none;
                transition: color 0.2s;
            }

            a[href="ServletCerrarSesion"]:hover {
                color: #b00020;
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="tarjeta">
            <h1>Bienvenido ${usuarioLogueado.nombre}</h1>
            
            <%
                List<Workspace> lista = (List<Workspace>) request.getAttribute("workspacesList");
                if(lista != null && !lista.isEmpty()) {
                    for(Workspace w : lista) {
            %>
                    <a href="ServletProyecto?idWorkspace=<%=w.getIdWorkspace()%>"><%=w.getNombre()%></a>
            <%
                    } %>
                    <p> <label>Gestión de Espacios</label> </p>
            <%         
                }else{
            %>
                <p><label>Crea tu primer workspace</label></p>
            <% 
                }
            %>
            
            <button class="btn-toggle" onclick="toggleForm('formW')">Agregar Workspace</button>
                    
            <form id="formW" class="form-oculto" action="ServletWorkspace" method="POST">
                <div>
                    <label>Nombre del workspace:</label>
                    <input type="text" name="txtNombreW" placeholder="Workspace" required>
                </div>
                <button id="btnNuevoW" type="submit"> Crear </button>
            </form>
            
            <a href="ServletCerrarSesion">Cerrar sesión</a>
        </div>

        <script>
            function toggleForm(id) {
                var f = document.getElementById(id);
                if (f.style.display === "block") {
                    f.style.display = "none";
                } else {
                    f.style.display = "block";
                }
            }
        </script>
    </body>
</html>