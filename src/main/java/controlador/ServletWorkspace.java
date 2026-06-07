package controlador;

import modelo.Usuario;
import modelo.Workspace;

import datos.UsuarioDAO;
import datos.WorkspaceDAO;

import java.io.IOException;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.time.LocalDate;

@WebServlet("/ServletWorkspace")

public class ServletWorkspace extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest hsreq, HttpServletResponse hsres) 
            throws ServletException, IOException{
        Usuario user = getUsuarioSesion(hsreq,hsres);
        
        List <Workspace> ws = new WorkspaceDAO().listWorkspace(user.getIdUsuario());
        
        hsreq.setAttribute("workspacesList", ws);
        hsreq.getRequestDispatcher("Menu.jsp").forward(hsreq, hsres);
    }
    
    @Override
    protected void doPost(HttpServletRequest hsreq, HttpServletResponse hsres)
            throws ServletException, IOException{
        
        Usuario user = getUsuarioSesion(hsreq,hsres);
        
        String wsn = hsreq.getParameter("txtNombreW");
        if(wsn == null || wsn.isEmpty()){
            
            hsreq.setAttribute("mensajeErrorNombre","Escriba un nombre");
            hsreq.getRequestDispatcher("Menu.jsp").forward(hsreq, hsres);
        
        }else{
            
            if(new WorkspaceDAO().createWorkspace(user.getIdUsuario(),wsn) != 0) {
               hsres.sendRedirect("ServletWorkspace");
            
            }else {
                hsreq.setAttribute("mensajeErrorWorkspace","Error al crear");
                hsreq.getRequestDispatcher("Menu.jsp").forward(hsreq, hsres);
            }        
        
         }
    }
    
    private Usuario getUsuarioSesion(HttpServletRequest hsreq, HttpServletResponse hsres) 
        throws IOException {
        HttpSession sesion = hsreq.getSession(false);
        Usuario usuario = (sesion != null) ? 
                          (Usuario) sesion.getAttribute("usuarioLogueado") : null;
        if(usuario == null) {
            hsres.sendRedirect("Login.jsp");
        }
        return usuario;
    }
    
}
