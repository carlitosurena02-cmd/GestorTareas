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
        <title>Pagina principal</title>
    </head>
    <body>
        <h1>Bienvenido ${usuarioLogueado.nombre}</h1>
        
        <%
            List<Workspace> lista = (List<Workspace>) request.getAttribute("workspacesList");
            if(lista != null && !lista.isEmpty()) {
                for(Workspace w : lista) {
        %>
                <a href="ServletProyecto?idWorkspace=<%=w.getIdWorkspace()%>"><%=w.getNombre()%></a>
        <%
                } %>
                <p> <label>Agrega un nuevo workspace</label> </p>
        <%        
            }else{
        %>
            <p><label>Crea tu primer workspace</label></p>
        <% 
            }
        %>
                
        
        <form action="ServletWorkspace" method="POST">
            <div>
                <label>Nombre del workspace:</label>
                <input type="text" name="txtNombreW" placeholder = "Workspace" required>
            </div>
            
            <button id="btnNuevoW" type="submit"> Crear </button>
        </form>
        
        
        <a href="ServletCerrarSesion">Cerrar sesión</a>
    </body>
</html>
