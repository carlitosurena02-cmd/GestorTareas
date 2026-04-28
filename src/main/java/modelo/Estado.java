package modelo;

import java.io.Serializable;

public class Estado implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    int IdEstado;
    String Descripcion;

    public Estado() {
    }

    public Estado(int IdEstado, String Descripcion) {
        this.IdEstado = IdEstado;
        this.Descripcion = Descripcion;
    }

    public int getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(int IdEstado) {
        this.IdEstado = IdEstado;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
}
