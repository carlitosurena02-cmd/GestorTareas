package controlador;

import modelo.Usuario;
import modelo.Tarea;
import modelo.Etiqueta;

import datos.UsuarioProyectoDAO;
import datos.TareaDAO;
import datos.EtiquetaDAO;

import java.io.IOException;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.time.LocalDate;


@WebServlet("/ServletTarea")

public class ServletTarea extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest hsreq, HttpServletResponse hsres)
            throws ServletException, IOException{
        
        Usuario user = getUsuarioSesion(hsreq, hsres);
        
        String proyectId = hsreq.getParameter("idProyecto");
        if(proyectId == null){
            hsres.sendRedirect("ServletWorkspace");
            return;
        }
        
        HttpSession sesion = hsreq.getSession();
        sesion.setAttribute("proyectoActual", proyectId);
        
        int idProyecto = Integer.parseInt(proyectId);
        int rol = new UsuarioProyectoDAO().getRolUserProyect(user.getIdUsuario(),idProyecto);
        if(rol<=0){
            hsres.sendRedirect("ServletWorkspace");
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
            throws ServletException, IOException{
        
        Usuario user = getUsuarioSesion(hsreq, hsres);
        
        HttpSession sesion = hsreq.getSession();
        String proyectoid = (String) sesion.getAttribute("proyectoActual");
        int idProyecto = Integer.parseInt(proyectoid);
                
        
        
        String accion = hsreq.getParameter("accion");
        
        if("cambiarEstado".equals(accion)){
            int idTarea = Integer.parseInt(hsreq.getParameter("idTarea"));
            int nuevoEstado = Integer.parseInt(hsreq.getParameter("nuevoEstado"));
            
            int check = new TareaDAO().switchProgress(idTarea, nuevoEstado);
            if(check <= 0){
                 hsreq.setAttribute("MensajeError", "Error");
                 hsreq.getRequestDispatcher("ServletProyecto").forward(hsreq, hsres);
                 return;
            }
            hsres.sendRedirect("ServletTarea?idProyecto=" + idProyecto);
            return;
        }
        
        if("crear".equals(accion)){
            String nombre = hsreq.getParameter("txtNombre");
            String desc = hsreq.getParameter("txtDescripcion");
            LocalDate fechaLim = (!hsreq.getParameter("txtFechaLim").isEmpty() && hsreq.getParameter("txtFechaLim") != null) ? LocalDate.parse(hsreq.getParameter("txtFechaLim")) : null;          
            int prioridad = Integer.parseInt(hsreq.getParameter("txtPrioridad"));
            int etiqueta = Integer.parseInt(hsreq.getParameter("txtEtiqueta"));
            
            if(nombre.isEmpty() || desc.isEmpty()){
                 hsreq.setAttribute("MensajeError", "¡Llena todos los campos!");
                 hsreq.getRequestDispatcher("ServletProyecto").forward(hsreq, hsres);
                 return;
            }
            
            Tarea work = new Tarea(nombre, desc, LocalDate.now(), fechaLim, 1, prioridad, etiqueta, idProyecto, 3);
            new TareaDAO().insertWork(work);
            
            hsres.sendRedirect("ServletTarea?idProyecto=" + idProyecto);
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
