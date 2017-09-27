package synergeio;

import java.sql.*;

/**
 *
 * @author matikas
 */
public class ServiceList {
    private static String tableName = "SERVICELIST";
    
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    
    private Integer id;
    private String service;
    private Double price;
    private String notes;
    
    public ServiceList(Integer id, String service, Double price, String notes){
        this.id=id;
        this.service=DerbyDB.cutString(service,255);
        this.price=price;
        this.notes=DerbyDB.cutString(notes,255);
    }
    
    public ServiceList(String service, Double price, String notes){
        this.service=DerbyDB.cutString(service,255);
        this.price=price;
        this.notes=DerbyDB.cutString(notes,255);
        
        conn = DerbyDB.createConnection();
        
        try {
            PreparedStatement psInsert = conn.prepareStatement("insert into "+ tableName +" (service, price, notes) values ('"+this.service+"',"+this.price+",'"+this.notes+"')");
            psInsert.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
    }
    
    public ServiceList() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public ServiceList (String id)
    {
        conn = DerbyDB.createConnection();
        
        try {
            stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("select * from "+ tableName +" where id = " + id);
            
            boolean next = rs.next();
            
            this.id=rs.getInt("id");
            this.service=rs.getString("service");
            this.price=rs.getDouble("price");
            this.notes=rs.getString("notes");
            
            rs.close();
        } catch (Exception e) {
            System.out.println("Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
        
    }
    
    public void setid(int id){
        this.id=id;
    }
    public int getid(){
        return id;
    }
    
    public void setservice(String service){
        this.service=DerbyDB.cutString(service,255);
    }
    public String getservice(){
        return service;
    }
    
    public void setprice(double price){
        this.price=price;
    }
    public double getprice(){
        return price;
    }
    
    public void setnotes(String notes){
        this.notes=DerbyDB.cutString(notes,255);
    }
    public String getnotes(){
        return notes;
    }
    
    
	public static ServiceList[] getServiceList()
    {
        System.out.println("START getServiceList");
        ServiceList[] servicelist = null;
        int counter = 0;
        
        conn = DerbyDB.createConnection();
        
        try {
            stmt = conn.createStatement();
            
            ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM "+ tableName +"");
            r.next();
            int count = r.getInt("rowcount") ;
            r.close() ;
            servicelist = new ServiceList[count];
            
            ResultSet rs = stmt.executeQuery("select * from "+ tableName +" ORDER BY service");
            int num = 0;
            
            while (rs.next()) {
                ServiceList service = new ServiceList(rs.getInt("id"), rs.getString("service"), rs.getDouble("price"), rs.getString("notes"));
                servicelist[counter] = service;
                counter++;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("getServiceList Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
        System.out.println("END getServiceList");
        return servicelist;
    }
	
    public static Boolean deleteServiceList (String id)
    {
        conn = DerbyDB.createConnection();
        
        boolean deleted;
        
        try {
            PreparedStatement psInsert = conn.prepareStatement("DELETE FROM "+ tableName +" where id = " + id);
            psInsert.executeUpdate();
            deleted = true;
        } catch (SQLException e) {
            System.out.println("Exception: "+ e.toString());
            deleted = false;
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
        
        return deleted;
    }
    
    
    public boolean updateServiceList(){
        boolean result;
        
        conn = DerbyDB.createConnection();
        
        try {
            PreparedStatement psInsert = conn.prepareStatement("UPDATE "+ tableName +" SET "
                            + "service = '"+service+"', "
                            + "price = "+price+", "
                            + "notes = '"+notes+"' "
                            + "WHERE id = "+id );
            psInsert.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.err.println("Exception: "+ e.toString());
            result = false;
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
        
        return result;
    }
    
    
    public static void createTableIfNotExists(){
        
        conn = DerbyDB.createConnection();
        
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, tableName, null);
            if(!rs.next())
            {
                System.out.println("Table " +tableName+ " does not exist");
                createTable();
            }
            
        } catch (SQLException e) {
            System.err.println("Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
    }
    
    private static void createTable(){
        conn = DerbyDB.createConnection();
        System.out.println("Create Table: " + tableName);
        //createTableService_______________________________________
        String createString = "CREATE table " + tableName + " ("
                + "id          INTEGER NOT NULL "
                + "PRIMARY KEY GENERATED ALWAYS AS IDENTITY "
                + "(START WITH 1, INCREMENT BY 1),"
                + "service     VARCHAR(255) NOT NULL, "
                + "price     	DOUBLE NOT NULL DEFAULT 0,"
                + "notes       VARCHAR(255) )";
        
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
        } catch (SQLException sqlExcept){
            System.err.println("createTable SQL Exception: "+ sqlExcept.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
    }
}
