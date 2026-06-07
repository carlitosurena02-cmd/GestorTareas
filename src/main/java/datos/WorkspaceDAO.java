package datos;

import modelo.Workspace;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkspaceDAO {
    private static final String insertWorkspaceSQL = "INSERT INTO Workspace(Nombre, Estado) VALUES (?,?)";
    private static final String insertUsuarioWorkspaceSQL = "INSERT INTO UsuarioWorkspace (Usuario, Workspace, RolWorkspace, Estado) VALUES (?,?,?,?)";
    
    private static final String listarSQL = "SELECT w.IdWorkspace, w.Nombre, w.Estado " +
                                            "FROM Workspace w " +
                                            "INNER JOIN UsuarioWorkspace uw "+                               
                                            "ON w.IdWorkspace = uw.Workspace " +
                                            "WHERE uw.Usuario = ? " +
                                            "AND w.Estado = 1";
    
    public static final String deleteWorkspaceSQL = "UPDATE Workspace " + 
                                               "SET Estado = 2 " +
                                               "WHERE IdWorkspace = ?";

    public int createWorkspace(int idUser, String nombre){
        Connection conn =  null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int check = 0;
        
        try{
            conn = Conexion.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(insertWorkspaceSQL,Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, nombre);
            ps.setInt(2, 1);
            
            if(ps.executeUpdate()== 1)
            {
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    int idWorkspace = rs.getInt(1);
                    ps = conn.prepareStatement(insertUsuarioWorkspaceSQL);
                    ps.setInt(1,idUser);
                    ps.setInt(2, idWorkspace);
                    ps.setInt(3,1);
                    ps.setInt(4,1);
                    
                    check = ps.executeUpdate();
                    if(check == 1)
                        conn.commit();
                    else
                        conn.rollback();
                }else{
                    conn.rollback();
                }
            }else{
                conn.rollback();
            }
                        
        }catch(SQLException ex){
            try{
                conn.rollback();
            }catch(SQLException e){
                e.printStackTrace(System.out);
            }
            ex.printStackTrace(System.out);            
        }finally{
            
            try{
                conn.setAutoCommit(true);
            }catch(SQLException ex){
                ex.printStackTrace(System.out);
            }
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return check;
    }
    
    public List<Workspace> listWorkspace(int idUser){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Workspace ws = null;
        List<Workspace> workspaces = new ArrayList<>();
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(listarSQL);
            
            ps.setInt(1, idUser);
            
            rs = ps.executeQuery();
                    
            while(rs.next()){
                int id = rs.getInt("IdWorkspace");
                String nombre = rs.getString("Nombre");
                int estado = rs.getInt("Estado");
                
                ws = new Workspace(id,nombre,estado);
                workspaces.add(ws);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return workspaces;
    }
    
    public int deleteWorkspaces(int workspaceId){
        Connection conn = null;
        PreparedStatement ps = null;
        int check = 0;
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(deleteWorkspaceSQL);
            
            ps.setInt(1, workspaceId);
            
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


