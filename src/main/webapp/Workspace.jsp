<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="modelo.Proyecto" %>
<%@page import="modelo.Usuario" %>
<%@page import="modelo.Workspace" %>

<%@page import="java.util.*" %>

<%
    HttpSession sesionActual = request.getSession(false);
    Usuario usuarioActual = (sesionActual != null) ? 
                            (Usuario) sesionActual.getAttribute("usuarioLogueado") : null;
    if(usuarioActual == null){
        response.sendRedirect("Login.jsp");
        return;
    }
    
    Workspace workspace = (Workspace) sesionActual.getAttribute("workspaceActual");
    
    if(workspace == null){
        response.sendRedirect("ServletProyecto");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= workspace.getNombre() %></title>
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
                padding: 40px 20px;
                box-sizing: border-box;
            }

            .tarjeta2 {
                background: white;
                padding: 40px;
                border-radius: 20px;
                width: 100%;
                max-width: 750px;
                box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
                box-sizing: border-box;
            }

            h1 {
                color: #023e8a;
                margin-top: 0;
                margin-bottom: 20px;
            }

            h2 {
                color: #0077b6;
                font-size: 20px;
                margin-bottom: 15px;
            }

            .proyecto-item {
                background: #ffffff;
                border: 1px solid #e0e1dd;
                border-left: 5px solid #0077b6;
                padding: 16px 20px;
                border-radius: 8px;
                margin-bottom: 15px;
                box-sizing: border-box;
                position: relative;
            }

            h3 {
                margin: 0 200px 0 0;
            }

            h3 a {
                color: #023e8a;
                font-size: 18px;
                font-weight: bold;
                text-decoration: none;
            }

            h3 a:hover {
                color: #0077b6;
                text-decoration: underline;
            }

            p {
                margin: 6px 0;
                color: #4a4e69;
                font-size: 14px;
            }

            p strong {
                color: #0077b6;
            }

            form {
                margin-top: 20px;
                border-top: 1px solid #e0e1dd;
                padding-top: 20px;
            }

            .form-inline-del {
                position: absolute;
                top: 16px;
                right: 20px;
                margin: 0;
                padding: 0;
                border: none;
            }

            .btn-delete {
                background: #d90429;
                padding: 6px 12px;
                font-size: 12px;
                width: auto;
                margin: 0;
            }

            .btn-delete:hover {
                background: #b00020;
            }

            label {
                color: #0077b6;
                font-weight: bold;
                font-size: 14px;
                margin-bottom: 4px;
                display: block;
            }

            input[type="text"], input[type="date"], select {
                width: 100%;
                padding: 12px;
                border: 2px solid #e0e1dd;
                border-radius: 10px;
                font-size: 15px;
                box-sizing: border-box;
                margin-bottom: 12px;
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
            }

            button:hover {
                background: #0077b6;
                transform: translateY(-2px);
            }

            .btn-toggle {
                background: #0077b6;
                margin-bottom: 20px;
            }

            .btn-toggle:hover {
                background: #023e8a;
            }

            .form-oculto {
                display: none;
            }

            .error-box {
                color: #d90429;
                font-weight: bold;
                margin-top: 15px;
                text-align: center;
            }

            a[href="ServletWorkspace"] {
                display: block;
                text-align: center;
                margin-top: 25px;
                color: #0077b6;
                font-weight: bold;
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <div class="tarjeta2">
            <div style="position: relative;">
                <h1>Workspace: <%= workspace.getNombre() %></h1>
                <form class="form-inline-del" action="ServletWorkspace" method="POST">
                    <input type="hidden" name="accion" value="eliminar">
                    <input type="hidden" name="idWorkspace" value="<%= workspace.getIdWorkspace() %>">
                    <button type="submit" class="btn-delete">Eliminar Workspace</button>
                </form>
            </div>
            
            <h2>Mis Proyectos</h2>

            <% 
                List<Proyecto> listaProyectos = (List<Proyecto>) request.getAttribute("proyectList");
                if(listaProyectos != null && !listaProyectos.isEmpty()) {
                    for(Proyecto p : listaProyectos) {
            %>
                    <div class="proyecto-item">
                        <h3>
                            <a href="ServletTarea?idProyecto=<%= p.getIdProyecto() %>">
                                <%= p.getNombre() %>
                            </a>
                        </h3>
                        <p>
                            <strong>Inicio:</strong> <%= p.getFechaInicio() %> | 
                            <strong>Fin:</strong> <%= p.getFechaFin() != null ? p.getFechaFin() : "Sin fecha límite" %>
                        </p>
                        <p>
                            <strong>Descripción:</strong> <%= p.getDescripcion() != null ? p.getDescripcion() : "No hay descripción" %>
                        </p>
                        
                        <form class="form-inline-del" action="ServletProyecto" method="POST">
                            <input type="hidden" name="accion" value="eliminar">
                            <input type="hidden" name="idProyecto" value="<%= p.getIdProyecto() %>">
                            <button type="submit" class="btn-delete">Eliminar Proyecto</button>
                        </form>
                    </div>
            <%
                    }
                } else {
            %>
                <p>No hay proyectos aún en este espacio de trabajo.</p>
            <%
                }
            %>
            
            <div style="margin-top: 25px; display: flex; gap: 15px;">
                <button class="btn-toggle" onclick="toggleForm('formP')">Agregar Proyecto</button>
                <button class="btn-toggle" style="background:#4a4e69;" onclick="toggleForm('formM')">Asignar Miembro</button>
            </div>
                
            <form id="formP" class="form-oculto" action="ServletProyecto" method="POST">
                <label>Nombre del proyecto:</label>
                <input type="text" name="txtNombre" placeholder="MiProyecto" required>
                
                <label>Descripción del proyecto:</label>
                <input type="text" name="txtDescripcion" placeholder="¿De qué trata?" required>
                
                <label>Fecha límite del proyecto:</label>
                <input type="date" name="txtFechaFin">
                
                <button type="submit"> Crear Proyecto </button>
            </form>

            <form id="formM" class="form-oculto" action="ServletWorkspace" method="POST">
                <input type="hidden" name="accion" value="agregarMiembro">
                <input type="hidden" name="idWorkspace" value="<%= workspace.getIdWorkspace() %>">
                
                <label>Username del Colaborador:</label>
                <input type="text" name="txtUsername" placeholder="Ej: carlos99" required>
                
                <label>Rol organizativo:</label>
                <select name="txtRol">
                    <option value="1">Administrador</option>
                    <option value="2">Miembro Técnico</option>
                </select>
                
                <button type="submit"> Vincular al Workspace </button>
            </form>

            <form id="formRM" class="form-oculto" action="ServletWorkspace" method="POST" style="border-top:none; margin-top:10px;">
                <input type="hidden" name="accion" value="eliminarMiembro">
                <input type="hidden" name="idWorkspace" value="<%= workspace.getIdWorkspace() %>">
                <label>Remover Miembro por Username:</label>
                <input type="text" name="txtUsernameTarget" placeholder="Username a remover" required>
                <button type="submit" class="btn-delete" style="width:100%; padding:14px;"> Desvincular Miembro </button>
            </form>
            <button class="btn-toggle" style="background:#6c757d; margin-top:10px;" onclick="toggleForm('formRM')">Gestionar Desvinculaciones</button>
                
            <div class="error-box">${mensajeError}</div>

            <a href="ServletWorkspace">Volver al Menú Principal</a>
        </div>

        <script>
            function toggleForm(id) {
                var f = document.getElementById(id);
                f.style.display = (f.style.display === "block") ? "none" : "block";
            }
        </script>
    </body>
</html>