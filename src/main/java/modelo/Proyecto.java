
package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Proyecto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    int IdProyecto;
    String Nombre;
    String Descripcion;
    LocalDate FechaInicio;
    LocalDate FechaFin;
    int Estado;
    int Workspace;

    public Proyecto() {
    }

    public Proyecto(int IdProyecto, String Nombre, String Descripcion, LocalDate FechaInicio, LocalDate FechaFin, int Estado, int Workspace) {
        this.IdProyecto = IdProyecto;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.FechaInicio = FechaInicio;
        this.FechaFin = FechaFin;
        this.Estado = Estado;
        this.Workspace = Workspace;
    }

    public int getIdProyecto() {
        return IdProyecto;
    }

    public void setIdProyecto(int IdProyecto) {
        this.IdProyecto = IdProyecto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public LocalDate getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(LocalDate FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public LocalDate getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(LocalDate FechaFin) {
        this.FechaFin = FechaFin;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    public int getWorkspace() {
        return Workspace;
    }

    public void setWorkspace(int Workspace) {
        this.Workspace = Workspace;
    }
    
}
