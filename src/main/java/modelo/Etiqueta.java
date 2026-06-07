
package modelo;

import java.io.Serializable;

public class Etiqueta implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    int IdEtiqueta;
    String Descripcion;
    int Proyecto;
    int Usuario;
    int Estado;

    public Etiqueta() {
    }

    public Etiqueta(int IdEtiqueta, String Descripcion, int Proyecto, int Usuario, int Estado) {
        this.IdEtiqueta = IdEtiqueta;
        this.Descripcion = Descripcion;
        this.Proyecto = Proyecto;
        this.Usuario = Usuario;
        this.Estado = Estado;
    }  

    public Etiqueta(String Descripcion, int Proyecto, int Usuario, int Estado) {
        this.Descripcion = Descripcion;
        this.Proyecto = Proyecto;
        this.Usuario = Usuario;
        this.Estado = Estado;
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
    
    public int getProyecto() {
        return Proyecto;
    }

    public void setProyecto(int Proyecto) {
        this.Proyecto = Proyecto;
    }

    public int getUsuario() {
        return Usuario;
    }

    public void setUsuario(int Usuario) {
        this.Usuario = Usuario;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
        
}
