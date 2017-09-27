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
public class VehicleListModel extends AbstractListModel{
        
    private Vehicle vehicles[];

    public VehicleListModel(Vehicle vehicles[]){
        this.vehicles=vehicles;
    }
    public VehicleListModel(){
        //this.vehicles=null;
    }

    //String list[][] = getCustomerList();

    //String[] tokens = selected.split(" ");
    //if(tokens.length<2){throw new IllegalArgumentException();}
    //String id = tokens[0];

    @Override
    public int getSize() { return vehicles.length; }
    @Override
    public Object getElementAt(int i) { return vehicles[i].getbrand() + " " + vehicles[i].getmodel() + " " + vehicles[i].getplatenumber() + " [" + vehicles[i].getid() + "]"; }

    //public Object getElementIdAt(int i) { return customers[i].getid(); }
    
    
}