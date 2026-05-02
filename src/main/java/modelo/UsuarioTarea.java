
package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class UsuarioTarea implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    int Usuario;
    int Tarea;
    LocalDate FechaAsignacion;

    public UsuarioTarea() {
    }

    public UsuarioTarea(int Usuario, int Tarea, LocalDate FechaAsignacion) {
        this.Usuario = Usuario;
        this.Tarea = Tarea;
        this.FechaAsignacion = FechaAsignacion;
    }

    public int getUsuario() {
        return Usuario;
    }

    public void setUsuario(int Usuario) {
        this.Usuario = Usuario;
    }

    public int getTarea() {
        return Tarea;
    }

    public void setTarea(int Tarea) {
        this.Tarea = Tarea;
    }

    public LocalDate getFechaAsignacion() {
        return FechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate FechaAsignacion) {
        this.FechaAsignacion = FechaAsignacion;
    }
        
}
