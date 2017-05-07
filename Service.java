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
        this.notes=notes;
    }
    
    
    
    public Service() {
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
        this.notes=notes;
    }
    public String getnotes(){
        return notes;
    }
    
    
    
    
    private static void createTable(){
    }
}
