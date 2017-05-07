package synergeio;

import java.sql.*;

/**
 *
 * @author matikas
 */
public class Customer {
    private static String tableName = "CUSTOMER";
    
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    
    private Integer id;
    private String firstname;
    private String lastname;
    private String fathername;
    private String adt;
    private String afm;
    private String doy;
    private String address;
    private String city;
    private String postcode;
    private String phone_home;
    private String phone_mobile;
    private String fax;
    private String email;
    private String notes;
    
    public Customer(String firstname, String lastname, String fathername, String adt, String afm, String doy, String address, String city, String postcode, String phone_home, String phone_mobile, String fax, String email, String notes){
        
    }
    
    public Customer() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public void setid(int id){
        this.id=id;
    }
    public int getid(){
        return id;
    }
    
    
    
    public static Customer[] getAllCustomers()
    {   
        Customer[] table = null;
        return table;
    }
    
    

    
    
    public static Boolean deleteCustomer (String id)
    {
        return false;
    }
    
    public boolean updateCustomer(){
        
        return false;
    }
    
    
    
    
    private static void createTable(){
        
    }
}

