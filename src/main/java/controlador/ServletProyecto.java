package controlador;

import modelo.Usuario;
import modelo.Proyecto;
import modelo.Workspace;
import datos.ProyectoDAO;
import datos.WorkspaceDAO;
import datos.UsuarioWorkspaceDAO;
import datos.UsuarioDAO;
import datos.UsuarioProyectoDAO;

import java.io.IOException;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;


import jakarta.servlet.http.*;
import java.time.LocalDate;

@WebServlet("/ServletProyecto")
public class ServletProyecto extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest hsreq, HttpServletResponse hsres)
            throws ServletException, IOException {
        
        Usuario user = getUsuarioSesion(hsreq, hsres);
        int workspaceId = Integer.parseInt(hsreq.getParameter("idWorkspace"));
        Workspace workspace = new WorkspaceDAO().searchById(workspaceId);
        
        HttpSession sesion = hsreq.getSession();
        sesion.setAttribute("workspaceActual", workspace);
        
        int rol = new UsuarioWorkspaceDAO().getRol(user.getIdUsuario(), workspaceId);
        
        if (rol <= 0) {
            hsres.sendRedirect("ServletWorkspace");
            return;
        }
        
        List<Proyecto> proyects = new ProyectoDAO().listProyects(rol, workspaceId, user.getIdUsuario());
        
        hsreq.setAttribute("proyectList", proyects);
        hsreq.getRequestDispatcher("Workspace.jsp").forward(hsreq, hsres);
    }    
    
    @Override
    protected void doPost(HttpServletRequest hsreq, HttpServletResponse hsres)
            throws ServletException, IOException {
        
        Usuario user = getUsuarioSesion(hsreq, hsres);
        HttpSession sesion = hsreq.getSession();
        Workspace workspace = (Workspace) sesion.getAttribute("workspaceActual");
        Proyecto proyectoActual = (Proyecto) sesion.getAttribute("proyectoActual");
        
        String accion = hsreq.getParameter("accion");
        int rolUsuarioActual = new UsuarioProyectoDAO().getRolUserProyect(user.getIdUsuario(), proyectoActual != null ? proyectoActual.getIdProyecto() : 0);
        
        if ("eliminar".equals(accion)) {
            int idProyecto = Integer.parseInt(hsreq.getParameter("idProyecto"));
            int rolValidacion = new UsuarioProyectoDAO().getRolUserProyect(user.getIdUsuario(), idProyecto);
            if (rolValidacion != 1) {
                sesion.setAttribute("MensajeError", "No tienes permisos de administrador.");
                hsres.sendRedirect("ServletProyecto?idWorkspace=" + workspace.getIdWorkspace());
                return;
            }
            new ProyectoDAO().deleteProyect(idProyecto); 
            hsres.sendRedirect("ServletProyecto?idWorkspace=" + workspace.getIdWorkspace());
            return;
        }
        
        if ("agregarMiembroProyecto".equals(accion)) {
            if (rolUsuarioActual != 1) {
                sesion.setAttribute("MensajeError", "No tienes permisos de administrador.");
                hsres.sendRedirect("ServletTarea?idProyecto=" + proyectoActual.getIdProyecto());
                return;
            }
            
            String username = hsreq.getParameter("txtUsernameProyect");
            int rolProyect = Integer.parseInt(hsreq.getParameter("txtRolProyect"));
            
            int idInvitado = UsuarioDAO.userId(username);
            if (idInvitado <= 0) {
                sesion.setAttribute("MensajeError", "El usuario '" + username + "' no existe.");
                hsres.sendRedirect("ServletTarea?idProyecto=" + proyectoActual.getIdProyecto());
                return;
            }
            
            new UsuarioProyectoDAO().addUser(idInvitado, proyectoActual.getIdProyecto(), rolProyect);
            hsres.sendRedirect("ServletTarea?idProyecto=" + proyectoActual.getIdProyecto());
            return;
        }
        
        if ("eliminarMiembroProyecto".equals(accion)) {
            if (rolUsuarioActual != 1) {
                sesion.setAttribute("MensajeError", "No tienes permisos de administrador.");
                hsres.sendRedirect("ServletTarea?idProyecto=" + proyectoActual.getIdProyecto());
                return;
            }
            
            String usernameTarget = hsreq.getParameter("txtUsernameTargetProyect");
            int idTarget = UsuarioDAO.userId(usernameTarget);
            
            new UsuarioProyectoDAO().deleteUser(idTarget, proyectoActual.getIdProyecto());
            hsres.sendRedirect("ServletTarea?idProyecto=" + proyectoActual.getIdProyecto());
            return;
        }
        
        String name = hsreq.getParameter("txtNombre");
        String desc = hsreq.getParameter("txtDescripcion");
        LocalDate fechaFin = (!hsreq.getParameter("txtFechaFin").isEmpty() && hsreq.getParameter("txtFechaFin") != null) ? LocalDate.parse(hsreq.getParameter("txtFechaFin")) : null;
        
        if (name.isEmpty() || desc.isEmpty()) {
            hsreq.setAttribute("MensajeError", "¡Llena todos los campos!");
            hsreq.getRequestDispatcher("Workspace.jsp").forward(hsreq, hsres);
            return;
        }
       
        Proyecto p = new Proyecto(name, desc, LocalDate.now(), fechaFin, 1, workspace.getIdWorkspace());
        new ProyectoDAO().createProyect(p, user.getIdUsuario());
        
        hsres.sendRedirect("ServletProyecto?idWorkspace=" + workspace.getIdWorkspace());        
    }
    
    private Usuario getUsuarioSesion(HttpServletRequest hsreq, HttpServletResponse hsres) 
            throws IOException {
        HttpSession sesion = hsreq.getSession(false);
        Usuario usuario = (sesion != null) ? 
                          (Usuario) sesion.getAttribute("usuarioLogueado") : null;
        if (usuario == null) {
            hsres.sendRedirect("Login.jsp");
        }
        return usuario;
    }
    
}