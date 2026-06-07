package datos;

import modelo.ListMembersDTO;

import java.sql.*;
import java.util.*;



public class UsuarioWorkspaceDAO {
    private static final String addMember= "INSERT INTO UsuarioWorkspace(Usuario, Workspace, RolWorkspace, Estado) " +
                                           "VALUES (?,?,?,?)";
    
    private static final String listMembers = "SELECT Usuario.Username, Rol.Descripcion " +
                                              "FROM UsuarioWorkspace " +
                                              "JOIN Usuario " +
                                              "ON UsuarioWorkspace.Usuario = Usuario.IdUsuario " +
                                              "JOIN Rol " +
                                              "ON UsuarioWorkspace.RolWorkspace = Rol.IdRol " +
                                              "WHERE UsuarioWorkspace.Workspace = ?";     
    
    private static final String switchRol= "UPDATE UsuarioWorkspace " +
                                           "SET RolWorkspace = ? " +
                                           "WHERE Usuario = ? " +
                                           "AND Workspace = ?";
    
    private static final String deleteMember = "UPDATE UsuarioWorkspace " +
                                               "SET Estado = 2 " +
                                               "WHERE Usuario = ? "+
                                               "AND Workspace = ?";
    
    private static final String getRolSQL = "SELECT RolWorkspace " +
                                         "FROM UsuarioWorkspace " +
                                         "WHERE Usuario = ? " +
                                         "AND Workspace = ?";
    
    
    public int getRol(int userId, int workspaceId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(getRolSQL);
            
            ps.setInt(1, userId);
            ps.setInt(2, workspaceId);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                id = rs.getInt("RolWorkspace");
            }
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        
        return id;
    }
    
    
    public int addMember(int userId, int workspaceId, int rol){
        Connection conn = null;
        PreparedStatement ps = null;
        int check = 0;
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(addMember);
            
            ps.setInt(1, userId);
            ps.setInt(2, workspaceId);
            ps.setInt(3, rol);
            ps.setInt(4, 1);
            
            check = ps.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return check;
    }
    
    public List<ListMembersDTO> listUsers(int workspaceId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ListMembersDTO luw;
        List<ListMembersDTO> listUW = new ArrayList<>();
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(listMembers);
            
            ps.setInt(1,workspaceId);
           
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
    
    public int switchRol(int rol, int userId, int workspaceId){
        Connection conn = null;
        PreparedStatement ps = null;
        int check = 0;
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(switchRol);
            
            ps.setInt(1,rol);
            ps.setInt(2,userId);
            ps.setInt(3, workspaceId);  
            
            check = ps.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return check;
    }
    
    public int deleteUser(int userId, int workspaceId){
        Connection conn = null;
        PreparedStatement ps = null;
        int check = 0;
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(deleteMember);
            
            ps.setInt(1, userId);
            ps.setInt(2,workspaceId);
            
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
