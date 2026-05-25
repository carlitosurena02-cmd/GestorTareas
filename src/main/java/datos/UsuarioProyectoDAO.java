package datos;

import modelo.ListMembersDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;


public class UsuarioProyectoDAO {
    private static final String addMemberSQL = "INSERT INTO UsuarioProyecto(Usuario, Proyecto, RolProyecto, Estado, FechaUnion) VALUES (?,?,?,?,?)";
    
    private static final String listMembersSQL = "SELECT Usuario.Username, Rol.Descripcion " +
                                                 "FROM UsuarioProyecto " +
                                                 "JOIN Usuario " + 
                                                 "ON UsuarioProyecto.Usuario = Usuario.IdUsuario " +
                                                 "JOIN Rol " +
                                                 "ON UsuarioProyecto.RolProyecto = Rol.IdRol " +
                                                 "WHERE UsuarioProyecto.Proyecto = ? " +
                                                 "AND UsuarioProyecto.Estado = 1 " +
                                                 "AND Usuario.Estado = 1";
    
    private static final String switchRolSQL = "UPDATE UsuarioProyecto " + 
                                               "SET RolProyecto = ? " +
                                               "WHERE Usuario = ? " +
                                               "AND Proyecto = ?";
    
    private static final String deleteMemberSQL = "UPDATE UsuarioProyecto " +
                                                  "SET Estado = 2 " +
                                                  "WHERE Usuario = ? " + 
                                                  "AND Proyecto = ?";
    
    public int addUser(int userId, int proyectId, int rol){
        Connection conn = null;
        PreparedStatement ps = null;
        int check = 0;
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(addMemberSQL);
            
            ps.setInt(1, userId);
            ps.setInt(2, proyectId);
            ps.setInt(3, rol);
            ps.setInt(4, 1);
            ps.setDate(5,java.sql.Date.valueOf(LocalDate.now()));
            
            check = ps.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return check;
    }
    
    public List<ListMembersDTO> listUsers(int proyectId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ListMembersDTO luw;
        List<ListMembersDTO> listUW = new ArrayList<>();
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(listMembersSQL);
            
            ps.setInt(1,proyectId);
                       
            rs = ps.executeQuery();
            
            while(rs.next()){
                String username = rs.getString("Username");
                String desc = rs.getString("Descripcion");
                luw = new ListMembersDTO(username,desc);
                listUW.add(luw);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return listUW;
    }
    
    public int switchRol(int rol, int userId, int proyectId){
        Connection conn = null;
        PreparedStatement ps = null;
        int check = 0;
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(switchRolSQL);
            
            ps.setInt(1,rol);
            ps.setInt(2,userId);
            ps.setInt(3, proyectId);  
            
            check = ps.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return check;
    }
    
    public int deleteUser(int userId, int proyectId){
        Connection conn = null;
        PreparedStatement ps = null;
        int check = 0;
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(deleteMemberSQL);
            
            ps.setInt(1, userId);
            ps.setInt(2,proyectId);
            
            check = ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return check;
    }
    
}
