/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package synergeio;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author matikas
 */

class ServiceListComboBoxModel extends AbstractListModel implements ComboBoxModel {
    private final ServiceList servicelist[];

    public ServiceListComboBoxModel(ServiceList servicelist[]){
        this.servicelist=servicelist;
    }

    String selection = null;

    @Override
    public Object getElementAt(int index) {
        return servicelist[index].getservice() +   " [" + servicelist[index].getid() + "]";
    }

    @Override
    public int getSize() {
        return servicelist.length;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (String) anItem; // to select and register an
    } // item from the pull-down list

    // Methods implemented from the interface ComboBoxModel
    @Override
    public Object getSelectedItem() {
        return selection; // to add the selection to the combo box
    }
}