/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsimedia.multiproperties.standalone;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import lsimedia.multiproperties.JMultiProperties;

/**
 *
 * @author sbodmer
 */
public class JMultiPropertiesCellRenderer extends javax.swing.JPanel implements ListCellRenderer {
    static final Color COLOR_ODD = new Color(241,245,250);
    
    static final Font MONO_PLAIN = new java.awt.Font("Monospaced", Font.PLAIN, 11);
    static final Font MONO_BOLD = new java.awt.Font("Monospaced", Font.BOLD, 11);
    
    /**
     * Creates new form JMultiPropertiesFileContainerCellRenderer
     */
    public JMultiPropertiesCellRenderer() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LB_Name = new javax.swing.JLabel();
        LB_Location = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        LB_Name.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        LB_Name.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LB_Name.setText("...");
        add(LB_Name, java.awt.BorderLayout.SOUTH);

        LB_Location.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        LB_Location.setText("...");
        add(LB_Location, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        MultiProperties cont = (MultiProperties) value;
        
        JMultiProperties jm = cont.getVisual();
        boolean modified = false;
        if ((jm != null) && jm.isModified()) modified = true;
        LB_Location.setFont(modified?MONO_BOLD:MONO_PLAIN);
        LB_Name.setFont(modified?MONO_BOLD:MONO_PLAIN);
        
        LB_Location.setText(cont.getFile().getParent());
        LB_Name.setText(cont.getFile().getName());
        
        setBackground(isSelected?list.getSelectionBackground():(index%2==0?list.getBackground():COLOR_ODD));
        
        return this;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LB_Location;
    private javax.swing.JLabel LB_Name;
    // End of variables declaration//GEN-END:variables
}
