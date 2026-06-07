
package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Tarea implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    int IdTarea;
    String Nombre;
    String Descripcion;
    LocalDate FechaInicio;
    LocalDate FechaLim;
    int Estado;
    int Prioridad;
    int Etiqueta;
    int Proyecto;
    int Progreso;

    public Tarea() {
    }

    public Tarea(int IdTarea, String Nombre, String Descripcion, LocalDate FechaInicio, LocalDate FechaLim, int Estado, int Prioridad, int Etiqueta, int Proyecto, int Progreso) {
        this.IdTarea = IdTarea;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.FechaInicio = FechaInicio;
        this.FechaLim = FechaLim;
        this.Estado = Estado;
        this.Prioridad = Prioridad;
        this.Etiqueta = Etiqueta;
        this.Proyecto = Proyecto;
        this.Progreso = Progreso;
    }

    public Tarea(String Nombre, String Descripcion, LocalDate FechaInicio, LocalDate FechaLim, int Estado, int Prioridad, int Etiqueta, int Proyecto, int Progreso) {
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.FechaInicio = FechaInicio;
        this.FechaLim = FechaLim;
        this.Estado = Estado;
        this.Prioridad = Prioridad;
        this.Etiqueta = Etiqueta;
        this.Proyecto = Proyecto;
        this.Progreso = Progreso;
    }
    

    public int getIdTarea() {
        return IdTarea;
    }

    public void setIdTarea(int IdTarea) {
        this.IdTarea = IdTarea;
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

    public LocalDate getFechaLim() {
        return FechaLim;
    }

    public void setFechaLim(LocalDate FechaLim) {
        this.FechaLim = FechaLim;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    public int getPrioridad() {
        return Prioridad;
    }

    public void setPrioridad(int Prioridad) {
        this.Prioridad = Prioridad;
    }

    public int getEtiqueta() {
        return Etiqueta;
    }

    public void setEtiqueta(int Etiqueta) {
        this.Etiqueta = Etiqueta;
    }

    public int getProyecto() {
        return Proyecto;
    }

    public void setProyecto(int Proyecto) {
        this.Proyecto = Proyecto;
    }

    public int getProgreso() {
        return Progreso;
    }

    public void setProgreso(int Progreso) {
        this.Progreso = Progreso;
    }
       
}
