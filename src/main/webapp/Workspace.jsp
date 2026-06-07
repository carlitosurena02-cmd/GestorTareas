<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
<!-- formulario crear proyecto + lista de proyectos -->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><% %></title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
