
package modelo;

import java.io.Serializable;

public class UsuarioTarea implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    int Usuario;
    int Tarea;
    int FechaAsignacion;

    public UsuarioTarea() {
    }

    public UsuarioTarea(int Usuario, int Tarea, int FechaAsignacion) {
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

    public int getFechaAsignacion() {
        return FechaAsignacion;
    }

    public void setFechaAsignacion(int FechaAsignacion) {
        this.FechaAsignacion = FechaAsignacion;
    }
        
}
