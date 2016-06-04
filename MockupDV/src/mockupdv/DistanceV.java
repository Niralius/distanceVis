/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MichaelH
 */
package mockupdv;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.*;
import static mockupdv.matrixChooser.matrixD;
import mockupdv.xyzChooser;

public class DistanceV extends javax.swing.JFrame {
    
    //JPanel panel = new JPanel();
    
//    public Visualization vis = null;

    /**
     * Creates new form DistanceV
     */
    public DistanceV() {
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

        jFileChooser1 = new javax.swing.JFileChooser();
        statisticsFrame = new javax.swing.JFrame();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pearsonField = new javax.swing.JTextField();
        errorField = new javax.swing.JTextField();
        statisticsUpdate = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        visPanel = new Visualization();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        button2D = new javax.swing.JToggleButton();
        positionBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        statistics = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        addLabels = new javax.swing.JButton();
        addXY = new javax.swing.JButton();
        addMatrix = new javax.swing.JButton();
        split = new javax.swing.JCheckBox();
        splitBox = new javax.swing.JComboBox<>();
        filter = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        SVG = new javax.swing.JMenuItem();
        jpg = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        freeze = new javax.swing.JCheckBoxMenuItem();
        outline = new javax.swing.JCheckBoxMenuItem();

        jFileChooser1.setMultiSelectionEnabled(true);

        statisticsFrame.setTitle("Statistics");
        statisticsFrame.setBounds(new java.awt.Rectangle(150, 150, 530, 280));

        jLabel3.setText("Pearson:");

        jLabel4.setText("Average Error:");

        pearsonField.setEditable(false);
        pearsonField.setText("jTextField1");

        errorField.setEditable(false);
        errorField.setText("jTextField1");
        errorField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                errorFieldActionPerformed(evt);
            }
        });

        statisticsUpdate.setText("Update Statistics");
        statisticsUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticsUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout statisticsFrameLayout = new javax.swing.GroupLayout(statisticsFrame.getContentPane());
        statisticsFrame.getContentPane().setLayout(statisticsFrameLayout);
        statisticsFrameLayout.setHorizontalGroup(
            statisticsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statisticsFrameLayout.createSequentialGroup()
                .addGroup(statisticsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statisticsFrameLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(statisticsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(statisticsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pearsonField, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(statisticsFrameLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(statisticsUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        statisticsFrameLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {errorField, pearsonField});

        statisticsFrameLayout.setVerticalGroup(
            statisticsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statisticsFrameLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(statisticsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pearsonField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(statisticsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(errorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(statisticsUpdate)
                .addGap(51, 51, 51))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Distance Visualizer");

        visPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        javax.swing.GroupLayout visPanelLayout = new javax.swing.GroupLayout(visPanel);
        visPanel.setLayout(visPanelLayout);
        visPanelLayout.setHorizontalGroup(
            visPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );
        visPanelLayout.setVerticalGroup(
            visPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visPanelLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(409, Short.MAX_VALUE))
        );

        button2D.setText("2D");
        button2D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2DActionPerformed(evt);
            }
        });

        positionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Position Controller:");

        statistics.setText("Statistics");
        statistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticsActionPerformed(evt);
            }
        });

        jLabel2.setText("Labels:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        addLabels.setText("Add Labels");
        addLabels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLabelsActionPerformed(evt);
            }
        });

        addXY.setText("Add X/Y");
        addXY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addXYActionPerformed(evt);
            }
        });

        addMatrix.setText("Add Distance Matrix");
        addMatrix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMatrixActionPerformed(evt);
            }
        });

        split.setText("Split");
        split.setToolTipText("Separate the visualization, can only separate if there are only two labels.");
        split.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splitActionPerformed(evt);
            }
        });

        splitBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        splitBox.setToolTipText("Separate the visualization, can only separate if there are only two labels.");
        splitBox.setEnabled(false);

        filter.setText("Filter");

        jMenu1.setText("File");

        SVG.setText("Save as SVG");
        jMenu1.add(SVG);

        jpg.setText("Save as .jpg");
        jpg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpgActionPerformed(evt);
            }
        });
        jMenu1.add(jpg);

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jMenu1.add(Exit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        freeze.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, 0));
        freeze.setText("Freeze");
        freeze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                freezeActionPerformed(evt);
            }
        });
        jMenu2.add(freeze);

        outline.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, 0));
        outline.setText("Show Outline");
        jMenu2.add(outline);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(visPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addLabels, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addXY, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addMatrix, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(47, 47, 47))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(positionBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(split)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(splitBox, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(statistics)
                                    .addComponent(button2D)
                                    .addComponent(filter))
                                .addGap(49, 49, 49))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(visPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(positionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button2D)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statistics)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(split)
                    .addComponent(splitBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addLabels)
                    .addComponent(addXY)
                    .addComponent(addMatrix))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button2DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2DActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_button2DActionPerformed

    private void addXYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addXYActionPerformed
        jFileChooser1.showOpenDialog(this);
        File xyzPosition = jFileChooser1.getSelectedFile();
        try {
            xyzChooser position = new xyzChooser(xyzPosition);
            ((Visualization)visPanel).addXyz(position);
            ((Visualization)visPanel).repaint();
            //System.out.println(DistanceChooser.xpos.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_addXYActionPerformed

    private void statisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statisticsActionPerformed
        statisticsFrame.setVisible(true);
    }//GEN-LAST:event_statisticsActionPerformed

    private void freezeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_freezeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_freezeActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void jpgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpgActionPerformed

    private void addLabelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLabelsActionPerformed
        int labelsFile = jFileChooser1.showOpenDialog(this);
    }//GEN-LAST:event_addLabelsActionPerformed

    private void addMatrixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMatrixActionPerformed
        jFileChooser1.showOpenDialog(this);
        File matrixPos = jFileChooser1.getSelectedFile();
        try {
            matrixChooser position = new matrixChooser(matrixPos);
            //System.out.println(matrixChooser.matrixD.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_addMatrixActionPerformed

    private void statisticsUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statisticsUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statisticsUpdateActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void errorFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_errorFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_errorFieldActionPerformed

    private void splitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splitActionPerformed
        if (split.isSelected()){
            splitBox.setEnabled(true);
        } 
        if (!split.isSelected()){
            splitBox.setEnabled(false);
        }
           
    }//GEN-LAST:event_splitActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(DistanceV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DistanceV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DistanceV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DistanceV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DistanceV().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem SVG;
    private javax.swing.JButton addLabels;
    private javax.swing.JButton addMatrix;
    private javax.swing.JButton addXY;
    private javax.swing.JToggleButton button2D;
    private javax.swing.JTextField errorField;
    private javax.swing.JButton filter;
    private javax.swing.JCheckBoxMenuItem freeze;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem jpg;
    private javax.swing.JCheckBoxMenuItem outline;
    private javax.swing.JTextField pearsonField;
    private javax.swing.JComboBox<String> positionBox;
    private javax.swing.JCheckBox split;
    private javax.swing.JComboBox<String> splitBox;
    private javax.swing.JButton statistics;
    private javax.swing.JFrame statisticsFrame;
    private javax.swing.JButton statisticsUpdate;
    private javax.swing.JPanel visPanel;
    // End of variables declaration//GEN-END:variables
}
