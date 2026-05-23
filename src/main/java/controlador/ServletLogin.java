package controlador;

import modelo.Usuario;

import datos.UsuarioDAO;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ServletLogin")

public class ServletLogin extends HttpServlet{
    
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest hsreq, HttpServletResponse hsres)throws ServletException, IOException{
        
        String user = hsreq.getParameter("txtUser");
        String pass = hsreq.getParameter("txtPass");
        
        if(user.isEmpty()|| pass.isEmpty()){
             
            hsreq.setAttribute("mensajeError", "Por favor, llene todos los campos solicitados.");

            hsreq.getRequestDispatcher("Login.jsp").forward(hsreq, hsres); 
            return;
        }
        
        Usuario usuario = new UsuarioDAO().login(user,pass);
        
        if(usuario != null){
            
            HttpSession sesion = hsreq.getSession();
            sesion.setAttribute("usuarioLogueado", usuario);
            hsres.sendRedirect("Menu.jsp");
            
        }else{
            hsreq.setAttribute("mensajeError", "Usuario o Contraseña incorrectos");
            hsreq.getRequestDispatcher("Login.jsp").forward(hsreq, hsres);
        }
           
        
    }
    
}
