package controlador;

import modelo.Usuario;
import modelo.Workspace;

import datos.UsuarioDAO;
import datos.WorkspaceDAO;
import datos.UsuarioWorkspaceDAO;

import java.io.IOException;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@WebServlet("/ServletWorkspace")
public class ServletWorkspace extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest hsreq, HttpServletResponse hsres) 
            throws ServletException, IOException {
        Usuario user = getUsuarioSesion(hsreq, hsres);
        
        List<Workspace> ws = new WorkspaceDAO().listWorkspace(user.getIdUsuario());
        
        hsreq.setAttribute("workspacesList", ws);
        hsreq.getRequestDispatcher("Menu.jsp").forward(hsreq, hsres);
    }
    
    @Override
    protected void doPost(HttpServletRequest hsreq, HttpServletResponse hsres)
            throws ServletException, IOException {
        
        Usuario user = getUsuarioSesion(hsreq, hsres);
        HttpSession sesion = hsreq.getSession();
        String accion = hsreq.getParameter("accion");
        
        if ("eliminar".equals(accion)) {
            int idWorkspace = Integer.parseInt(hsreq.getParameter("idWorkspace"));
            int rolUsuarioActual = new UsuarioWorkspaceDAO().getRol(user.getIdUsuario(), idWorkspace);
            if (rolUsuarioActual != 1) {
                sesion.setAttribute("MensajeError", "No tienes permisos de administrador.");
                hsres.sendRedirect("ServletWorkspace");
                return;
            }
            new WorkspaceDAO().deleteWorkspaces(idWorkspace); 
            hsres.sendRedirect("ServletWorkspace");
            return;
        }
        
        if ("agregarMiembro".equals(accion)) {
            int idWorkspace = Integer.parseInt(hsreq.getParameter("idWorkspace"));
            int rolUsuarioActual = new UsuarioWorkspaceDAO().getRol(user.getIdUsuario(), idWorkspace);
            if (rolUsuarioActual != 1) {
                sesion.setAttribute("MensajeError", "No tienes permisos de administrador.");
                hsres.sendRedirect("ServletProyecto?idWorkspace=" + idWorkspace);
                return;
            }
            
            String username = hsreq.getParameter("txtUsername");
            int rol = Integer.parseInt(hsreq.getParameter("txtRol"));
            
            int idInvitado = UsuarioDAO.userId(username);
            if (idInvitado <= 0) {
                sesion.setAttribute("MensajeError", "El usuario '" + username + "' no existe.");
                hsres.sendRedirect("ServletProyecto?idWorkspace=" + idWorkspace);
                return;
            }
            
            new UsuarioWorkspaceDAO().addMember(idInvitado, idWorkspace, rol);
            hsres.sendRedirect("ServletProyecto?idWorkspace=" + idWorkspace);
            return;
        }
        
        if ("eliminarMiembro".equals(accion)) {
            int idWorkspace = Integer.parseInt(hsreq.getParameter("idWorkspace"));
            int rolUsuarioActual = new UsuarioWorkspaceDAO().getRol(user.getIdUsuario(), idWorkspace);
            if (rolUsuarioActual != 1) {
                sesion.setAttribute("MensajeError", "No tienes permisos de administrador.");
                hsres.sendRedirect("ServletProyecto?idWorkspace=" + idWorkspace);
                return;
            }
            
            String usernameTarget = hsreq.getParameter("txtUsernameTarget");
            int idTarget = UsuarioDAO.userId(usernameTarget);
            
            new UsuarioWorkspaceDAO().deleteUser(idTarget, idWorkspace);
            hsres.sendRedirect("ServletProyecto?idWorkspace=" + idWorkspace);
            return;
        }
        
        String wsn = hsreq.getParameter("txtNombreW");
        if (wsn == null || wsn.isEmpty()) {
            hsreq.setAttribute("mensajeErrorNombre", "Escriba un nombre");
            hsreq.getRequestDispatcher("Menu.jsp").forward(hsreq, hsres);
        } else {
            if (new WorkspaceDAO().createWorkspace(user.getIdUsuario(), wsn) != 0) {
                hsres.sendRedirect("ServletWorkspace");
            } else {
                hsreq.setAttribute("mensajeErrorWorkspace", "Error al crear");
                hsreq.getRequestDispatcher("Menu.jsp").forward(hsreq, hsres);
            }        
        }
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