package modelo;

public class ListMembersDTO{
    
    String Username;
    String Descripcion;

    public ListMembersDTO() {
    }

    public ListMembersDTO(String Username, String Descripcion) {
        this.Username = Username;
        this.Descripcion = Descripcion;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
}
