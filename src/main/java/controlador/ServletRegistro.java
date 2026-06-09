
package controlador;

import datos.UsuarioDAO;
import modelo.Usuario;

import java.util.*;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.time.LocalDate;

@WebServlet("/ServletRegistro")

public class ServletRegistro extends HttpServlet{
    
     private static final long serialVersionUID = 1L;
     
     @Override
     protected void doPost(HttpServletRequest hsreq, HttpServletResponse hsres)
             throws ServletException, IOException{
         
        String nombre = hsreq.getParameter("txtNombre");
        String app = hsreq.getParameter("txtApellidoP");
        String apm = hsreq.getParameter("txtApellidoM");
        String email = hsreq.getParameter("txtEmail");
        String user = hsreq.getParameter("txtUsername");
        String pass = hsreq.getParameter("txtPassword_");
        String dob = hsreq.getParameter("txtDOB");
        
        
        if(nombre.isEmpty()||app.isEmpty()||apm.isEmpty()||user.isEmpty()|| pass.isEmpty()||email.isEmpty()||dob.isEmpty()){
         
        hsreq.setAttribute("mensajeError", "Por favor, llene todos los campos solicitados.");
        
        hsreq.getRequestDispatcher("Registro.jsp").forward(hsreq, hsres);
        return; 
        }
        
        if(UsuarioDAO.emailExist(email)){
            hsreq.setAttribute("mensajeError", "Correo anteriormente usado");
            hsreq.getRequestDispatcher("Registro.jsp").forward(hsreq, hsres);
            return;
        }
        
        if(UsuarioDAO.userExist(user)){
            hsreq.setAttribute("mensajeError", "Usuario repetido");
            hsreq.getRequestDispatcher("Registro.jsp").forward(hsreq, hsres);
            return;
        }
        
        Usuario usuario = new Usuario(nombre, app, apm, email, user, pass, LocalDate.parse(dob));
        
        int registro = new UsuarioDAO().insert(usuario);
        if (registro == 1){
        hsres.sendRedirect("Login.jsp");
        }else{
            hsreq.setAttribute("mensajeError", "Error, intentelo de nuevo");
            hsreq.getRequestDispatcher("Registro.jsp").forward(hsreq, hsres);
        }
        
    }
    
}
