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
public class ServiceListListModel extends AbstractListModel{
        
    private ServiceList servicelist[];

    public ServiceListListModel(ServiceList servicelist[]){
        this.servicelist=servicelist;
    }


    //String list[][] = getCustomerList();

    //String[] tokens = selected.split(" ");
    //if(tokens.length<2){throw new IllegalArgumentException();}
    //String id = tokens[0];

    @Override
    public int getSize() { return servicelist.length; }
    @Override
    public Object getElementAt(int i) { return servicelist[i].getservice() +   " [" + servicelist[i].getid() + "]"; }

    //public Object getElementIdAt(int i) { return customers[i].getid(); }
    
    
}