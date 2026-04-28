package modelo;

import java.io.Serializable;

public class Rol implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    int IdRol;
    String Permisos;
    String Descripcion;

    public Rol() {
    }
    
    public Rol(int IdRol, String Permisos, String Descripcion) {
        this.IdRol = IdRol;
        this.Permisos = Permisos;
        this.Descripcion = Descripcion;
    }
    
    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int IdRol) {
        this.IdRol = IdRol;
    }

    public String getPermisos() {
        return Permisos;
    }

    public void setPermisos(String Permisos) {
        this.Permisos = Permisos;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
}
