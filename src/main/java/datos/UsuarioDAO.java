package datos;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import modelo.Usuario;

public class UsuarioDAO {
    
    private static final String insertSQL = "INSERT INTO Usuario(Nombre, ApellidoP, ApellidoM, Email, Username, Password_, DOB, FechaRegistro) VALUES (?,?,?,?,?,?,?,?)";
    private static final String selectloginSQL = "SELECT * " + 
                                                 "FROM usuario " + 
                                                 "WHERE Username = ? " + 
                                                 "AND Password_ = ? ";
    
    private static final String selectEmailSQL = "SELECT COUNT(*) FROM Usuario WHERE Email = ?";
    private static final String selectUsernameSQL = "SELECT COUNT(*) FROM Usuario WHERE Username = ?";
    
    private static final String searchByUsername = "SELECT IdUsuario FROM Usuario WHERE Username = ?";
    
    static public int userId(String username){
        Connection conn = null;
        PreparedStatement ps =  null;
        ResultSet rs = null;
        int userId = 0;
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(searchByUsername);
            
            ps.setString(1, username);
            
            rs = ps.executeQuery();
            
            if(rs.next())
                userId = rs.getInt("IdUsuario");
                        
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return userId;
    }
    
    
    static public boolean userExist(String username){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(selectUsernameSQL);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        
        return false;
    }
    
    static public boolean emailExist(String email){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(selectEmailSQL);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        
        return false;
    }
    
    public int insert(Usuario usuario){
        Connection conn =null;
        PreparedStatement ps = null;
        int reg = 0;
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(insertSQL);
            
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidoP());
            ps.setString(3, usuario.getApellidoM());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getUsername());
            ps.setString(6, usuario.getPassword_());
            ps.setDate(7, java.sql.Date.valueOf(usuario.getDOB()));
            ps.setDate(8,java.sql.Date.valueOf(LocalDate.now()));
            
            reg = ps.executeUpdate();
            
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(conn);
            Conexion.close(ps);
        }
        
        return reg;
    }
    
    public Usuario login(String user, String password){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;
        
        try{
            conn = Conexion.getConnection(); // creo la conexion
            ps = conn.prepareStatement(selectloginSQL); // Selecciono la consulta que se hara
            
            ps.setString(1, user);
            ps.setString(2, password); // lleno los parametros faltantes de la consulta
            
            rs = ps.executeQuery();// ejecuto la consulta ya completa
                        
            if (rs.next()){ // rs.next es necesario porque mueve el cursor a la fila encontrada
               
                //lleno un objeto usuario con los datos encontrados 
                int idUsuario = rs.getInt("IdUsuario");
                String nombre = rs.getString("Nombre");
                String apellidop = rs.getString("ApellidoP");
                String apellidom = rs.getString("ApellidoM");
                String email = rs.getString("Email");
                String username = rs.getString("Username");
                String pass = rs.getString("Password_");
                LocalDate DOB = rs.getDate("DOB").toLocalDate();
                LocalDate FR = rs.getDate("FechaRegistro").toLocalDate();
                int estado = rs.getInt("Estado");

                usuario = new Usuario(idUsuario, nombre, apellidop, apellidom, email, username, pass, DOB, FR, estado);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            if (rs != null) {
        Conexion.close(rs);
            }
            if (ps != null) {
                Conexion.close(ps);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }
        return usuario; // regreso el usuario encontrado
    }
        
}
