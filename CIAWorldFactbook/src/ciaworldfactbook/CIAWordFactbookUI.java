/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ciaworldfactbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Devon
 */
public class CIAWordFactbookUI  extends javax.swing.JFrame {

    /**
     * Creates new form CIAWordFactbookUI
     */
    WFBTable table;
    CIAWorldFactbook wfb;
    String[] factListArr;
    boolean listPopulated;
    boolean countrySelected;
    boolean factTypeSelected;
    boolean factSelected;
    boolean worldSelected;
    
    public CIAWordFactbookUI() throws FileNotFoundException, IOException {
        initComponents();
        wfb = new CIAWorldFactbook(); // constructs object that creates hash maps to be loaded into JLists
        listPopulated = false;
        countrySelected = false;
        factTypeSelected = false;
        factSelected = false;
        worldSelected = false;
        
        try 
        {

            table = new WFBTable();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
                       
        
        for (String country : table.getCountries())
        {
            jComboBox1.addItem(country);
        }
        
        for (String value : wfb.countryFactTypeList.keySet())
        {
            factTypeSelector.addItem(value);
        }
        
        factDisplayTextArea.setEditable(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jComboBox1 = new javax.swing.JComboBox();
        factTypeSelector = new javax.swing.JComboBox();
        factSelector = new javax.swing.JComboBox();
        getFactButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        factDisplay = new javax.swing.JScrollPane();
        factDisplayTextArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(635, 770));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Country" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        factTypeSelector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Fact Type" }));
        factTypeSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                factTypeSelectorActionPerformed(evt);
            }
        });

        factSelector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Fact" }));
        factSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                factSelectorActionPerformed(evt);
            }
        });

        getFactButton.setText("Get Fact");
        getFactButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getFactButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        factDisplayTextArea.setColumns(20);
        factDisplayTextArea.setRows(5);
        factDisplay.setViewportView(factDisplayTextArea);

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Devon\\Documents\\NetBeansProjects\\CIAWorldFactbook\\src\\image\\cia_factbook.jpg")); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel3.setText("Image Source: http://www.infotopia.info/images/cia_factbook.jpg");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(getFactButton)
                .addGap(145, 145, 145)
                .addComponent(clearButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addComponent(exitButton)
                .addGap(83, 83, 83))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(factDisplay)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(149, 149, 149))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(factTypeSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(factSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(193, 193, 193))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(30, 30, 30)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(factTypeSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(factSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(factDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearButton)
                    .addComponent(getFactButton)
                    .addComponent(exitButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if(jComboBox1.getSelectedItem().equals("Select Country")){
            countrySelected = false;
        } else {
            countrySelected = true;
        }
        
        if(jComboBox1.getSelectedItem().equals("World"))
        {
            worldSelected = true;
        }
        else
        {
            worldSelected = false;
        }
            
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void factTypeSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factTypeSelectorActionPerformed
        if (factSelector.getSelectedItem().equals("Select Fact Type"))
        {
            factTypeSelected = false;
        }
        
        if(listPopulated)
        {
            factSelector.removeAllItems();
            factSelector.addItem("Select Fact");
        }
        
        populateWorldOrCountry(worldSelected);
        
        factTypeSelected = true;
    }//GEN-LAST:event_factTypeSelectorActionPerformed

    private void factSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factSelectorActionPerformed
        factSelected = true;
    }//GEN-LAST:event_factSelectorActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        factDisplayTextArea.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void getFactButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getFactButtonActionPerformed
        
        factDisplayTextArea.setText("");
        factDisplayTextArea.setLineWrap(true);
        factDisplayTextArea.setWrapStyleWord(true);
        if (!countrySelected || !factTypeSelected || !factSelected)
        {
            factDisplayTextArea.append("Please select country, fact type, and fact");
        }
        else
        {
            String selectedFact = (String) factSelector.getSelectedItem();
            
            String countryCode = wfb.getCountryCodeList().get((String) jComboBox1.getSelectedItem());
            try {
                String fact = wfb.getCountryFact(countryCode, selectedFact);
                if (fact==null) {
                    factDisplayTextArea.append("No data found");
                }
                else {
                    factDisplayTextArea.append(fact);
                }
            } catch (IOException ex) {
                Logger.getLogger(CIAWordFactbookUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_getFactButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws FileNotFoundException, IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CIAWordFactbookUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CIAWordFactbookUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CIAWordFactbookUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CIAWordFactbookUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
 
                            try {
                                new CIAWordFactbookUI().setVisible(true);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(CIAWordFactbookUI.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(CIAWordFactbookUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } 
        
                    
                });
//        String countryFact = WFBConnections.getCountryFact("us", "Economy - overview");
//        System.out.println(countryFact);

    }

    
    private static String[] populateList(String fileName) throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File(fileName));
        ArrayList<String> list = new ArrayList<String>();
        while (scan.hasNext())
        {
            list.add(scan.nextLine().trim());
                    
        }
        
        String[] temp = new String[list.size()];
        
        for (int i = 0; i < list.size(); ++i)
        {
            temp[i] = list.get(i);
        }
        
        return temp;
    }
    
    private void populateWorldOrCountry (boolean isWorld)
    {
                if (!isWorld)
        {
            for (Map.Entry<String, String> entry : wfb.countryFactTypeList.entrySet()) {
            if (entry.getKey().equals((String) factTypeSelector.getSelectedItem())) {
                try {
                    factListArr = populateList(entry.getValue());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CIAWordFactbookUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                for (int i = 0; i < factListArr.length; ++i) {
                    factSelector.addItem(factListArr[i]);
                }
                listPopulated = true;
                factTypeSelected = true;
                break;
            }

        }
        }
        else
        {
            for (Map.Entry<String, String> entry : wfb.worldFactTypeList.entrySet()) {
            if (entry.getKey().equals((String) factTypeSelector.getSelectedItem())) {
                try {
                    factListArr = populateList(entry.getValue());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CIAWordFactbookUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                for (int i = 0; i < factListArr.length; ++i) {
                    factSelector.addItem(factListArr[i]);
                }
                listPopulated = true;
                factTypeSelected = true;
                break;
            }

        }
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JScrollPane factDisplay;
    private javax.swing.JTextArea factDisplayTextArea;
    private javax.swing.JComboBox factSelector;
    private javax.swing.JComboBox factTypeSelector;
    private javax.swing.JButton getFactButton;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    // End of variables declaration//GEN-END:variables
}
