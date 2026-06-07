
package controlador;

import modelo.Usuario;
import modelo.Proyecto;


import datos.ProyectoDAO;
import datos.UsuarioWorkspaceDAO;

import java.io.IOException;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.time.LocalDate;


@WebServlet("/ServletProyecto")

public class ServletProyecto extends HttpServlet{
    
    // Lista los proyectos del workspace dependiendo si es admin o no
    @Override
    protected void doGet(HttpServletRequest hsreq, HttpServletResponse hsres)
            throws ServletException, IOException{
        
        Usuario user = getUsuarioSesion(hsreq, hsres);
        
        int workspaceId = Integer.parseInt(hsreq.getParameter("idWorkspace"));
        
        HttpSession sesion = hsreq.getSession();
        sesion.setAttribute("workspaceActual", workspaceId);
        
        int rol = new UsuarioWorkspaceDAO().getRol(user.getIdUsuario(), workspaceId);
        
        if(rol <= 0){
            hsres.sendRedirect("ServletWorkspace");
            return;
        }
        
        List<Proyecto> proyects = new ProyectoDAO().listProyects(rol , workspaceId, user.getIdUsuario());
        
        hsreq.setAttribute("proyectList", proyects);
        hsreq.getRequestDispatcher("Workspace.jsp").forward(hsreq, hsres);
        
    }    
    
    // Crea proyectos nuevos dentro del workspace
    @Override
    protected void doPost(HttpServletRequest hsreq, HttpServletResponse hsres)
            throws ServletException, IOException{
        
        Usuario user = getUsuarioSesion(hsreq, hsres);
        
        HttpSession sesion = hsreq.getSession();
        
        int workspaceId = (Integer) sesion.getAttribute("workspaceActual");
        
        String name = hsreq.getParameter("");
        String desc = hsreq.getParameter("");
        LocalDate fechaFin = LocalDate.parse(hsreq.getParameter(""));
        
        if(name.isEmpty() || desc.isEmpty()){
            hsreq.setAttribute("MensajeError", "¡Llena todos los campos!");
            hsreq.getRequestDispatcher("Menu.jsp").forward(hsreq, hsres);
            return;
        }
       
        Proyecto p = new Proyecto(name, desc, LocalDate.now(),fechaFin, 1, workspaceId);
        
        new ProyectoDAO().createProyect(p, user.getIdUsuario());
        
        hsres.sendRedirect("ServletProyecto?idWorkspace=" + workspaceId);
        
        
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
