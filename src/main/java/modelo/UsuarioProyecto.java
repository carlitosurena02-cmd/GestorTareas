
package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class UsuarioProyecto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    int Usuario;
    int Proyecto;
    int RolProyecto;
    int Estado;
    LocalDate FechaUnion;

    public UsuarioProyecto() {
    }

    public UsuarioProyecto(int Usuario, int Proyecto, int RolProyecto, int Estado, LocalDate FechaUnion) {
        this.Usuario = Usuario;
        this.Proyecto = Proyecto;
        this.RolProyecto = RolProyecto;
        this.Estado = Estado;
        this.FechaUnion = FechaUnion;
    }

    public int getUsuario() {
        return Usuario;
    }

    public void setUsuario(int Usuario) {
        this.Usuario = Usuario;
    }

    public int getProyecto() {
        return Proyecto;
    }

    public void setProyecto(int Proyecto) {
        this.Proyecto = Proyecto;
    }

    public int getRolProyecto() {
        return RolProyecto;
    }

    public void setRolProyecto(int RolProyecto) {
        this.RolProyecto = RolProyecto;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    public LocalDate getFechaUnion() {
        return FechaUnion;
    }

    public void setFechaUnion(LocalDate FechaUnion) {
        this.FechaUnion = FechaUnion;
    }
        
}
