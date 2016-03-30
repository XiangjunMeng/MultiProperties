/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsimedia.multiproperties.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import lsimedia.multiproperties.Column;
import lsimedia.multiproperties.HandlerGUI;

/**
 * The column for the Java Properties handler<p>
 * 
 * If the description of the column is "SAMEFOLDER" then check the same folder checkbox, this
 * hack is needed for eclipse plugin compatibility
 *
 * @author sbodmer
 */
public class JJavaPropertiesHandler extends javax.swing.JPanel implements HandlerGUI, ActionListener {

    Column column = null;

    /**
     * Creates new form JJavaPropertiesHandler
     *
     * The configuration string is
     * <PRE>
     * /KnoP_Birt_Report/audit/Birt/Audit_fr.properties|false|false|false|false
     * </PRE>
     *
     */
    public JJavaPropertiesHandler(Column column) {
        this.column = column;

        initComponents();

        CB_EnableOutput.addActionListener(this);
        CB_SameFolder.addActionListener(this);
        
        BT_FC.addActionListener(this);
        
        //-- Fill with the value
        String tokens[] = column.getHandlerConfiguration().split("\\|");
        try {
            String fn = tokens[0];
            if (!fn.equals("")) {
                File f = new File(fn);
                TF_Location.setText(f.getParent());
                TF_Filename.setText(f.getName());
            }
            CB_EnableOutput.setSelected(!fn.equals(""));
            TF_Location.setEditable(!fn.equals(""));
            TF_Filename.setEditable(!fn.equals(""));
            BT_FC.setEnabled(!fn.equals(""));
            
            CB_InsertDescriptionComment.setSelected(tokens[1].equals("true"));
            CB_InsertColumnComment.setSelected(tokens[2].equals("true"));
            CB_WriteDisableComment.setSelected(tokens[3].equals("true"));
            CB_DisableDefault.setSelected(tokens[4].equals("true"));
            CB_SameFolder.setSelected(tokens[5].equals("true"));
            
            //--- Handle special case for compatibility with eclipse plugin
            if (column.getDescription().equals("SAMEFOLDER")) CB_SameFolder.setSelected(true);
            
            if (CB_SameFolder.isSelected()) {
                TF_Location.setEditable(false);
                TF_Location.setText("");
                BT_FC.setEnabled(false);
                   
            }
            
        } catch (Exception ex) {
            //---
        }

    }

    @Override
    public JComponent getVisual() {
        return this;
    }

    @Override
    public void apply() {
        //--- Store the value in Column instance
        String txt = "";
        if (CB_EnableOutput.isSelected()) {
            File f = new File(TF_Location.getText(), TF_Filename.getText());
            txt = f.getPath();
        }
        txt += "|" + CB_InsertDescriptionComment.isSelected();
        txt += "|" + CB_InsertColumnComment.isSelected();
        txt += "|" + CB_WriteDisableComment.isSelected();
        txt += "|" + CB_DisableDefault.isSelected();
        txt += "|" + CB_SameFolder.isSelected();
        
        column.setHandlerConfiguration(txt);
    }

    //**************************************************************************
    //*** ActionListener
    //**************************************************************************
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("enable")) {
            if (CB_EnableOutput.isSelected()) {
                TF_Location.setEditable(true);
                TF_Filename.setEditable(true);
                BT_FC.setEnabled(true);
                
            } else {
                TF_Location.setText("");
                TF_Location.setEditable(false);
                TF_Filename.setText("");
                TF_Filename.setEditable(false);
                BT_FC.setEnabled(false);
            }
            
        } else if (e.getActionCommand().equals("fc")) {
            File f = new File(TF_Location.getText(), TF_Filename.getText());
            JFileChooser fc = new JFileChooser(f);
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int rep = fc.showSaveDialog(this);
            if (rep == JFileChooser.APPROVE_OPTION) {
                f = fc.getSelectedFile();
                TF_Location.setText(f.getParent());
                TF_Filename.setText(f.getName());
            }
            
        } else if (e.getActionCommand().equals("same")) {
            if (CB_SameFolder.isSelected()) {
                TF_Location.setEditable(false);
                TF_Location.setText("");
                BT_FC.setEnabled(false);
                
            } else {
                TF_Location.setEditable(true);
                BT_FC.setEnabled(true);
                
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        CB_EnableOutput = new javax.swing.JCheckBox();
        TF_Location = new javax.swing.JTextField();
        BT_FC = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TF_Filename = new javax.swing.JTextField();
        CB_InsertDescriptionComment = new javax.swing.JCheckBox();
        CB_InsertColumnComment = new javax.swing.JCheckBox();
        CB_WriteDisableComment = new javax.swing.JCheckBox();
        CB_DisableDefault = new javax.swing.JCheckBox();
        CB_SameFolder = new javax.swing.JCheckBox();

        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        CB_EnableOutput.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(CB_EnableOutput, org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.CB_EnableOutput.text")); // NOI18N
        CB_EnableOutput.setActionCommand(org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.CB_EnableOutput.actionCommand")); // NOI18N

        TF_Location.setEditable(false);
        TF_Location.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        TF_Location.setText(org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.TF_Location.text")); // NOI18N

        BT_FC.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(BT_FC, org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.BT_FC.text")); // NOI18N
        BT_FC.setActionCommand(org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.BT_FC.actionCommand")); // NOI18N
        BT_FC.setEnabled(false);
        BT_FC.setMargin(new java.awt.Insets(2, 2, 2, 2));
        BT_FC.setMaximumSize(new java.awt.Dimension(16, 16));
        BT_FC.setMinimumSize(new java.awt.Dimension(16, 16));
        BT_FC.setPreferredSize(new java.awt.Dimension(16, 16));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.jLabel2.text")); // NOI18N

        TF_Filename.setEditable(false);
        TF_Filename.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        TF_Filename.setText(org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.TF_Filename.text")); // NOI18N

        CB_InsertDescriptionComment.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(CB_InsertDescriptionComment, org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.CB_InsertDescriptionComment.text")); // NOI18N

        CB_InsertColumnComment.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(CB_InsertColumnComment, org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.CB_InsertColumnComment.text")); // NOI18N

        CB_WriteDisableComment.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(CB_WriteDisableComment, org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.CB_WriteDisableComment.text")); // NOI18N

        CB_DisableDefault.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(CB_DisableDefault, org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.CB_DisableDefault.text")); // NOI18N

        CB_SameFolder.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(CB_SameFolder, org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.CB_SameFolder.text")); // NOI18N
        CB_SameFolder.setActionCommand(org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.CB_SameFolder.actionCommand")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TF_Filename)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(TF_Location)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BT_FC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CB_EnableOutput)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(CB_InsertDescriptionComment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CB_InsertColumnComment, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                    .addComponent(CB_WriteDisableComment, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                    .addComponent(CB_DisableDefault, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                    .addComponent(CB_SameFolder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CB_EnableOutput)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_SameFolder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_Location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BT_FC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TF_Filename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CB_InsertDescriptionComment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_InsertColumnComment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_WriteDisableComment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_DisableDefault)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(JJavaPropertiesHandler.class, "JJavaPropertiesHandler.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_FC;
    private javax.swing.JCheckBox CB_DisableDefault;
    private javax.swing.JCheckBox CB_EnableOutput;
    private javax.swing.JCheckBox CB_InsertColumnComment;
    private javax.swing.JCheckBox CB_InsertDescriptionComment;
    private javax.swing.JCheckBox CB_SameFolder;
    private javax.swing.JCheckBox CB_WriteDisableComment;
    private javax.swing.JTextField TF_Filename;
    private javax.swing.JTextField TF_Location;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

}
