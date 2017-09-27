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
public class Service {
    private static String tableName = "SERVICE";
    
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    
    private Integer id;
    private Integer customer_id;
    private Integer servicelist_id;
    private Integer vehicle_id;
    private String date;
    private Integer kilometers;
    private Double price;
    private Double balance;
    private Integer receipted;
    private String notes;
    
    public Service(Integer id, Integer customer_id, Integer servicelist_id, Integer vehicle_id, String date, Integer kilometers, Double price, Double balance, Integer receipted, String notes){
        this.id=id;
        this.customer_id=customer_id;
        this.servicelist_id=servicelist_id;
        this.vehicle_id=vehicle_id;
        this.date=date;
        this.kilometers=kilometers;
        this.price=price;
        this.balance=balance;
        this.receipted=receipted;
        this.notes=DerbyDB.cutString(notes,255);
    }
    
    public Service(Integer customer_id, Integer servicelist_id, Integer vehicle_id, String date, Integer kilometers, Double price, Double balance, Integer receipted, String notes){
        this.customer_id=customer_id;
        this.servicelist_id=servicelist_id;
        this.vehicle_id=vehicle_id;
        this.date=date;
        this.kilometers=kilometers;
        this.price=price;
        this.balance=balance;
        this.receipted=receipted;
        this.notes=DerbyDB.cutString(notes,255);
        
        conn = DerbyDB.createConnection();
        
        try {
            PreparedStatement psInsert = conn.prepareStatement("insert into "+ tableName +" "
                    + "(customer_id, servicelist_id, vehicle_id, date, kilometers, price, balance, receipted, notes) "
                    + "values "
                    + "("+this.customer_id+", "+this.servicelist_id+", "+this.vehicle_id+", '"+this.date+"', "+this.kilometers+", "+this.price+", "+this.balance+", "+this.receipted+", '"+this.notes+"')");
            psInsert.executeUpdate();
        } catch (Exception e) {
            System.err.println("Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
    }
    
    public Service() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public Service (String id)
    {
        conn = DerbyDB.createConnection();
        
        try {
            stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("select * from "+ tableName +" where id = " + id);
            
            boolean next = rs.next();
            
            this.id=rs.getInt("id");
            this.id=rs.getInt("customer_id");
            this.id=rs.getInt("servicelist_id");
            this.id=rs.getInt("vehicle_id");
            this.date=rs.getString("date");
            this.kilometers=rs.getInt("kilometers");
            this.price=rs.getDouble("price");
            this.balance=rs.getDouble("balance");
            this.receipted=rs.getInt("receipted");
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
    
    public void setcustomer_id(int customer_id){
        this.customer_id=customer_id;
    }
    public int getcustomer_id(){
        return customer_id;
    }
    
    public void setservicelist_id(int servicelist_id){
        this.servicelist_id=servicelist_id;
    }
    public int getservicelist_id(){
        return servicelist_id;
    }
    
    public void setvehicle_id(int vehicle_id){
        this.vehicle_id=vehicle_id;
    }
    public int getvehicle_id(){
        return vehicle_id;
    }
    
    public void setdate(String date){
        this.date=date;
    }
    public String getdate(){
        return date;
    }
    
    public void setkilometers(int kilometers){
        this.kilometers=kilometers;
    }
    public int getkilometers(){
        return kilometers;
    }
    
    public void setprice(double price){
        this.price=price;
    }
    public double getprice(){
        return price;
    }
    
    public void setbalance(double balance){
        this.balance=balance;
    }
    public double getbalance(){
        return balance;
    }
    
    public void setreceipted(int receipted){
        this.receipted=receipted;
    }
    public int getreceipted(){
        return receipted;
    }
    
    public void setnotes(String notes){
        this.notes=DerbyDB.cutString(notes,255);
    }
    public String getnotes(){
        return notes;
    }
    
    
    public static Service[] getServices()
    {
        System.out.println("START getServices");//DEBUG
        Service[] services = null;
        int counter = 0;
        
        conn = DerbyDB.createConnection();
        
        try {
            stmt = conn.createStatement();
            
            ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM "+ tableName +"");
            r.next();
            int count = r.getInt("rowcount") ;
            r.close() ;
            services = new Service[count];
            
            ResultSet rs = stmt.executeQuery("select * from "+ tableName +" ORDER BY service");
            int num = 0;
            
            while (rs.next()) {
                Service service = new Service(rs.getInt("id"), rs.getInt("customer_id"), rs.getInt("servicelist_id"), rs.getInt("vehicle_id"), rs.getString("date"), rs.getInt("kilometers"), rs.getDouble("price"), rs.getDouble("balance"), rs.getInt("receipted"), rs.getString("notes"));
                services[counter] = service;
                counter++;
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("getServices Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
        System.out.println("END getServiceList");//DEBUG
        return services;
    }
	
	public static String[][] getServicesTable()
    {
        System.out.println("START getServicesTable");
        String[][] customers = new String [][] {
                                {null,null,null,null,null,null,null,null}
                            };
        int counter = 0;
        
        conn = DerbyDB.createConnection();
        
        try {
            stmt = conn.createStatement();
            
            ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM "+ tableName +"");
            r.next();
            int count = r.getInt("rowcount") ;
            r.close() ;
            //System.out.println("MyTable has " + count + " row(s).");
            if(count>0){
                customers = new String[count][8];

                //ResultSet rs = stmt.executeQuery("select * from "+ tableName +" ORDER BY lastname");
                ResultSet rs = stmt.executeQuery("select customer.id, customer.lastname, customer.firstname, customer.fathername, customer.city, customer.address, SUM(service.balance) AS count_balance, SUM(service.price) AS count_price "
                        + "FROM customer LEFT JOIN service "
                        + "ON customer.id = service.customer_id "
                        + "GROUP BY customer.id, customer.lastname, customer.firstname, customer.fathername, customer.city, customer.address "
                        + "ORDER BY lastname");
                
                int num = 0;
                
                while (rs.next()) {
                    //System.out.println(++num + ": First Name: " + rs.getString(2) + " Last Name: " + rs.getString(3) +"\n");
                    customers[counter][0] = Integer.toString(rs.getInt("id"));
                    customers[counter][1] = rs.getString("lastname");
                    customers[counter][2] = rs.getString("firstname");
                    customers[counter][3] = rs.getString("fathername");
                    customers[counter][4] = rs.getString("city");
                    customers[counter][5] = rs.getString("address");
                    customers[counter][6] = rs.getString("count_price");
                    customers[counter][7] = rs.getString("count_balance");

                    counter++;
                }
                rs.close();
            }
        } catch (Exception e) {
            System.err.println("getServicesTable Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
        System.out.println("END getServicesTable");
        return customers;
    }
    
    
    
    public static Boolean deleteService (String id)
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
    
    
    public boolean updateService(){
        boolean result;
        
        conn = DerbyDB.createConnection();
        
        try {
            PreparedStatement psInsert = conn.prepareStatement("UPDATE "+ tableName +" SET "
                            + "customer_id = "+customer_id+", "
                            + "servicelist_id = "+servicelist_id+", "
                            + "vehicle_id = "+vehicle_id+", "
                            + "date = '"+date+"', "
                            + "kilometers = "+kilometers+", "
                            + "price = "+price+", "
                            + "balance = "+balance+", "
                            + "receipted = "+receipted+", "
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
                System.out.println("Table " +tableName+ " does not exist");//DEBUG
                createTable();
            }
            
        } catch (SQLException e) {
            System.err.println("Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
    }
    
    private static void createTable(){
        conn = DerbyDB.createConnection();
        System.out.println("Create Table: " + tableName);//DEBUG
        //createTableService_______________________________________
        String createString = "CREATE table " + tableName + " ("
                + "customer_id		INTEGER NOT NULL,"
                + "servicelist_id	INTEGER NOT NULL,"
                + "vehicle_id		INTEGER NOT NULL,"
                + "date			DATE,"
                + "kilometers		INTEGER,"
                + "price     		DOUBLE NOT NULL DEFAULT 0,"
                + "balance		DOUBLE NOT NULL DEFAULT 0,"
                + "receipted		SMALLINT,"
                + "notes         	VARCHAR(255) )";
        
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
        } catch (SQLException sqlExcept){
            System.err.println("createTable SQL Exception: "+ sqlExcept.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
    }
}
