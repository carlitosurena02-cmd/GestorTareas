package modelo;

import java.io.Serializable;

public class UsuarioWorkspace implements Serializable{
    
    int Usuario;
    int Workspace;
    int RolWorkspace;
    int Estado;

    public UsuarioWorkspace() {
    }

    public UsuarioWorkspace(int Usuario, int Workspace, int RolWorkspace, int Estado) {
        this.Usuario = Usuario;
        this.Workspace = Workspace;
        this.RolWorkspace = RolWorkspace;
        this.Estado = Estado;
    }

    public int getUsuario() {
        return Usuario;
    }

    public void setUsuario(int Usuario) {
        this.Usuario = Usuario;
    }

    public int getWorkspace() {
        return Workspace;
    }

    public void setWorkspace(int Workspace) {
        this.Workspace = Workspace;
    }

    public int getRolWorkspace() {
        return RolWorkspace;
    }

    public void setRolWorkspace(int RolWorkspace) {
        this.RolWorkspace = RolWorkspace;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
       
}
