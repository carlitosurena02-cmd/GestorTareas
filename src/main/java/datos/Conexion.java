package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
      
    private static String user = System.getenv("DB_USER");
    private static String pswd = System.getenv("DB_PASSWORD");
    private static String bd = System.getenv("DB_NAME");
    private static String server = System.getenv("DB_SERVER") + bd;
    private static String driver = "org.postgresql.Driver";
    

    private static BasicDataSource ds;

    public static DataSource getDataSource() {
        if (ds == null) {
            ds = new BasicDataSource();
            ds.setUrl(server);
            ds.setUsername(user);
            ds.setPassword(pswd);
            ds.setInitialSize(5);
            ds.setDriverClassName(driver);
        }
        return ds;
    }
    
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    public static void close(ResultSet rs) {
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement ps) {
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection conn) {
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
