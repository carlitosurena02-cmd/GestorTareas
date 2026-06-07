package datos;

import modelo.Tarea;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class TareaDAO {
    
    private static final String insertWorkSQL = "INSERT INTO Tarea(Nombre, Descripcion, FechaInicio, FechaLim, Estado, Prioridad, Etiqueta, Proyecto) "
                                              + "VALUES (?,?,?,?,?,?,?,?)";
    
    private static final String listMembersSQL = "SELECT Tarea.IdTarea, Tarea.Nombre, Tarea.Descripcion, Tarea.FechaInicio, Tarea.FechaLim, Tarea.Estado, Tarea.Prioridad, Tarea.Etiqueta, Tarea.Proyecto, Tarea.Progreso" +
                                                  "FROM Tarea " +
                                                  "JOIN UsuarioTarea " +
                                                  "ON Tarea.IdTarea = UsuarioTarea.Tarea " +
                                                  "WHERE UsuarioTarea.Usuario = ? " +
                                                  "AND Tarea.Estado = 1 " + 
                                                  "AND Tarea.Proyecto = ?";
    
    private static final String listMembersAdminSQL = "SELECT * " + 
                                                       "FROM Tarea " +
                                                       "WHERE Estado = 1 " +
                                                       "AND Proyecto = ?";
    
    private static final String switchProgressSQL = "UPDATE Tarea " +
                                                   "SET Progreso = ? " +
                                                   "WHERE IdTarea = ?";
    
    private static final String deleteWorkSQL = "UPDATE Tarea " + 
                                               "SET Estado = 2 " +
                                               "WHERE IdTarea = ?";
    
    public int insertWork(Tarea tarea){
        Connection conn = null;
        PreparedStatement ps = null;
        int check = 0;
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(insertWorkSQL);
            
            ps.setString(1,tarea.getNombre());
            ps.setString(2, tarea.getDescripcion());
            ps.setDate(3, java.sql.Date.valueOf(tarea.getFechaInicio()));
            ps.setDate(4, java.sql.Date.valueOf(tarea.getFechaLim()));
            ps.setInt(5, tarea.getEstado());
            ps.setInt(6,tarea.getPrioridad());
            ps.setInt(7, tarea.getEtiqueta());
            ps.setInt(8,tarea.getProyecto());
            ps.setInt(9, tarea.getProgreso());
            
            check = ps.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return check;
    }
    
    public List<Tarea> listWorks(int userId, int proyectId, int userRol){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Tarea work ;
        List<Tarea> works = new ArrayList<>();
        
        try{
            conn = Conexion.getConnection();
            
            if(userRol == 1){
               ps = conn.prepareStatement(listMembersAdminSQL);
               
               ps.setInt(1, proyectId);
            }else{
               ps = conn.prepareStatement(listMembersSQL);
               
               ps.setInt(1, userId);
               ps.setInt(2, proyectId);
            }
            
            rs = ps.executeQuery();
            while(rs.next()){
                int IdTarea = rs.getInt("IdTarea");
                String Nombre = rs.getString("Nombre");
                String Descripcion = rs.getString("Descripcion");
                LocalDate FechaInicio = rs.getDate("FechaInicio").toLocalDate();
                LocalDate FechaLim = rs.getDate("FechaLim").toLocalDate();
                int Estado = rs.getInt("Estado");
                int Prioridad = rs.getInt("Prioridad");
                int Etiqueta = rs.getInt("Etiqueta");
                int Proyecto = rs.getInt("Proyecto");
                int Progreso = rs.getInt("Progreso");
                
                work = new Tarea(IdTarea, Nombre, Descripcion, FechaInicio, FechaLim, Estado, Prioridad, Etiqueta, Proyecto, Progreso);
                works.add(work);
                
                }
            
            }catch(SQLException ex){
                ex.printStackTrace(System.out);
            }finally{
                Conexion.close(rs);
                Conexion.close(ps);
                Conexion.close(conn);
            }
        return works;
    }
    
    public int switchProgress(int workId, int progress){
        Connection conn = null;
        PreparedStatement ps = null;
        int check = -1;
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(switchProgressSQL);
            
            ps.setInt(1,progress);
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
    
    public int deleteWorks(int workId){
        Connection conn = null;
        PreparedStatement ps = null;
        int check = 0;
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(deleteWorkSQL);
            
            ps.setInt(1, workId);
            
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