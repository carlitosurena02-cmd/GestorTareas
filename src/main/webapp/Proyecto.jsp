<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="modelo.Proyecto" %>
<%@page import="modelo.Usuario" %> 
<%@page import="modelo.Tarea" %>
<%@page import="modelo.Etiqueta" %>
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
    
    Proyecto proyectoActual = (Proyecto) sesionActual.getAttribute("proyectoActual");
    
    List<Tarea> tareas = (List<Tarea>) request.getAttribute("tareaList");
    List<Etiqueta> etiquetas = (List<Etiqueta>) request.getAttribute("etiquetaList");
    int rolActual = (Integer) request.getAttribute("rolActual");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= proyectoActual.getNombre() %></title>
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
                padding: 50px 20px;
                box-sizing: border-box;
            }

            .tarjeta2 {
                background: white;
                padding: 40px;
                border-radius: 20px;
                width: 100%;
                max-width: 800px;
                box-shadow: 0 10px 35px rgba(0, 0, 0, 0.15);
                box-sizing: border-box;
            }

            h1 {
                color: #023e8a;
                margin-top: 0;
                margin-bottom: 5px;
            }

            h2 {
                color: #023e8a;
                font-size: 20px;
                border-bottom: 2px solid #e0e1dd;
                padding-bottom: 8px;
                margin-top: 30px;
            }

            hr {
                border: 0;
                height: 1px;
                background: #e0e1dd;
                margin: 20px 0;
            }

            .error-msg-box {
                background: #ffe3e3;
                color: #d90429;
                padding: 12px 20px;
                border-radius: 10px;
                border: 1px solid #ffb3b3;
                font-weight: bold;
                margin-bottom: 20px;
            }

            ul {
                list-style: none;
                padding: 0;
                display: flex;
                flex-wrap: wrap;
                gap: 10px;
                margin: 15px 0;
            }

            ul li {
                background: #eef1f6;
                color: #023e8a;
                padding: 6px 14px;
                border-radius: 20px;
                font-size: 13px;
                font-weight: bold;
                border: 1px solid #0077b6;
            }

            form div {
                margin-bottom: 12px;
            }

            label {
                display: block;
                color: #0077b6;
                font-weight: bold;
                margin-bottom: 6px;
                font-size: 14px;
            }

            input[type="text"], input[type="date"], select, textarea {
                width: 100%;
                padding: 12px;
                border: 2px solid #e0e1dd;
                border-radius: 10px;
                font-size: 15px;
                font-family: inherit;
                box-sizing: border-box;
                transition: border 0.2s;
                background-color: #fff;
            }

            textarea {
                height: 80px;
                resize: vertical;
            }

            input:focus, select:focus, textarea:focus {
                border: 2px solid #0077b6;
                outline: none;
            }

            form > button[type="submit"] {
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
                margin-top: 5px;
            }

            form > button[type="submit"]:hover {
                background: #0077b6;
                transform: translateY(-2px);
            }

            .btn-toggle {
                background: #0077b6;
                color: white;
                border: none;
                padding: 12px 20px;
                border-radius: 10px;
                font-weight: bold;
                cursor: pointer;
                margin-bottom: 15px;
                margin-right: 10px;
            }

            .btn-toggle:hover {
                background: #023e8a;
            }

            .form-oculto {
                display: none;
                margin-top: 15px;
                border: 1px solid #e0e1dd;
                padding: 20px;
                border-radius: 12px;
                background: #f8f9fa;
            }

            .btn-danger-action {
                background: #d90429;
                margin-top: 10px;
            }

            .btn-danger-action:hover {
                background: #b00020;
            }

            .tarea-card {
                background: #f8f9fa;
                border: 1px solid #e0e1dd !important;
                border-radius: 12px;
                padding: 20px !important;
                margin-bottom: 20px !important;
                box-sizing: border-box;
                position: relative;
            }

            .tarea-card h3 {
                margin-top: 0;
                color: #023e8a;
                font-size: 18px;
                border-bottom: 1px solid #e0e1dd;
                padding-bottom: 6px;
                margin-right: 120px;
            }

            .tarea-card p {
                margin: 8px 0;
                font-size: 14px;
                color: #333;
            }

            .tarea-card p strong {
                color: #0077b6;
            }

            .tarea-card form {
                display: flex !important;
                align-items: center;
                gap: 10px;
                margin-top: 15px;
                border-top: 1px dashed #e0e1dd;
                padding-top: 15px;
                margin-bottom: 0;
            }

            .tarea-card form label {
                margin-bottom: 0;
                white-space: nowrap;
            }

            .tarea-card form select {
                width: auto;
                padding: 8px 12px;
                font-size: 14px;
            }

            .tarea-card form button[type="submit"] {
                width: auto;
                background: #0077b6;
                padding: 8px 16px;
                font-size: 14px;
                border-radius: 8px;
                margin-top: 0;
            }

            .btn-abs-delete {
                position: absolute;
                top: 20px;
                right: 20px;
                background: #d90429;
                color: white;
                border: none;
                padding: 6px 12px;
                font-size: 12px;
                border-radius: 6px;
                cursor: pointer;
            }

            .btn-abs-delete:hover {
                background: #b00020;
            }

            a[href*="idWorkspace"] {
                display: block;
                text-align: center;
                margin-top: 30px;
                color: #0077b6;
                font-weight: bold;
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <div class="tarjeta2">
            <% 
                String mensajeError = (String) request.getAttribute("MensajeError");
                if(mensajeError != null) { 
            %>
                <div class="error-msg-box">
                    <%= mensajeError %>
                </div>
            <% } %>

            <h1>Proyecto: <%= proyectoActual.getNombre() %></h1>
            <p><strong>Workspace:</strong> <%= workspace.getNombre() %></p>
            <hr>

            <% if(rolActual == 1) { %>
                <h2>Administración del Proyecto (Modo Administrador)</h2>
                
                <div style="margin-top:15px;">
                    <button class="btn-toggle" onclick="toggleForm('formE')">Gestionar Etiquetas</button>
                    <button class="btn-toggle" onclick="toggleForm('formT')">Nueva Tarea</button>
                    <button class="btn-toggle" style="background:#4a4e69;" onclick="toggleForm('formAP')">Asignar al Proyecto</button>
                    <button class="btn-toggle" style="background:#6c757d;" onclick="toggleForm('formRP')">Remover del Proyecto</button>
                </div>
                
                <div id="formE" class="form-oculto">
                    <form action="ServletEtiqueta" method="POST">
                        <div>
                            <label>Nueva Etiqueta:</label>
                            <input type="text" name="txtNombreEtiqueta" placeholder="Ej: Urgente" required>
                        </div>
                        <button type="submit">Crear Etiqueta</button>
                    </form>
                    <h3>Etiquetas actuales:</h3>
                    <ul>
                        <% 
                            if(etiquetas != null && !etiquetas.isEmpty()) {
                                for(Etiqueta e : etiquetas) {
                        %>
                                    <li><%= e.getDescripcion() %> (ID: <%= e.getIdEtiqueta() %>)</li>
                        <% 
                                }
                            } else {
                        %>
                            <li style="border:none; background:none; color:#777; padding:0; font-weight:normal;">Sin etiquetas.</li>
                        <% } %>
                    </ul>
                </div>

                <form id="formT" class="form-oculto" action="ServletTarea" method="POST">
                    <input type="hidden" name="accion" value="crear">
                    <div>
                        <label>Nombre de la tarea (*):</label>
                        <input type="text" name="txtNombre" required>
                    </div>
                    <div>
                        <label>Descripción (*):</label>
                        <textarea name="txtDescripcion" required></textarea>
                    </div>
                    <div>
                        <label>Fecha Límite:</label>
                        <input type="date" name="txtFechaLim">
                    </div>
                    <div>
                        <label>Prioridad:</label>
                        <select name="txtPrioridad">
                            <option value="1">Alta</option>
                            <option value="2">Media</option>
                            <option value="3">Baja</option>
                        </select>
                    </div>
                    <div>
                        <label>Etiqueta:</label>
                        <select name="txtEtiqueta">
                            <option value="0">Sin etiqueta</option>
                            <% if(etiquetas != null) { for(Etiqueta e : etiquetas) { %>
                                <option value="<%= e.getIdEtiqueta() %>"><%= e.getDescripcion() %></option>
                            <% } } %>
                        </select>
                    </div>
                    <button type="submit">Guardar Tarea</button>
                </form>

                <form id="formAP" class="form-oculto" action="ServletProyecto" method="POST">
                    <input type="hidden" name="accion" value="agregarMiembroProyecto">
                    <div>
                        <label>Username del Colaborador:</label>
                        <input type="text" name="txtUsernameProyect" placeholder="Username" required>
                    </div>
                    <div>
                        <label>Rol en el Proyecto:</label>
                        <select name="txtRolProyect">
                            <option value="1">Administrador de Proyecto</option>
                            <option value="2">Desarrollador asignado</option>
                        </select>
                    </div>
                    <button type="submit">Asignar Miembro</button>
                </form>

                <form id="formRP" class="form-oculto" action="ServletProyecto" method="POST">
                    <input type="hidden" name="accion" value="eliminarMiembroProyecto">
                    <div>
                        <label>Remover Miembro del Equipo (Username):</label>
                        <input type="text" name="txtUsernameTargetProyect" placeholder="Username" required>
                    </div>
                    <button type="submit" class="btn-danger-action">Remover del Proyecto</button>
                </form>
                <hr>
            <% } %>

            <h2>Tablero de Tareas</h2>
            
            <% 
                if(tareas != null && !tareas.isEmpty()) {
                    for(Tarea t : tareas) {
            %>
                    <div class="tarea-card">
                        <h3><%= t.getNombre() %></h3>
                        
                        <% if(rolActual == 1) { %>
                            <form action="ServletTarea" method="POST" style="margin:0; padding:0; border:none; display:inline;">
                                <input type="hidden" name="accion" value="eliminar">
                                <input type="hidden" name="idTarea" value="<%= t.getIdTarea() %>">
                                <button type="submit" class="btn-abs-delete">Borrar</button>
                            </form>
                        <% } %>

                        <p><strong>Descripción:</strong> <%= t.getDescripcion() %></p>
                        <p><strong>Prioridad:</strong> <%= t.getPrioridad() %></p>
                        <p><strong>Etiqueta (ID):</strong> <%= t.getEtiqueta() %></p>
                        <p><strong>Fecha Límite:</strong> <%= t.getFechaLim() != null ? t.getFechaLim() : "Sin asignar" %></p>
                        <p><strong>Progreso Actual:</strong> 
                            <% 
                                if(t.getProgreso() == 1) { out.print("Pendiente"); }
                                else if(t.getProgreso() == 2) { out.print("En progreso"); }
                                else if(t.getProgreso() == 3) { out.print("Completada"); }
                            %>
                        </p>

                        <form action="ServletTarea" method="POST">
                            <input type="hidden" name="accion" value="cambiarProgreso">
                            <input type="hidden" name="idTarea" value="<%= t.getIdTarea() %>">
                            
                            <label>Cambiar estado:</label>
                            <select name="nuevoEstado">
                                <option value="1" <%= t.getProgreso() == 1 ? "selected" : "" %>>Pendiente</option>
                                <option value="2" <%= t.getProgreso() == 2 ? "selected" : "" %>>En progreso</option>
                                <option value="3" <%= t.getProgreso() == 3 ? "selected" : "" %>>Completada</option>
                            </select>
                            <button type="submit">Actualizar</button>
                        </form>

                        <% if(rolActual == 1) { %>
                            <button class="btn-toggle" style="margin-top:15px; font-size:12px; padding:6px 12px;" onclick="toggleForm('formAT_<%= t.getIdTarea() %>')">Asignar Encargado</button>
                            <button class="btn-toggle" style="margin-top:15px; font-size:12px; padding:6px 12px; background:#6c757d;" onclick="toggleForm('formRT_<%= t.getIdTarea() %>')">Remover Encargado</button>
                            
                            <form id="formAT_<%= t.getIdTarea() %>" class="form-oculto" action="ServletTarea" method="POST" style="flex-direction:column; align-items:flex-start; gap:5px;">
                                <input type="hidden" name="accion" value="asignarUsuarioTarea">
                                <input type="hidden" name="idTarea" value="<%= t.getIdTarea() %>">
                                <label>Username del encargado:</label>
                                <input type="text" name="txtUsernameTarea" placeholder="Username" required style="width:100%;">
                                <button type="submit" style="margin-top:5px; padding:8px 16px;">Asignar</button>
                            </form>

                            <form id="formRT_<%= t.getIdTarea() %>" class="form-oculto" action="ServletTarea" method="POST" style="flex-direction:column; align-items:flex-start; gap:5px;">
                                <input type="hidden" name="accion" value="desasignarUsuarioTarea">
                                <input type="hidden" name="idTarea" value="<%= t.getIdTarea() %>">
                                <label>Username a remover:</label>
                                <input type="text" name="txtUsernameTargetTarea" placeholder="Username" required style="width:100%;">
                                <button type="submit" class="btn-danger-action" style="margin-top:5px; padding:8px 16px; width:auto;">Remover</button>
                            </form>
                        <% } %>
                    </div>
            <% 
                    }
                } else {
            %>
                <p>Este proyecto aún no tiene tareas asignadas.</p>
            <%  } %>

            <hr>
            <p><a href="ServletProyecto?idWorkspace=<%= workspace.getIdWorkspace() %>">Volver al Workspace</a></p>
        </div>

        <script>
            function toggleForm(id) {
                var f = document.getElementById(id);
                f.style.display = (f.style.display === "block") ? "none" : "block";
            }
        </script>
    </body>
</html>