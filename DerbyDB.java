package synergeio;

import java.sql.*;


/**
 *
 * @author matikas
 */
public class DerbyDB {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String CONNECTIONURL = "jdbc:derby:SynergeioDB;create=true";
    // jdbc Connection
    private static Connection conn = null;
    
    public void DerbyDB(){}
    
    public static Connection createConnection()
    {
        try {
            Class.forName(DRIVER);
            //System.out.println("Driver Loaded!");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("Class Not Found Exception: "+ e.toString());
        }
        
        try {
            conn = DriverManager.getConnection(CONNECTIONURL);
            //System.out.println("Connected!");
        } catch (SQLException e) {
            System.out.println("Exception: "+ e.toString());
        }
        
        return conn;
    }
    
    public static void shutdownConnection(Connection conn, Statement stmt)
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                //DriverManager.getConnection(connectionURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            System.out.println("SQL Exception: "+ sqlExcept.toString());
        }

    }
   
    

}
