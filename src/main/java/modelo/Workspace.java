package modelo;

import java.io.Serializable;

public class Workspace implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    int IdWorkspace;
    String Nombre;
    int Estado;

    public Workspace() {
    }

    public Workspace(int IdWorkspace, String Nombre, int Estado) {
        this.IdWorkspace = IdWorkspace;
        this.Nombre = Nombre;
        this.Estado = Estado;
    }

    public int getIdWorkspace() {
        return IdWorkspace;
    }

    public void setIdWorkspace(int IdWorkspace) {
        this.IdWorkspace = IdWorkspace;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
    
}
