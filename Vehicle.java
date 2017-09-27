/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package synergeio;

import java.sql.*;

/**
 *
 * @author matikas
 */
public class Vehicle {
    private static String tableName = "VEHICLE";
    
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    
    private Integer id;
    private Integer customer_id;
    private String brand;
    private String model;
    private String platenumber;
    private String framenumber;
    private String enginenumber;
    private String color;
    private String notes;
					
    public Vehicle(Integer id, Integer customer_id, String brand, String model, String platenumber,String framenumber, String enginenumber,String color, String notes){
        this.id=id;
        this.customer_id=customer_id;
        this.brand=DerbyDB.cutString(brand,150);
        this.model=DerbyDB.cutString(model,150);
        this.platenumber=DerbyDB.cutString(platenumber,30);
        this.framenumber=DerbyDB.cutString(framenumber,30);
        this.enginenumber=DerbyDB.cutString(enginenumber,30);
        this.color=DerbyDB.cutString(color,30);
        this.notes=DerbyDB.cutString(notes,255);
    }
    
    public Vehicle(Integer customer_id, String brand, String model, String platenumber,String framenumber, String enginenumber,String color, String notes){
        this.customer_id=customer_id;
        this.brand=DerbyDB.cutString(brand,150);
        this.model=DerbyDB.cutString(model,150);
        this.platenumber=DerbyDB.cutString(platenumber,30);
        this.framenumber=DerbyDB.cutString(framenumber,30);
        this.enginenumber=DerbyDB.cutString(enginenumber,30);
        this.color=DerbyDB.cutString(color,30);
        this.notes=DerbyDB.cutString(notes,255);
        
        createTableIfNotExists();
        
        conn = DerbyDB.createConnection();
        
        try {
            PreparedStatement psInsert = conn.prepareStatement("insert into "+ tableName +
                    " (customer_id, brand, model, platenumber, framenumber, enginenumber, color, notes)"
                    + " values "
                    + "("+this.customer_id+",'"+this.brand+"','"+this.model+"','"+this.platenumber+"','"+this.framenumber+"','"+this.enginenumber+"','"+this.color+"','"+this.notes+"')"
                    + "");
            psInsert.executeUpdate();
        } catch (Exception e) {
            System.err.println("Insert "+ tableName +" SQL Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
    }
    
    public Vehicle() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public Vehicle (String id)
    {
        conn = DerbyDB.createConnection();
        
        try {
            stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("select * from "+ tableName +" where id = " + id);
            
            boolean next = rs.next();
            
            this.id=rs.getInt("id");
            this.customer_id=rs.getInt("customer_id");
            this.brand=rs.getString("brand");
            this.model=rs.getString("model");
            this.platenumber=rs.getString("platenumber");
            this.framenumber=rs.getString("framenumber");
            this.enginenumber=rs.getString("enginenumber");
            this.color=rs.getString("color");
            this.notes=rs.getString("notes");
            
            rs.close();
        } catch (Exception e) {
            System.out.println("Select "+ tableName +" SQL Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
        
    }
    
    
    public void setid(int id){
        this.id=id;
    }
    public int getid(){
        return id;
    }
        
    public void setcustomer_id(int customer_id){
        this.customer_id=customer_id;
    }
    public int getcustomer_id(){
        return customer_id;
    }
    
    public void setbrand(String brand){
        this.brand=brand;
    }
    public String getbrand(){
        return brand;
    }
    
    public void setmodel(String model){
        this.model=model;
    }
    public String getmodel(){
        return model;
    }
    
    public void setplatenumber(String platenumber){
        this.platenumber=platenumber;
    }
    public String getplatenumber(){
        return platenumber;
    }
    
    public void setframenumber(String framenumber){
        this.framenumber=framenumber;
    }
    public String getframenumber(){
        return framenumber;
    }
    
    public void setenginenumber(String enginenumber){
        this.enginenumber=enginenumber;
    }
    public String getenginenumber(){
        return enginenumber;
    }
    
    public void setcolor(String color){
        this.color=color;
    }
    public String getcolor(){
        return color;
    }
    
    public void setnotes(String notes){
        this.notes=notes;
    }
    public String getnotes(){
        return notes;
    }
    
	
	public static Vehicle[] getAllVehicles(String customer_id)
    {
        
        System.out.println("START getAllVehicles");
        Vehicle[] table = null;
        int counter = 0;
        
        conn = DerbyDB.createConnection();
        
        try {
            stmt = conn.createStatement();
            
            ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM "+ tableName +" WHERE customer_id = " + customer_id);
            r.next();
            int count = r.getInt("rowcount") ;
            r.close() ;
            //System.out.println("MyTable has " + count + " row(s).");
            table = new Vehicle[count];
            
            ResultSet rs = stmt.executeQuery("select * from "+ tableName +" WHERE customer_id = " + customer_id + " ORDER BY brand");
            
            while (rs.next()) {
                //System.out.println("car id: " + rs.getString("id") + " car brand: " + rs.getString("brand") +"\n");
                Vehicle vehicle = new Vehicle(rs.getInt("id"), rs.getInt("customer_id"), rs.getString("brand"), rs.getString("model"), rs.getString("platenumber"), rs.getString("framenumber"), rs.getString("enginenumber"), rs.getString("color"), rs.getString("notes"));
                table[counter] = vehicle;
                counter++;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("getAllVehicles Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
        System.out.println("END getAllVehicles");
        return table;
    }
    
    

    public static String[][] getVehiclesTable(String vehicle_id)
    {
        Customer.createTableIfNotExists();
        
        System.out.println("START getVehiclesTable");
        String[][] vehicles = new String [][] {
                                {null,null,null,null,null}
                            };
        int counter = 0;
        
        conn = DerbyDB.createConnection();
        
        try {
            stmt = conn.createStatement();
            
            ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM "+ tableName +" ");
            r.next();
            int count = r.getInt("rowcount") ;
            r.close() ;
            //System.out.println("MyTable has " + count + " row(s).");
            if(count>0){
                vehicles = new String[count][5];

                //ResultSet rs = stmt.executeQuery("select * from "+ tableName +" ORDER BY lastname");
                ResultSet rs = stmt.executeQuery("select id, brand, model, color, platenumber "
                        + "FROM "+ tableName +" "
                        + "ORDER BY brand");
                
                int num = 0;
                
                while (rs.next()) {
                    //System.out.println(++num + ": First Name: " + rs.getString(2) + " Last Name: " + rs.getString(3) +"\n");
                    vehicles[counter][0] = Integer.toString(rs.getInt("id"));
                    vehicles[counter][1] = rs.getString("brand");
                    vehicles[counter][2] = rs.getString("model");
                    vehicles[counter][3] = rs.getString("color");
                    vehicles[counter][4] = rs.getString("platenumber");

                    counter++;
                }
                rs.close();
            }
        } catch (Exception e) {
            System.err.println("getAllVehicles Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
        System.out.println("END getCustomersTable");
        return vehicles;
    }
	
    public static Boolean deleteVehicle (String id)
    {
        conn = DerbyDB.createConnection();
        
        boolean deleted;
        
        try {
            //stmt = conn.createStatement();
            
            PreparedStatement psDelete = conn.prepareStatement("DELETE FROM "+ tableName +" where id = " + id);
            psDelete.executeUpdate();
            
            //ResultSet rs = stmt.executeQuery("DELETE FROM customer where id = " + id);
            
            //boolean next = rs.next();
            deleted = true;
            
        } catch (SQLException e) {
            System.out.println("Exception: "+ e.toString());
            deleted = false;
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
        
        return deleted;
    }
    
    public boolean updateVehicle(){
        boolean result;
        
        conn = DerbyDB.createConnection();
        
        try {
            PreparedStatement psUpdate = conn.prepareStatement("UPDATE " +tableName+ " SET "
                            + "customer_id = "+customer_id+", "
                            + "brand = '"+brand+"', "
                            + "model = '"+model+"', "
                            + "platenumber = '"+platenumber+"', "
                            + "framenumber = '"+framenumber+"', "
                            + "enginenumber = '"+enginenumber+"', "
                            + "color = '"+color+"', "
                            + "notes = '"+notes+"' "
                            + "WHERE id = "+id );
            psUpdate.executeUpdate();
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
                + "customer_id		INTEGER NOT NULL,"
                + "brand	     	VARCHAR(150) NOT NULL, "
                + "model		VARCHAR(150) ,"
                + "platenumber		VARCHAR(30) ,"
                + "framenumber		VARCHAR(30) ,"
                + "enginenumber         VARCHAR(30) ,"
                + "color		VARCHAR(30) ,"
                + "notes                VARCHAR(255) )";
        
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
        } catch (SQLException sqlExcept){
            System.err.println("createTable SQL Exception: "+ sqlExcept.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
    }
}
