package synergeio;

import javax.swing.AbstractListModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matikas
 */
public class CustomerListModel extends AbstractListModel{
        
    private Customer customers[];

    public CustomerListModel(Customer customers[]){
        this.customers=customers;
    }


    //String list[][] = getCustomerList();

    //String[] tokens = selected.split(" ");
    //if(tokens.length<2){throw new IllegalArgumentException();}
    //String id = tokens[0];

    @Override
    public int getSize() { return customers.length; }
    @Override
    public Object getElementAt(int i) { return customers[i].getlastname() + " " + customers[i].getfirstname() + " [" + customers[i].getid() + "]"; }

    //public Object getElementIdAt(int i) { return customers[i].getid(); }
    
    
}
