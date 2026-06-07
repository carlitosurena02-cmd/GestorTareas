
package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class UsuarioTarea implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    int Usuario;
    int Tarea;
    int Estado;
    LocalDate FechaAsignacion;

    public UsuarioTarea() {
    }

    public UsuarioTarea(int Usuario, int Tarea, int Estado, LocalDate FechaAsignacion) {
        this.Usuario = Usuario;
        this.Tarea = Tarea;
        this.Estado = Estado;
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

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
    
    
    public LocalDate getFechaAsignacion() {
        return FechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate FechaAsignacion) {
        this.FechaAsignacion = FechaAsignacion;
    }
        
}
