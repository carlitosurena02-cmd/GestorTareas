
package modelo;

import java.io.Serializable;

public class Etiqueta implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    int IdEtiqueta;
    String Descripcion;

    public Etiqueta() {
    }

    public Etiqueta(int IdEtiqueta, String Descripcion) {
        this.IdEtiqueta = IdEtiqueta;
        this.Descripcion = Descripcion;
    }

    public int getIdEtiqueta() {
        return IdEtiqueta;
    }

    public void setIdEtiqueta(int IdEtiqueta) {
        this.IdEtiqueta = IdEtiqueta;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
        
}
