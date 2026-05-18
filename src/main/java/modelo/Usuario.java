
package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    int IdUsuario;
    String Nombre;
    String ApellidoP;
    String ApellidoM;
    String Email;
    String Username;
    String Password_;
    LocalDate DOB;
    LocalDate FechaRegistro;
    int Estado;

    public Usuario() {
    }

    public Usuario(int IdUsuario, String Nombre, String ApellidoP, String ApellidoM, String Email, String Username, String Password_, LocalDate DOB, LocalDate FechaRegistro, int Estado) {
        this.IdUsuario = IdUsuario;
        this.Nombre = Nombre;
        this.ApellidoP = ApellidoP;
        this.ApellidoM = ApellidoM;
        this.Email = Email;
        this.Username = Username;
        this.Password_ = Password_;
        this.DOB = DOB;
        this.FechaRegistro = FechaRegistro;
        this.Estado = Estado;
    }

    public Usuario(String Nombre, String ApellidoP, String ApellidoM, String Email, String Username, String Password_, LocalDate DOB) {
        this.Nombre = Nombre;
        this.ApellidoP = ApellidoP;
        this.ApellidoM = ApellidoM;
        this.Email = Email;
        this.Username = Username;
        this.Password_ = Password_;
        this.DOB = DOB;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoP() {
        return ApellidoP;
    }

    public void setApellidoP(String ApellidoP) {
        this.ApellidoP = ApellidoP;
    }

    public String getApellidoM() {
        return ApellidoM;
    }

    public void setApellidoM(String ApellidoM) {
        this.ApellidoM = ApellidoM;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword_() {
        return Password_;
    }

    public void setPassword_(String Password_) {
        this.Password_ = Password_;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public LocalDate getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(LocalDate FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
       
}
