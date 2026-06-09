package datos;

import modelo.Etiqueta;

import java.sql.*;
import java.util.*;

public class EtiquetaDAO {
    
    private static final String insertLabelSQL = "INSERT INTO Etiqueta(Descripcion, Proyecto, Usuario, Estado) "
                                               + "VALUES (?,?,?,?)";
    
    private static final String listLabelsSQL = "SELECT IdEtiqueta, Descripcion, Proyecto, Usuario, Estado "
                                              + "FROM Etiqueta "
                                              + "WHERE Proyecto = ? "
                                              + "AND Estado = 1";
    
    private static final String deleteLabelSQL = "UPDATE Etiqueta " +
                                                 "SET Estado = 2 " +
                                                 "WHERE IdEtiqueta = ?";
    
    public int insertLabel(Etiqueta lbl){
        Connection conn = null;
        PreparedStatement ps = null;
        int check = 0;
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(insertLabelSQL);
            
            ps.setString(1, lbl.getDescripcion());
            ps.setInt(2, lbl.getProyecto());
            ps.setInt(3, lbl.getUsuario());
            ps.setInt(4,lbl.getEstado());
            
            check = ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return check;
    } 
    
    public List<Etiqueta> listLabels(int proyectId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Etiqueta lbl;
        List<Etiqueta> labels = new ArrayList<>();
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(listLabelsSQL);
            
            ps.setInt(1, proyectId);
            
            rs = ps.executeQuery();
            
                while(rs.next()){
                    int id = rs.getInt("IdEtiqueta");
                    String desc = rs.getString("Descripcion");
                    int proyect = rs.getInt("Proyecto");
                    int user = rs.getInt("Usuario");
                    int status = rs.getInt("Estado");

                    lbl = new Etiqueta(id,desc,proyect,user,status);

                    labels.add(lbl);
                }
                
            }catch(SQLException ex){
                ex.printStackTrace(System.out);
            }finally{
                Conexion.close(rs);
                Conexion.close(ps);
                Conexion.close(conn);
            }
            return labels;
        }
    
    public int deleteLabel(int labelId){
        Connection conn = null;
        PreparedStatement ps = null;
        int check = 0;
        
        try{
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(deleteLabelSQL);
            
            ps.setInt(1, labelId);
            
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
