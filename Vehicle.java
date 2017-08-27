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
        
    }
    
    
    
    public Vehicle() {
        throw new UnsupportedOperationException("Not yet implemented");
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
    
    
    
    public static Boolean deleteVehicle (String id)
    {
        
        boolean deleted;

        
        return false;
    }
    
    public boolean updateVehicle(){
        
        
        return false;
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
