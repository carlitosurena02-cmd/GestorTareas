package datos;

import modelo.Proyecto;
import modelo.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class ProyectoDAO {
    
    private static final String insertProyectsSQL = "INSERT INTO Proyecto(Nombre, Descripcion, FechaInicio, FechaFin, Estado, Workspace) " +
                                                    "VALUES (?,?,?,?,?,?)";
    private static final String insertUsuarioProyectoSQL = "INSERT INTO UsuarioProyecto(Usuario, Proyecto, RolProyecto, Estado, FechaUnion) " +
                                                           "VALUES (?,?,?,?,?)";
    
    private static final String listProyectsSQL = "SELECT Proyecto.IdProyecto, Proyecto.Nombre, Proyecto.Descripcion, Proyecto.FechaInicio, Proyecto.FechaFin, Proyecto.Estado, Proyecto.Workspace " +
                                                  "FROM Proyecto " +
                                                  "JOIN UsuarioProyecto " +
                                                  "ON Proyecto.IdProyecto = UsuarioProyecto.Proyecto " +
                                                  "WHERE UsuarioProyecto.Usuario = ? " +
                                                  "AND Proyecto.Estado = 1 " + 
                                                  "AND Proyecto.Workspace = ?";
    
    private static final String listProyectsAdminSQL = "SELECT * " + 
                                                       "FROM Proyecto " +
                                                       "WHERE Estado = 1 " +
                                                       "AND Workspace = ?";
    
    public int createProyect(Proyecto p, int userId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int check = 0;
        
        try{
            conn = Conexion.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(insertProyectsSQL, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDate(3, java.sql.Date.valueOf(p.getFechaInicio()));
            ps.setDate(4,p.getFechaFin() != null ? java.sql.Date.valueOf(p.getFechaFin()): null);
            ps.setInt(5, 1);
            ps.setInt(6,p.getWorkspace());
            
            if(ps.executeUpdate() == 1){
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    int proyectId = rs.getInt(1);
                    ps = conn.prepareStatement(insertUsuarioProyectoSQL);
                    
                    ps.setInt(1, userId);
                    ps.setInt(2, proyectId);
                    ps.setInt(3, 1);
                    ps.setInt(4, 1);
                    ps.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
                    
                    check = ps.executeUpdate();
                    
                    if(check == 1)
                        conn.commit();
                    else
                        conn.rollback();
                }else
                    conn.rollback();
            }else
                conn.rollback();
            
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
    
    public List<Proyecto> listProyects(int userRol, int workspaceId, int userId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proyecto p;
        List<Proyecto> listP = new ArrayList<>();
        
        try{
            conn = Conexion.getConnection();
            if(userRol == 1){
                ps = conn.prepareStatement(listProyectsAdminSQL);
                ps.setInt(1,workspaceId);
                rs = ps.executeQuery();
            }else{
                ps = conn.prepareStatement(listProyectsSQL);
                ps.setInt(1, userId);
                ps.setInt(2, workspaceId);
                rs = ps.executeQuery();
            }
            
            while(rs.next()){
                    int id = rs.getInt("IdProyecto");
                    String nombre = rs.getString("Nombre");
                    String desc = rs.getString("Descripcion");
                    LocalDate fi = rs.getDate("FechaInicio").toLocalDate();
                    LocalDate ff = rs.getDate("FechaFin") != null ? rs.getDate("FechaFin").toLocalDate() : null;
                    int status = rs.getInt("Estado");
                    int workspace = rs.getInt("Workspace");
                    p = new Proyecto(id,nombre,desc,fi,ff,status,workspace);
                    listP.add(p);
                }
                       
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
       
        return listP;
    }
    
}
