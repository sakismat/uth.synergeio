package synergeio;

import java.sql.*;

/**
 *
 * @author matikas
 */
public class Customer {
    //arxikopoiisi tou onomatos tou pinaka sti vasi dedomenon
    private static String tableName = "CUSTOMER";
    
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    
    //
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
    
    //Customer class Constructors 
    //Constructor 1
    public Customer(Integer id, String firstname, String lastname, String fathername, String adt, String afm, String doy, String address, String city, String postcode, String phone_home, String phone_mobile, String fax, String email, String notes){
        this.id=id;
        this.firstname=DerbyDB.cutString(firstname,30);
        this.lastname=DerbyDB.cutString(lastname,30);
        this.fathername=DerbyDB.cutString(fathername,30);
        this.adt=DerbyDB.cutString(adt,30);
        this.afm=DerbyDB.cutString(afm,30);
        this.doy=DerbyDB.cutString(doy,30);
        this.address=DerbyDB.cutString(address,100);
        this.city=DerbyDB.cutString(city,30);
        this.postcode=DerbyDB.cutString(postcode,30);
        this.phone_home=DerbyDB.cutString(phone_home,30);
        this.phone_mobile=DerbyDB.cutString(phone_mobile,30);
        this.fax=DerbyDB.cutString(fax,30);
        this.email=DerbyDB.cutString(email,100);
        this.notes=DerbyDB.cutString(notes,255);
    }
    
    //Constructor 2 -- dimiourgei ena pelati sti vasi (to id to dimiourgei i basi)
    public Customer(String firstname, String lastname, String fathername, String adt, String afm, String doy, String address, String city, String postcode, String phone_home, String phone_mobile, String fax, String email, String notes){
        this.firstname=DerbyDB.cutString(firstname,30);
        this.lastname=DerbyDB.cutString(lastname,30);
        this.fathername=DerbyDB.cutString(fathername,30);
        this.adt=DerbyDB.cutString(adt,30);
        this.afm=DerbyDB.cutString(afm,30);
        this.doy=DerbyDB.cutString(doy,30);
        this.address=DerbyDB.cutString(address,100);
        this.city=DerbyDB.cutString(city,30);
        this.postcode=DerbyDB.cutString(postcode,30);
        this.phone_home=DerbyDB.cutString(phone_home,30);
        this.phone_mobile=DerbyDB.cutString(phone_mobile,30);
        this.fax=DerbyDB.cutString(fax,30);
        this.email=DerbyDB.cutString(email,100);
        this.notes=DerbyDB.cutString(notes,255);
        
        //an den yparxei pinakas sti vasi ton dimiourgei
        createTableIfNotExists();
        
        //sindesi me tin DB
        conn = DerbyDB.createConnection();
        
        try {
            PreparedStatement psInsert = conn.prepareStatement("insert into customer (firstname, lastname, fathername, adt, afm, doy, address, city, postcode, phone_home, phone_mobile, fax, email, notes) values ('"+this.firstname+"','"+this.lastname+"','"+this.fathername+"','"+this.adt+"','"+this.afm+"','"+this.doy+"','"+this.address+"','"+this.city+"','"+this.postcode+"','"+this.phone_home+"','"+this.phone_mobile+"','"+this.fax+"','"+this.email+"','"+this.notes+"')");
            psInsert.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
    }
    
    //Constructor 3 -- vriskei enan pelati apo ti vasi me sigekrimeno id
    public Customer (String id)
    {
        conn = DerbyDB.createConnection();
        
        try {
            stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("select * from customer where id = " + id);
            
            boolean next = rs.next();
            
            this.id=rs.getInt(1);
            this.firstname=rs.getString(2);
            this.lastname=rs.getString(3);
            this.fathername=rs.getString(4);
            this.adt=rs.getString(5);
            this.afm=rs.getString(6);
            this.doy=rs.getString(7);
            this.address=rs.getString(8);
            this.city=rs.getString(9);
            this.postcode=rs.getString(10);
            this.phone_home=rs.getString(11);
            this.phone_mobile=rs.getString(12);
            this.fax=rs.getString(13);
            this.email=rs.getString(14);
            this.notes=rs.getString(15);
            
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
        
    }
    
    //Default Constructor
    public Customer() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    //prosvasi stis private metavlites me methodous get... set...
    public void setid(int id){
        this.id=id;
    }
    public int getid(){
        return id;
    }
    
    public void setfirstname(String firstname){
        this.firstname=DerbyDB.cutString(firstname,30);
    }
    public String getfirstname(){
        return firstname;
    }
    
    public void setlastname(String lastname){
        this.lastname=DerbyDB.cutString(lastname,30);
    }
    public String getlastname(){
        return lastname;
    }
    
    public void setfathername(String fathername){
        this.fathername=DerbyDB.cutString(fathername,30);
    }
    public String getfathername(){
        return fathername;
    }
    
    public void setadt(String adt){
        this.adt=DerbyDB.cutString(adt,30);
    }
    public String getadt(){
        return adt;
    }
    
    public void setafm(String afm){
        this.afm=DerbyDB.cutString(afm,30);
    }
    public String getafm(){
        return afm;
    }
    
    public void setdoy(String doy){
        this.doy=DerbyDB.cutString(doy,30);
    }
    public String getdoy(){
        return doy;
    }
    
    public void setaddress(String address){
        this.address=DerbyDB.cutString(address,100);
    }
    public String getaddress(){
        return address;
    }
    
    public void setcity(String city){
        this.city=DerbyDB.cutString(city,30);
    }
    public String getcity(){
        return city;
    }
    
    public void setpostcode(String postcode){
        this.postcode=DerbyDB.cutString(postcode,30);
    }
    public String getpostcode(){
        return postcode;
    }
    
    public void setphone_home(String phone_home){
        this.phone_home=DerbyDB.cutString(phone_home,30);
    }
    public String getphone_home(){
        return phone_home;
    }
    
    public void setphone_mobile(String phone_mobile){
        this.phone_mobile=DerbyDB.cutString(phone_mobile,30);
    }
    public String getphone_mobile(){
        return phone_mobile;
    }
    
    public void setfax(String fax){
        this.fax=DerbyDB.cutString(fax,30);
    }
    public String getfax(){
        return fax;
    }
    
    public void setemail(String email){
        this.email=DerbyDB.cutString(email,100);
    }
    public String getemail(){
        return email;
    }
    
    public void setnotes(String notes){
        this.notes=DerbyDB.cutString(notes,255);
    }
    public String getnotes(){
        return notes;
    }
    
    public static Customer[] getAllCustomers()
    {   
        System.out.println("START getAllCustomers");
        Customer[] table = null;
        int counter = 0;
        
        conn = DerbyDB.createConnection();
        
        try {
            stmt = conn.createStatement();
            
            ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM "+ tableName +"");
            r.next();
            int count = r.getInt("rowcount") ;
            r.close() ;
            //System.out.println("MyTable has " + count + " row(s).");
            table = new Customer[count];
            
            ResultSet rs = stmt.executeQuery("select * from "+ tableName +" ORDER BY lastname");
            int num = 0;
            
            while (rs.next()) {
                //System.out.println(++num + ": First Name: " + rs.getString(2) + " Last Name: " + rs.getString(3) +"\n");
                Customer customer = new Customer(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("fathername"), rs.getString("adt"), rs.getString("afm"), rs.getString("doy"), rs.getString("address"), rs.getString("city"), rs.getString("postcode"), rs.getString("phone_home"), rs.getString("phone_mobile"), rs.getString("fax"), rs.getString("email"), rs.getString("notes"));
                table[counter] = customer;
                counter++;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("getAllCustomers Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
        System.out.println("END getAllCustomers");
        return table;
    }
    
    

    public static String[][] getCustomersTable()
    {
        System.out.println("START getCustomersTable");
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
            System.err.println("getAllCustomers Exception: "+ e.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
        System.out.println("END getCustomersTable");
        return customers;
    }
    
    
    public static Boolean deleteCustomer (String id)
    {
        conn = DerbyDB.createConnection();
        
        boolean deleted;
        
        try {
            //stmt = conn.createStatement();
            
            PreparedStatement psInsert = conn.prepareStatement("DELETE FROM "+ tableName +" where id = " + id);
            psInsert.executeUpdate();
            
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
    
    public boolean updateCustomer(){
        boolean result;
        
        conn = DerbyDB.createConnection();
        
        try {
            PreparedStatement psInsert = conn.prepareStatement("UPDATE customer SET "
                            + "firstname = '"+firstname+"', "
                            + "lastname = '"+lastname+"', "
                            + "fathername = '"+fathername+"', "
                            + "adt = '"+adt+"', "
                            + "afm = '"+afm+"', "
                            + "doy = '"+doy+"', "
                            + "address = '"+address+"', "
                            + "city = '"+city+"', "
                            + "postcode = '"+postcode+"', "
                            + "phone_home = '"+phone_home+"', "
                            + "phone_mobile = '"+phone_mobile+"', "
                            + "fax = '"+fax+"', "
                            + "email = '"+email+"', "
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
                + "firstname     VARCHAR(30) NOT NULL, "
                + "lastname   	  VARCHAR(30) NOT NULL,"
                + "fathername    VARCHAR(30),"
                + "adt  		  VARCHAR(30),"
                + "afm  		  VARCHAR(30),"
                + "doy  		  VARCHAR(30),"
                + "address       VARCHAR(100),"
                + "city       	  VARCHAR(30), "
                + "postcode      VARCHAR(30),"
                + "phone_home    VARCHAR(30),"
                + "phone_mobile  VARCHAR(30),"
                + "fax           VARCHAR(30),"
                + "email		  VARCHAR(100),"
                + "notes         VARCHAR(255) )";
        
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            System.out.println("Created");
        } catch (SQLException sqlExcept){
            System.err.println("createTable SQL Exception: "+ sqlExcept.toString());
        }
        
        DerbyDB.shutdownConnection(conn, stmt);
    }
}
