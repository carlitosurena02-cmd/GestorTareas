
package modelo;

import java.io.Serializable;

public class Comentario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    int IdComentario;
    String Contenido;
    int Usuario;
    int Tarea;
    int Estado;

    public Comentario() {
    }

    public Comentario(int IdComentario, String Contenido, int Usuario, int Tarea, int Estado) {
        this.IdComentario = IdComentario;
        this.Contenido = Contenido;
        this.Usuario = Usuario;
        this.Tarea = Tarea;
        this.Estado = Estado;
    }

    public int getIdComentario() {
        return IdComentario;
    }

    public void setIdComentario(int IdComentario) {
        this.IdComentario = IdComentario;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String Contenido) {
        this.Contenido = Contenido;
    }

    public int getUsuario() {
        return Usuario;
    }

    public void setUsuario(int Usuario) {
        this.Usuario = Usuario;
    }

    public int getTarea() {
        return Tarea;
    }

    public void setTarea(int Tarea) {
        this.Tarea = Tarea;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
        
}
