
package controlador;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@WebServlet("/ServletCerrarSesion")

public class ServletCerrarSesion extends HttpServlet{
    
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest hsreq, HttpServletResponse hsres) 
            throws ServletException,IOException{
        
        HttpSession sesion = hsreq.getSession(false);
        
        if(sesion != null){
            sesion.invalidate(); // destruye toda la sesión
        }
        hsres.sendRedirect("Login.jsp");
             
        
    }
        
}
