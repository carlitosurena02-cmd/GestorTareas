package modelo;

import java.io.Serializable;

public class Prioridad implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    int IdPrioridad;
    String Descripcion;

    public Prioridad() {
    }

    public Prioridad(int IdPrioridad, String Descripcion) {
        this.IdPrioridad = IdPrioridad;
        this.Descripcion = Descripcion;
    }

    public int getIdPrioridad() {
        return IdPrioridad;
    }

    public void setIdPrioridad(int IdPrioridad) {
        this.IdPrioridad = IdPrioridad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
       
}
