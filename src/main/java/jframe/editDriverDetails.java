//Author: SAM
//Transport Management System


package jframe;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class editDriverDetails extends javax.swing.JFrame {

    /**
     * Creates new form AboutUs
     */
    public editDriverDetails(String name) {
        initComponents();
        putInfo(name);
    }
    
    //Method to put information into textboxes
    public void putInfo(String name) {
        
        try {
            
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from vehicle_details where DriverName = ?");
            
            pst.setString(1, name);
            
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                
                txt_username.setText(name);
                txt_password.setText(rs.getString("password"));
                txt_contact.setText(rs.getString("contact"));
                txt_cost.setText(String.valueOf(rs.getDouble("cost_perKm")));
                txt_loadCapacity.setText(String.valueOf(rs.getInt("LoadCapacity")));
            }
        
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
    }
    
    public boolean editDetails() {
        
        boolean isEdited = false;
        
        String name = txt_username.getText();
        String pwd = txt_password.getText();
        String contact = txt_contact.getText();
        double cost = Double.parseDouble(txt_cost.getText());
        int load = Integer.parseInt(txt_loadCapacity.getText());
        
        if(name.equals("") || pwd.equals("") || contact.equals("") || cost == 0 || load == 0)
        {
            JOptionPane.showMessageDialog(this, "Please enter all feilds.");
            return false;
        }
        
        try {
            
            Connection con = DBConnection.getConnection();
            String str = "UPDATE vehicle_details SET password = ? and contact = ? and cost_perKm = ? and LoadCapacity = ? WHERE DriverName = ?";
            PreparedStatement pst = con.prepareStatement(str);
            pst.setString(1,pwd);
            pst.setString(2,contact);
            pst.setDouble(3,cost);
            pst.setInt(4,load);
            pst.setString(5,name);
            
            pst.execute();
            
            con.close();
            pst.close();
            
            
            return isEdited;
            
        } catch (Exception e) {
            
            e.printStackTrace();
            return isEdited;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn_login = new rojerusan.RSMaterialButtonCircle();
        txt_username = new app.bolivia.swing.JCTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_password = new app.bolivia.swing.JCTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_loadCapacity = new app.bolivia.swing.JCTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_cost = new app.bolivia.swing.JCTextField();
        jLabel27 = new javax.swing.JLabel();
        txt_contact = new app.bolivia.swing.JCTextField();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setUndecorated(true);
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(255, 255, 255), new java.awt.Color(102, 102, 102), new java.awt.Color(204, 204, 204)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Edit Details");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 160, 60));

        btn_login.setBackground(new java.awt.Color(204, 204, 255));
        btn_login.setText("Edit");
        btn_login.setFont(new java.awt.Font("Roboto Medium", 1, 17)); // NOI18N
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        jPanel1.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 520, 110, 80));

        txt_username.setBackground(new java.awt.Color(0, 51, 102));
        txt_username.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txt_username.setForeground(new java.awt.Color(255, 255, 255));
        txt_username.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_username.setEnabled(false);
        txt_username.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_username.setPhColor(new java.awt.Color(255, 255, 255));
        txt_username.setSelectionColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 270, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Name:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 100, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Password:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 100, 30));

        txt_password.setBackground(new java.awt.Color(0, 51, 102));
        txt_password.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txt_password.setForeground(new java.awt.Color(255, 255, 255));
        txt_password.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_password.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_password.setPhColor(new java.awt.Color(255, 255, 255));
        txt_password.setSelectionColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 270, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Load Capacity:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 160, 30));

        txt_loadCapacity.setBackground(new java.awt.Color(0, 51, 102));
        txt_loadCapacity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txt_loadCapacity.setForeground(new java.awt.Color(255, 255, 255));
        txt_loadCapacity.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_loadCapacity.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_loadCapacity.setPhColor(new java.awt.Color(255, 255, 255));
        txt_loadCapacity.setSelectionColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txt_loadCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 270, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Cost (per km): ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 150, 30));

        txt_cost.setBackground(new java.awt.Color(0, 51, 102));
        txt_cost.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txt_cost.setForeground(new java.awt.Color(255, 255, 255));
        txt_cost.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_cost.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_cost.setPhColor(new java.awt.Color(255, 255, 255));
        txt_cost.setSelectionColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txt_cost, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 270, 40));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Contact:");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 100, 30));

        txt_contact.setBackground(new java.awt.Color(0, 51, 102));
        txt_contact.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txt_contact.setForeground(new java.awt.Color(255, 255, 255));
        txt_contact.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_contact.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_contact.setPhColor(new java.awt.Color(255, 255, 255));
        txt_contact.setSelectionColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txt_contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 270, 40));

        jLabel15.setFont(new java.awt.Font("Swis721 WGL4 BT", 1, 30)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("X");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
        });
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 40, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 650));

        setSize(new java.awt.Dimension(638, 650));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
//        if(editDetails())
//        {
            JOptionPane.showMessageDialog(this, "Edit complete.");
            this.dispose();
//        }
//        else
//            JOptionPane.showMessageDialog(this, "Cannot edit at the time.");
    }//GEN-LAST:event_btn_loginActionPerformed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        jLabel15.setForeground(Color.GRAY);
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited
        jLabel15.setForeground(Color.WHITE);
    }//GEN-LAST:event_jLabel15MouseExited

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
            java.util.logging.Logger.getLogger(editDriverDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editDriverDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editDriverDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editDriverDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editDriverDetails(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonCircle btn_login;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private app.bolivia.swing.JCTextField txt_contact;
    private app.bolivia.swing.JCTextField txt_cost;
    private app.bolivia.swing.JCTextField txt_loadCapacity;
    private app.bolivia.swing.JCTextField txt_password;
    private app.bolivia.swing.JCTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
