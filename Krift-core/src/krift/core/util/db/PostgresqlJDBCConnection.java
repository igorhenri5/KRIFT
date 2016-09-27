package krift.core.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresqlJDBCConnection implements JDBCConnectionFactory {

    private final static String dbDriver = "org.postgresql.Driver";
    private final static String dbURL = "jdbc:postgresql://localhost:5432/Krift";
    private final static String user = "postgres";
    private final static String pass = "123456";

    public PostgresqlJDBCConnection() {
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(dbDriver);
        return DriverManager.getConnection(dbURL, user, pass);
    }
    
    public static void main(String[] args) {
        try {
            JDBCConnectionFactory cf = new PostgresqlJDBCConnection();            
            cf.getConnection();
            ResultSet rs = cf.getConnection().prepareStatement("SELECT * FROM \"USUARIO\"").executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("TESTE - SELECT * DE USUARIO - TESTE");
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(",  ");
                    }
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostgresqlJDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
