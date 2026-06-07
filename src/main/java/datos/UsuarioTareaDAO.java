package datos;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;


public class UsuarioTareaDAO {
    
    private static final String addUserToWorkSQL = "INSERT INTO UsuarioTarea(Usuario, Tarea, Estado, FechaAsignacion) " +
                                                   "VALUES (?,?,?,?)";
    
    private static final String listUsersWorkSQL = "SELECT Usuario.Username " + 
                                                   "FROM UsuarioTarea " + 
                                                   "JOIN Usuario " +
                                                   "ON UsuarioTarea.Usuario = Usuario.IdUsuario " +
                                                   "WHERE UsuarioTarea.Tarea = ? " +
                                                   "AND Usuario.Estado = 1 " +
                                                   "AND UsuarioTarea.Estado = 1";
    
    private static final String unassingUserSQL = "UPDATE UsuarioTarea " +
                                                  "SET Estado = 2 " +
                                                  "WHERE Usuario = ? " +
                                                  "AND Tarea = ?";
    
    public int addUsertoWork(int userId, int workId){
        Connection conn = null;
        PreparedStatement ps = null;
        int check = 0;
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(addUserToWorkSQL);
            
            ps.setInt(1, userId);
            ps.setInt(2,workId);
            ps.setInt(3, 1);
            ps.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            
            check = ps.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return check;
    }
    
    public List<String> listUsersWork(int workId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String username;
        List<String> users = new ArrayList<>();
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(listUsersWorkSQL);
            
            ps.setInt(1,workId);
            
            rs = ps.executeQuery();
            while(rs.next()){
                username = rs.getString("Username");
                users.add(username);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return users;
    }
    
    public int unassingUser(int userId, int workId){
        Connection conn = null;
        PreparedStatement ps = null;
        int check = 0;
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(unassingUserSQL);
            
            ps.setInt(1,userId);
            ps.setInt(2, workId);
            
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
