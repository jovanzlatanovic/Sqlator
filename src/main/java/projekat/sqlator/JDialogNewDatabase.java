/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.sqlator;

import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author tesla
 */
public class JDialogNewDatabase extends javax.swing.JDialog
{

    /**
     * Creates new form JDialogNewDatabase
     */
    public JDialogNewDatabase(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        setModal(true);
    }
    
    //Class global variable for setting the return value of the dialog
    private String returnValue;
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        Label_Name = new javax.swing.JLabel();
        Label_Path = new javax.swing.JLabel();
        TextField_databaseName = new javax.swing.JTextField();
        TextField_DatabasePath = new javax.swing.JTextField();
        Button_Confirm = new javax.swing.JButton();
        Label_resultPath = new javax.swing.JLabel();
        Label_result = new javax.swing.JLabel();
        Button_ChooseDirectory = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New database");
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        Label_Name.setText("Database name:");

        Label_Path.setText("Database path:");

        TextField_databaseName.setText("database");
        TextField_databaseName.addCaretListener(new javax.swing.event.CaretListener()
        {
            public void caretUpdate(javax.swing.event.CaretEvent evt)
            {
                TextField_databaseNameCaretUpdate(evt);
            }
        });

        TextField_DatabasePath.setText("C:\\Users\\tesla\\Downloads");
        TextField_DatabasePath.addCaretListener(new javax.swing.event.CaretListener()
        {
            public void caretUpdate(javax.swing.event.CaretEvent evt)
            {
                TextField_DatabasePathCaretUpdate(evt);
            }
        });

        Button_Confirm.setText("Confirm");
        Button_Confirm.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                Button_ConfirmActionPerformed(evt);
            }
        });

        Label_resultPath.setText("Result path:");

        Button_ChooseDirectory.setText("Browse");
        Button_ChooseDirectory.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                Button_ChooseDirectoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_Name)
                    .addComponent(Label_Path)
                    .addComponent(Label_resultPath))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextField_databaseName)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Label_result)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TextField_DatabasePath, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                .addGap(14, 14, 14)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Button_ChooseDirectory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Button_Confirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_Name)
                    .addComponent(TextField_databaseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_Path)
                    .addComponent(TextField_DatabasePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_ChooseDirectory))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_Confirm)
                    .addComponent(Label_resultPath)
                    .addComponent(Label_result))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_ConfirmActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_Button_ConfirmActionPerformed
    {//GEN-HEADEREND:event_Button_ConfirmActionPerformed
        String path = TextField_DatabasePath.getText() + "\\" + TextField_databaseName.getText() + ".db";
        File f = new File(path);
        if (f.exists() && !f.isDirectory())
        {
            JOptionPane.showMessageDialog(rootPane, "A database with that name already exists.");
        }
        else
        {
            returnValue = path;

            setVisible(false);
            dispose();
        }
    }//GEN-LAST:event_Button_ConfirmActionPerformed

    private void TextField_databaseNameCaretUpdate(javax.swing.event.CaretEvent evt)//GEN-FIRST:event_TextField_databaseNameCaretUpdate
    {//GEN-HEADEREND:event_TextField_databaseNameCaretUpdate
        updatePathPreview();
    }//GEN-LAST:event_TextField_databaseNameCaretUpdate

    private void TextField_DatabasePathCaretUpdate(javax.swing.event.CaretEvent evt)//GEN-FIRST:event_TextField_DatabasePathCaretUpdate
    {//GEN-HEADEREND:event_TextField_DatabasePathCaretUpdate
        updatePathPreview();
    }//GEN-LAST:event_TextField_DatabasePathCaretUpdate

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        returnValue = "false";
    }//GEN-LAST:event_formWindowClosing

    private void Button_ChooseDirectoryActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_Button_ChooseDirectoryActionPerformed
    {//GEN-HEADEREND:event_Button_ChooseDirectoryActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select save directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        if (fileChooser.showDialog(this, "Select directory") == JFileChooser.APPROVE_OPTION)
        {
            TextField_DatabasePath.setText(fileChooser.getSelectedFile().toString());
            updatePathPreview();
        }
    }//GEN-LAST:event_Button_ChooseDirectoryActionPerformed
    
    //TODO Change this so that it shows the last part of the path, not the first
    private void updatePathPreview()
    {
        String path = TextField_DatabasePath.getText() + "\\" + TextField_databaseName.getText() + ".db";
        String s = path.substring(0, Math.min(path.length(), 30));
        if (path.length() > 30)
            s += "...";
        Label_result.setText(s);
    }
    
    public String showDialog()
    {
        setVisible(true);
        return returnValue;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(JDialogNewDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(JDialogNewDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(JDialogNewDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(JDialogNewDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JDialogNewDatabase dialog = new JDialogNewDatabase(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter()
                {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e)
                    {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_ChooseDirectory;
    private javax.swing.JButton Button_Confirm;
    private javax.swing.JLabel Label_Name;
    private javax.swing.JLabel Label_Path;
    private javax.swing.JLabel Label_result;
    private javax.swing.JLabel Label_resultPath;
    private javax.swing.JTextField TextField_DatabasePath;
    private javax.swing.JTextField TextField_databaseName;
    // End of variables declaration//GEN-END:variables
}