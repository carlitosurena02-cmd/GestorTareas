package modelo;

import java.io.Serializable;

public class Workspace implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    int IdWorkspace;
    String Nombre;
    int Usuario;
    int Estado;

    public Workspace() {
    }

    public Workspace(int IdWorkspace, String Nombre, int Usuario, int Estado) {
        this.IdWorkspace = IdWorkspace;
        this.Nombre = Nombre;
        this.Usuario = Usuario;
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
