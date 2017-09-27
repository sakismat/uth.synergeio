package synergeio;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author matikas
 */
class CustomerCellRenderer extends JLabel implements ListCellRenderer {

    public CustomerCellRenderer() {
        setOpaque(true);
    }

    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Customer customer = ( Customer )value;  // Using value we are getting the object in JList
        setText( customer.getlastname() + " " + customer.getfirstname() );  // Setting the text
        if (isSelected) {
            Color selectedRGB = new Color(51, 153, 255);
            setBackground(selectedRGB);
            setForeground(Color.white);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }
        return this;
    }
}