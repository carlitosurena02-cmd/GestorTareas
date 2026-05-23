<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="modelo.Usuario"%>
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
        <a href="CerrarSesion">Cerrar sesión</a>
    </body>
</html>
