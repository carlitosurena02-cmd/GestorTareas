package controlador;

import modelo.Usuario;
import modelo.Etiqueta;

import datos.EtiquetaDAO;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@WebServlet("/ServletEtiqueta")

public class ServletEtiqueta extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest hsreq, HttpServletResponse hsres)
            throws ServletException, IOException{
        Usuario user = getUsuarioSesion(hsreq, hsres);
        HttpSession sesion = hsreq.getSession(); 
        String proyectId = (String) sesion.getAttribute("proyectoActual");
        int idProyecto = Integer.parseInt(proyectId);
        
        String nombreDesc = hsreq.getParameter("txtEtiqueta");
         if(nombreDesc == null || nombreDesc.isEmpty()){
             hsreq.setAttribute("MensajeError", "¡Llena todos los campos!");
             hsreq.getRequestDispatcher("ServletProyecto").forward(hsreq, hsres);
             return;
         } 
        Etiqueta lbl = new Etiqueta(nombreDesc, idProyecto, user.getIdUsuario(), 1);
        new EtiquetaDAO().insertLabel(lbl);
        
        hsres.sendRedirect("ServletTarea?idProyecto=" + idProyecto);
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
