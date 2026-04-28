
package modelo;

import java.io.Serializable;

public class Proyecto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    int IdProyecto;
    String Nombre;
    String Descripcion;
    String FechaInicio;
    String FechaFin;
    int Estado;

    public Proyecto() {
    }

    public Proyecto(int IdProyecto, String Nombre, String Descripcion, String FechaInicio, String FechaFin, int Estado) {
        this.IdProyecto = IdProyecto;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.FechaInicio = FechaInicio;
        this.FechaFin = FechaFin;
        this.Estado = Estado;
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

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(String FechaFin) {
        this.FechaFin = FechaFin;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
    
}
