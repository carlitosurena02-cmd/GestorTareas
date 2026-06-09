package controlador;

import modelo.Usuario;
import modelo.Tarea;
import modelo.Etiqueta;
import modelo.Proyecto;

import datos.UsuarioProyectoDAO;
import datos.TareaDAO;
import datos.EtiquetaDAO;
import datos.ProyectoDAO;
import datos.UsuarioDAO;
import datos.UsuarioTareaDAO;

import java.io.IOException;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.time.LocalDate;


@WebServlet("/ServletTarea")
public class ServletTarea extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest hsreq, HttpServletResponse hsres)
            throws ServletException, IOException {
        
        Usuario user = getUsuarioSesion(hsreq, hsres);
        
        String proyectId = hsreq.getParameter("idProyecto");
        if (proyectId == null) {
            hsres.sendRedirect("ServletWorkspace");
            return;
        }
        int idProyecto = Integer.parseInt(proyectId);
        Proyecto proyectoActual = new ProyectoDAO().searchById(idProyecto);
        
        HttpSession sesion = hsreq.getSession();
        sesion.setAttribute("proyectoActual", proyectoActual);
        
        int rol = new UsuarioProyectoDAO().getRolUserProyect(user.getIdUsuario(), idProyecto);
        if (rol <= 0) {
            hsres.sendRedirect("ServletWorkspace");
            return;
        }
        
        List<Tarea> tareas = new TareaDAO().listWorks(user.getIdUsuario(), idProyecto, rol);
        List<Etiqueta> etiquetas = new EtiquetaDAO().listLabels(idProyecto);
        hsreq.setAttribute("tareaList", tareas);
        hsreq.setAttribute("etiquetaList", etiquetas);
        hsreq.setAttribute("rolActual", rol);
        
        hsreq.getRequestDispatcher("Proyecto.jsp").forward(hsreq, hsres);
    }
    
    @Override
    protected void doPost(HttpServletRequest hsreq, HttpServletResponse hsres)
            throws ServletException, IOException {
        
        Usuario user = getUsuarioSesion(hsreq, hsres);
        HttpSession sesion = hsreq.getSession();
        Proyecto proyecto = (Proyecto) sesion.getAttribute("proyectoActual");                
        
        String accion = hsreq.getParameter("accion");
        int rolUsuarioActual = new UsuarioProyectoDAO().getRolUserProyect(user.getIdUsuario(), proyecto.getIdProyecto());
        
        if ("eliminar".equals(accion)) {
            if (rolUsuarioActual != 1) {
                sesion.setAttribute("MensajeError", "No tienes permisos de administrador.");
                hsres.sendRedirect("ServletTarea?idProyecto=" + proyecto.getIdProyecto());
                return;
            }
            int idTarea = Integer.parseInt(hsreq.getParameter("idTarea"));
            new TareaDAO().deleteWorks(idTarea); 
            hsres.sendRedirect("ServletTarea?idProyecto=" + proyecto.getIdProyecto());
            return;
        }
        
        if ("asignarUsuarioTarea".equals(accion)) {
            if (rolUsuarioActual != 1) {
                sesion.setAttribute("MensajeError", "No tienes permisos de administrador.");
                hsres.sendRedirect("ServletTarea?idProyecto=" + proyecto.getIdProyecto());
                return;
            }
            
            int idTarea = Integer.parseInt(hsreq.getParameter("idTarea"));
            String username = hsreq.getParameter("txtUsernameTarea");
            
            int idInvitado = UsuarioDAO.userId(username);
            if (idInvitado <= 0) {
                sesion.setAttribute("MensajeError", "El usuario '" + username + "' no existe.");
                hsres.sendRedirect("ServletTarea?idProyecto=" + proyecto.getIdProyecto());
                return;
            }
            
            new UsuarioTareaDAO().addUsertoWork(idInvitado, idTarea);
            hsres.sendRedirect("ServletTarea?idProyecto=" + proyecto.getIdProyecto());
            return;
        }
        
        if ("desasignarUsuarioTarea".equals(accion)) {
            if (rolUsuarioActual != 1) {
                sesion.setAttribute("MensajeError", "No tienes permisos de administrador.");
                hsres.sendRedirect("ServletTarea?idProyecto=" + proyecto.getIdProyecto());
                return;
            }
            
            int idTarea = Integer.parseInt(hsreq.getParameter("idTarea"));
            String usernameTarget = hsreq.getParameter("txtUsernameTargetTarea");
            int idTarget = UsuarioDAO.userId(usernameTarget);
            
            new UsuarioTareaDAO().unassingUser(idTarget, idTarea);
            hsres.sendRedirect("ServletTarea?idProyecto=" + proyecto.getIdProyecto());
            return;
        }
        
        if ("cambiarProgreso".equals(accion)) {
            int idTarea = Integer.parseInt(hsreq.getParameter("idTarea"));
            int nuevoEstado = Integer.parseInt(hsreq.getParameter("nuevoEstado"));
            
            int check = new TareaDAO().switchProgress(idTarea, nuevoEstado);
            if (check <= 0) {
                 hsreq.setAttribute("MensajeError", "Error al cambiar progreso");
                 hsreq.getRequestDispatcher("Proyecto.jsp").forward(hsreq, hsres);
                 return;
            }
            hsres.sendRedirect("ServletTarea?idProyecto=" + proyecto.getIdProyecto());
            return;
        }
        
        if ("crear".equals(accion)) {
            if (rolUsuarioActual != 1) {
                sesion.setAttribute("MensajeError", "No tienes permisos de administrador.");
                hsres.sendRedirect("ServletTarea?idProyecto=" + proyecto.getIdProyecto());
                return;
            }
            
            String nombre = hsreq.getParameter("txtNombre");
            String desc = hsreq.getParameter("txtDescripcion");
            LocalDate fechaLim = (!hsreq.getParameter("txtFechaLim").isEmpty() && hsreq.getParameter("txtFechaLim") != null) ? LocalDate.parse(hsreq.getParameter("txtFechaLim")) : null;          
            int prioridad = Integer.parseInt(hsreq.getParameter("txtPrioridad"));
            int etiqueta = Integer.parseInt(hsreq.getParameter("txtEtiqueta"));
            
            if (nombre.isEmpty() || desc.isEmpty()) {
                 hsreq.setAttribute("MensajeError", "¡Llena todos los campos!");
                 hsreq.getRequestDispatcher("Proyecto.jsp").forward(hsreq, hsres);
                 return;
            }
            
            Tarea work = new Tarea(nombre, desc, LocalDate.now(), fechaLim, 1, prioridad, etiqueta, proyecto.getIdProyecto(), 1);
            new TareaDAO().insertWork(work);
            
            hsres.sendRedirect("ServletTarea?idProyecto=" + proyecto.getIdProyecto());
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