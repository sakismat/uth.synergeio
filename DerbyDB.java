package synergeio;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Date;


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
   
    
    
    public static String cutString(String str, int strlength)
    {
        if(str.length() > strlength)
            return str.substring(0,strlength).replace("'","");
        else
            return str.replace("'","");
    }
    
    private static String getDateTime()  
    {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh.mm.ss");  
        df.setTimeZone(TimeZone.getTimeZone("Europe/Athens"));  
        return df.format(new Date());  
    }  

}
