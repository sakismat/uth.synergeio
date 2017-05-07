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
        this.service=service;
        this.price=price;
        this.notes=notes;
    }
    
    public ServiceList(String service, Double price, String notes){
        
    }
    
    public ServiceList() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    
    
    public void setid(int id){
        this.id=id;
    }
    public int getid(){
        return id;
    }
    
    public void setservice(String service){
        this.service=service;
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
        this.notes=notes;
    }
    public String getnotes(){
        return notes;
    }
    
    
}
