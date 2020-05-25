/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.sqlator;

import javax.swing.JComboBox;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import projekat.sqlator.SqlTableField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author tesla
 */
public class JDialogNewTable extends javax.swing.JDialog
{

    private SqlTable sqlTable = new SqlTable("TableName");
    private String WORKING_PATH;
    private int fieldCounter = 0;
    
    /**
     * Creates new form JDialogNewTable
     */
    public JDialogNewTable(java.awt.Frame parent, boolean modal, String working_path)
    {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        setModal(true);
        addComboBoxToTable();
        
        this.WORKING_PATH = working_path;
        
        Table_Fields.getModel().addTableModelListener((TableModelEvent e) ->
        {
            if (e.getColumn() > -1)
                handleTableCellValueChange(e.getColumn(), e.getLastRow());
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        Label_Table = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        TextField_TableName = new javax.swing.JTextField();
        Label_Fields = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_Fields = new javax.swing.JTable();
        Button_AddField = new javax.swing.JButton();
        Button_RemoveField = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        Label_SqlPreview = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextArea_SqlPreview = new javax.swing.JTextArea();
        Button_CreateTable = new javax.swing.JButton();
        Button_Cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New table");

        Label_Table.setText("Table");

        TextField_TableName.setText("TableName");
        TextField_TableName.addCaretListener(new javax.swing.event.CaretListener()
        {
            public void caretUpdate(javax.swing.event.CaretEvent evt)
            {
                TextField_TableNameCaretUpdate(evt);
            }
        });

        Label_Fields.setText("Fields");

        Table_Fields.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Name", "Type", "NN", "PK", "AI", "U"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean []
            {
                true, true, true, false, true, true
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Table_Fields);
        if (Table_Fields.getColumnModel().getColumnCount() > 0)
        {
            Table_Fields.getColumnModel().getColumn(0).setPreferredWidth(300);
            Table_Fields.getColumnModel().getColumn(2).setPreferredWidth(40);
            Table_Fields.getColumnModel().getColumn(2).setMaxWidth(40);
            Table_Fields.getColumnModel().getColumn(3).setPreferredWidth(40);
            Table_Fields.getColumnModel().getColumn(3).setMaxWidth(40);
            Table_Fields.getColumnModel().getColumn(4).setPreferredWidth(40);
            Table_Fields.getColumnModel().getColumn(4).setMaxWidth(40);
            Table_Fields.getColumnModel().getColumn(5).setPreferredWidth(40);
            Table_Fields.getColumnModel().getColumn(5).setMaxWidth(40);
        }

        Button_AddField.setText("Add field");
        Button_AddField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                Button_AddFieldActionPerformed(evt);
            }
        });

        Button_RemoveField.setText("Remove field");
        Button_RemoveField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                Button_RemoveFieldActionPerformed(evt);
            }
        });

        Label_SqlPreview.setText("SQL Preview");

        TextArea_SqlPreview.setEditable(false);
        TextArea_SqlPreview.setColumns(20);
        TextArea_SqlPreview.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        TextArea_SqlPreview.setRows(5);
        jScrollPane2.setViewportView(TextArea_SqlPreview);

        Button_CreateTable.setText("Create table");
        Button_CreateTable.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                Button_CreateTableActionPerformed(evt);
            }
        });

        Button_Cancel.setText("Cancel");
        Button_Cancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                Button_CancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Label_Table)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextField_TableName))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_Fields)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Button_AddField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Button_RemoveField))
                            .addComponent(Label_SqlPreview))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Button_CreateTable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_Cancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_Table)
                    .addComponent(TextField_TableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label_Fields)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_AddField)
                    .addComponent(Button_RemoveField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label_SqlPreview)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_Cancel)
                    .addComponent(Button_CreateTable))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_AddFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_Button_AddFieldActionPerformed
    {//GEN-HEADEREND:event_Button_AddFieldActionPerformed
        fieldCounter += 1;

        SqlTableField tableField = new SqlTableField("Field" + fieldCounter, SqlType.INTEGER, false, false, false, false);
        sqlTable.addField(tableField);
        
        DefaultTableModel model = (DefaultTableModel) Table_Fields.getModel();
        model.addRow(new Object[]{"Field" + fieldCounter, "INTEGER", false, false, false, false});
        
        refreshSqlPreview();
    }//GEN-LAST:event_Button_AddFieldActionPerformed

    private void Button_RemoveFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_Button_RemoveFieldActionPerformed
    {//GEN-HEADEREND:event_Button_RemoveFieldActionPerformed
        int row = Table_Fields.getSelectedRow();
        
        if (row > -1)
        {
            DefaultTableModel model = (DefaultTableModel) Table_Fields.getModel();
            model.removeRow(row);
            sqlTable.removeField(row);

            refreshSqlPreview();
        }
    }//GEN-LAST:event_Button_RemoveFieldActionPerformed

    private void TextField_TableNameCaretUpdate(javax.swing.event.CaretEvent evt)//GEN-FIRST:event_TextField_TableNameCaretUpdate
    {//GEN-HEADEREND:event_TextField_TableNameCaretUpdate
        sqlTable.setName(TextField_TableName.getText());
        refreshSqlPreview();
    }//GEN-LAST:event_TextField_TableNameCaretUpdate

    private void Button_CancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_Button_CancelActionPerformed
    {//GEN-HEADEREND:event_Button_CancelActionPerformed
        closeForm();
    }//GEN-LAST:event_Button_CancelActionPerformed

    private void Button_CreateTableActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_Button_CreateTableActionPerformed
    {//GEN-HEADEREND:event_Button_CreateTableActionPerformed
        if (DataHandler.createTable(WORKING_PATH, TextArea_SqlPreview.getText()))
        {
            JOptionPane.showMessageDialog(rootPane, "Created table successfully");
            closeForm();
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Error creating table");
        }
    }//GEN-LAST:event_Button_CreateTableActionPerformed

    private void closeForm()
    {
        setVisible(false);
        dispose();
    }
    
    private void handleTableCellValueChange(int column, int row)
    {
        // Column numbers and their meanings:
        // 0 - Name, 1 - Type, 2 - NN, 3 - PK, 4 - AI, 5 - U
        
        System.out.println("old value: " + column + "; " + row + "; value: " + sqlTable.fieldList.get(row).getName());
        
        switch (column)
        {
            case 0:
                sqlTable.fieldList.get(row).setName(Table_Fields.getValueAt(row, column).toString());
                break;
            case 1:
                sqlTable.fieldList.get(row).setType(SqlType.valueOf(Table_Fields.getValueAt(row, column).toString()));
                break;
            case 2:
                sqlTable.fieldList.get(row).setNotNull((boolean)Table_Fields.getValueAt(row, column));
                break;
            case 4:
                boolean aiEnabled = (boolean)Table_Fields.getValueAt(row, column);
                sqlTable.fieldList.get(row).setAutoIncrement(aiEnabled);
                Table_Fields.setValueAt(aiEnabled, row, column-1); // Sets the PK column to AI boolean value
                break;
            case 5:
                sqlTable.fieldList.get(row).setUnique((boolean)Table_Fields.getValueAt(row, column));
                break;
        }

        System.out.println("new value: " + sqlTable.fieldList.get(row).getName());
        refreshSqlPreview();
    }
    
    private void refreshSqlPreview()
    {
        TextArea_SqlPreview.setText(sqlTable.getSqlCode());
    }
    
    private void addComboBoxToTable()
    {
        TableColumn typeColumn = Table_Fields.getColumnModel().getColumn(1);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("INTEGER");
        comboBox.addItem("TEXT");
        typeColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }
    
    public void showDialog()
    {
        setVisible(true);
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
            java.util.logging.Logger.getLogger(JDialogNewTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(JDialogNewTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(JDialogNewTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(JDialogNewTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JDialogNewTable dialog = new JDialogNewTable(new javax.swing.JFrame(), true, "");
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
    private javax.swing.JButton Button_AddField;
    private javax.swing.JButton Button_Cancel;
    private javax.swing.JButton Button_CreateTable;
    private javax.swing.JButton Button_RemoveField;
    private javax.swing.JLabel Label_Fields;
    private javax.swing.JLabel Label_SqlPreview;
    private javax.swing.JLabel Label_Table;
    private javax.swing.JTable Table_Fields;
    private javax.swing.JTextArea TextArea_SqlPreview;
    private javax.swing.JTextField TextField_TableName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
