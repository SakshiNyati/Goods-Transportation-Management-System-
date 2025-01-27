//Author: SAM
//Transport Management System


package jframe;
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class LoginPage extends javax.swing.JFrame {
    
    
    public LoginPage() {
        initComponents();
    }
    
    //validation
    public boolean validateLogin() {
        String name = txt_username.getText();
        String pwd = txt_password.getText();
        
        if(name.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter Username");
            return false;
        }
        
        if(pwd.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter Password");
            return false;
        }
        
        return true;
    }
    
    //Method to login process for Admin
    public void loginAdmin() {
        
        String name = txt_username.getText();
        String pwd = txt_password.getText();
        
        try {
            
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from users where name = ? and password = ?");
            
            pst.setString(1, name);
            pst.setString(2, pwd);
            
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                HomePage home = new HomePage();
                home.setVisible(true);
                this.dispose();                
            }
            else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    //Method to login process for Driver
    public void loginDriver() {
        
        String name = txt_username.getText();
        String pwd = txt_password.getText();
        
        try {
            
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from vehicle_details where DriverName = ? and password = ?");
            
            pst.setString(1, name);
            pst.setString(2, pwd);
            
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                
                DriverPage driverPage = new DriverPage(name);
                driverPage.setVisible(true);
                this.dispose();                
            }
            else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
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

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txt_username = new app.bolivia.swing.JCTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btn_login = new rojerusan.RSMaterialButtonCircle();
        lbl_minimize = new javax.swing.JLabel();
        cb_user = new javax.swing.JComboBox<>();
        txt_password = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        signup_image = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/icons8_Secure_50px.png"))); // NOI18N
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 50, 50));

        txt_username.setBackground(new java.awt.Color(0, 51, 102));
        txt_username.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txt_username.setForeground(new java.awt.Color(255, 255, 255));
        txt_username.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_username.setPhColor(new java.awt.Color(255, 255, 255));
        txt_username.setPlaceholder("Enter Username..");
        txt_username.setSelectionColor(new java.awt.Color(255, 255, 255));
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 260, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Username:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 100, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 100, 30));

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
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 40, 40));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/icons8_Account_50px.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 50, 50));

        jLabel20.setFont(new java.awt.Font("Swis721 LtEx BT", 1, 25)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Login Page");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 190, 50));

        btn_login.setBackground(new java.awt.Color(255, 51, 51));
        btn_login.setText("Login");
        btn_login.setFont(new java.awt.Font("Roboto Medium", 1, 17)); // NOI18N
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        btn_login.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_loginKeyPressed(evt);
            }
        });
        jPanel1.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 250, 70));

        lbl_minimize.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 48)); // NOI18N
        lbl_minimize.setForeground(new java.awt.Color(255, 255, 255));
        lbl_minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_minimize.setText("-");
        lbl_minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_minimize.setPreferredSize(new java.awt.Dimension(1920, 1324));
        lbl_minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_minimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_minimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_minimizeMouseExited(evt);
            }
        });
        jPanel1.add(lbl_minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 60, 40));

        cb_user.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cb_user.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Driver" }));
        jPanel1.add(cb_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 180, 40));

        txt_password.setBackground(new java.awt.Color(0, 51, 102));
        txt_password.setForeground(new java.awt.Color(255, 255, 255));
        txt_password.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txt_password.setSelectionColor(new java.awt.Color(255, 255, 255));
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        jPanel1.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 270, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 540, 570));

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setBackground(new java.awt.Color(54, 48, 98));
        jLabel12.setFont(new java.awt.Font("Sitka Text", 0, 30)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
        jLabel12.setText("Management System");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 300, 50));

        signup_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/Untitled-1.png"))); // NOI18N
        jPanel2.add(signup_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 400, 290));

        jLabel14.setBackground(new java.awt.Color(54, 48, 98));
        jLabel14.setFont(new java.awt.Font("Sitka Text", 0, 30)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setText("Transportation");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 220, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 570));

        setSize(new java.awt.Dimension(985, 568));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        jLabel15.setForeground(Color.GRAY);
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited
        jLabel15.setForeground(Color.WHITE);
    }//GEN-LAST:event_jLabel15MouseExited

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        if(validateLogin())
        {    
            if(cb_user.getSelectedItem().toString().equals("Admin"))
                loginAdmin();
            else
                loginDriver();
        }   
    }//GEN-LAST:event_btn_loginActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Image img = new ImageIcon(this.getClass().getResource("/jframe/icons/service1.jpg")).getImage();
        this.setIconImage(img);
    }//GEN-LAST:event_formWindowActivated

    private void lbl_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lbl_minimizeMouseClicked

    private void lbl_minimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseEntered
        lbl_minimize.setForeground(Color.GRAY);
    }//GEN-LAST:event_lbl_minimizeMouseEntered

    private void lbl_minimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseExited
        lbl_minimize.setForeground(Color.WHITE);
    }//GEN-LAST:event_lbl_minimizeMouseExited

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void btn_loginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_loginKeyPressed
        int key = evt.getKeyCode();
        if(key == 10)
        {
            if(validateLogin())
            {    
                if(cb_user.getSelectedItem().toString().equals("Admin"))
                    loginAdmin();
                else
                    loginDriver();
            }
        }
            
    }//GEN-LAST:event_btn_loginKeyPressed

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
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LoginPage().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonCircle btn_login;
    private javax.swing.JComboBox<String> cb_user;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_minimize;
    private javax.swing.JLabel signup_image;
    private javax.swing.JPasswordField txt_password;
    public app.bolivia.swing.JCTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
