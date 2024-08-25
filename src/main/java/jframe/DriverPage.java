//Author: SAM
//Transport Management System


package jframe;

import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class DriverPage extends javax.swing.JFrame {

    /**
     * Creates new form ViewRecords
     */
    public DriverPage(String name) {
        initComponents();
        String d_name = name;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        lbl_userName.setText("Welcome, " + d_name);
        setDataToLabels(d_name);
        searchRecords();
        
    }
    
    //method to clear table
    public void clearTable() {
        DefaultTableModel tModel = (DefaultTableModel) tbl_records.getModel();
        tModel.setRowCount(0);
    }
    
    //method to put data in labels
    public void setDataToLabels(String d_name) {
        
        lbl_userName1.setText(d_name);
        
        int vehicleID;
        String numberPlate, currentLocation, contact;
        boolean available;
        
        try {
            
            Connection con = DBConnection.getConnection();
            String str = "select * from vehicle_details WHERE DriverName = ?";
            PreparedStatement pst = con.prepareStatement(str);
            pst.setString(1,d_name);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {
                vehicleID = rs.getInt("ID");
                contact = rs.getString("contact");
                numberPlate = rs.getString("NumberPlate");
                currentLocation = rs.getString("CurrentLocation");
                available = rs.getBoolean("Availability");
                
                lbl_vehicleId.setText(String.valueOf(vehicleID));
                lbl_userContact.setText(contact);
                lbl_numberPlate.setText(numberPlate);
                lbl_currentLocation.setText(currentLocation);
                
                if(available == true)
                    checkBox_available.setSelected(true);
                else
                {
                    
                }
            }
            
            con.close();
            pst.close();
            rs.close();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        } 
    }
    
    //method to search records on specified dates
    public void searchRecords() {
        
        clearTable();
        DefaultTableModel model;
        
        String name = lbl_userName1.getText();
        int transportID, weight;
        double price;
        String customerName, loadPoint, deliveryPoint, packageName, product, status, loadDate, deliveryDate;
        
        try {
            
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String str = "select * from booking_details WHERE driver_name = ?";
            PreparedStatement pst = con.prepareStatement(str);
            pst.setString(1, name);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                
                transportID = rs.getInt("Transport_ID");
                customerName = rs.getString("customer_name");
                loadPoint = rs.getString("loading_point");
                deliveryPoint = rs.getString("delivery_point");
                packageName = rs.getString("package");
                product = rs.getString("product");
                weight = rs.getInt("weight");
                loadDate = rs.getString("load_date");
                deliveryDate = rs.getString("delivery_date");
                price = rs.getDouble("price");
                status = rs.getString("status");
                
                
                Object[] obj = {transportID, customerName, loadPoint, deliveryPoint, packageName, product, weight, loadDate, deliveryDate, price, status};
                model =(DefaultTableModel) tbl_records.getModel();
                model.addRow(obj);
            }            
            
            
            con.close();
            st.close();
            rs.close();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    
    public boolean changeAvailabilityStatus() {
        
        int vehicleID = Integer.parseInt(lbl_vehicleId.getText());
        boolean av = checkBox_available.isEnabled();
        
        try {
            
            Connection con = DBConnection.getConnection();
            
            //changing "Availability" status in "vehicle_details"
            String str = "UPDATE vehicle_details SET Availability = ? WHERE ID = ?";
            PreparedStatement pst = con.prepareStatement(str);
            pst.setBoolean(1,av);
            pst.setInt(2,vehicleID);
            pst.executeUpdate();
            
            //changing "ongoing" status in "booking_details"
            str = "UPDATE booking_details SET status = ? WHERE Vehicle_ID = ? AND status = ?";
            pst = con.prepareStatement(str);
            pst.setString(1,"Completed");
            pst.setInt(2,vehicleID);
            pst.setString(3,"Ongoing");
            pst.executeUpdate();
            
            con.close();
            pst.close();
            
            clearTable();
            searchRecords();
            
            return true;
            
        } catch (Exception e) {
            
            e.printStackTrace();
            return false;
        }
        
    }
    
    
    public boolean changeCurrentLocation() {
        
        int vehicleID = Integer.parseInt(lbl_vehicleId.getText());
        String currentLocation = combo_currentLocation.getSelectedItem().toString();
        
        try {
            
            Connection con = DBConnection.getConnection();
            String str = "UPDATE vehicle_details SET CurrentLocation = ? WHERE ID = ?";
            PreparedStatement pst = con.prepareStatement(str);
            pst.setString(1,currentLocation);
            pst.setInt(2,vehicleID);
            
            pst.executeUpdate();
            
            con.close();
            pst.close();
            
            lbl_currentLocation.setText(currentLocation);
            
            return true;
            
        } catch (Exception e) {
            
            e.printStackTrace();
            return false;
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
        lbl_minimize = new javax.swing.JLabel();
        lbl_close = new javax.swing.JLabel();
        lbl_userName = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        panel_logout = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbl_vehicleId = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbl_userName1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbl_numberPlate = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbl_currentLocation = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        combo_currentLocation = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_userContact = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        checkBox_available = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(lbl_minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1800, 10, 60, 40));

        lbl_close.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 30)); // NOI18N
        lbl_close.setForeground(new java.awt.Color(255, 255, 255));
        lbl_close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_close.setText("X");
        lbl_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_close.setPreferredSize(new java.awt.Dimension(1920, 1324));
        lbl_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_closeMouseExited(evt);
            }
        });
        jPanel1.add(lbl_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1860, 10, 50, 50));

        lbl_userName.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_userName.setForeground(new java.awt.Color(255, 255, 255));
        lbl_userName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/male_user_50px.png"))); // NOI18N
        lbl_userName.setText("Welcome, ");
        lbl_userName.setPreferredSize(new java.awt.Dimension(1920, 1324));
        jPanel1.add(lbl_userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(1520, 10, 250, 50));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 10, 280, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/icons8_menu_48px_1.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 50));

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 5, 50));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Transportation Management System");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 430, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 90));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_logout.setBackground(new java.awt.Color(255, 189, 165));
        panel_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_logoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_logoutMouseExited(evt);
            }
        });
        panel_logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/icons8_Exit_26px_2.png"))); // NOI18N
        jLabel7.setText("Logout");
        panel_logout.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 220, -1));

        jPanel4.add(panel_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 760, 380, 60));

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 25)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Dashbaord  ");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 210, 40));

        jPanel4.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 90));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Vehicle ID:");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 110, 40));

        lbl_vehicleId.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_vehicleId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_vehicleId, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 180, 40));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("User Name:");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 120, 40));

        lbl_userName1.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_userName1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_userName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 180, 40));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Number Plate:");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 140, 40));

        lbl_numberPlate.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_numberPlate.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_numberPlate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 180, 40));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Current Location:");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 160, 40));

        lbl_currentLocation.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_currentLocation.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_currentLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 490, 160, 40));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Details:");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 110, 40));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 260, 5));

        combo_currentLocation.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        combo_currentLocation.setForeground(new java.awt.Color(102, 102, 102));
        combo_currentLocation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jalgaon", "Pune", "Mumbai", "Aurangabad", "Nashik", "Bhusawal" }));
        jPanel4.add(combo_currentLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 640, 190, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Current Location:");
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, 170, 30));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 260, 5));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Contact:");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 110, 40));

        lbl_userContact.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_userContact.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_userContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 180, 40));

        jButton1.setFont(new java.awt.Font("Yu Gothic Light", 1, 15)); // NOI18N
        jButton1.setText("Edit Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 140, 40));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 380, 1240));

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        checkBox_available.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        checkBox_available.setText("Available");
        checkBox_available.setIconTextGap(10);
        checkBox_available.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_availableActionPerformed(evt);
            }
        });
        jPanel2.add(checkBox_available, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 170, 70));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 1570, 1240));

        setSize(new java.awt.Dimension(1920, 1324));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_recordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_recordsMouseClicked
        
    }//GEN-LAST:event_tbl_recordsMouseClicked

    private void lbl_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lbl_minimizeMouseClicked

    private void lbl_minimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseEntered
        lbl_minimize.setForeground(Color.GRAY);
    }//GEN-LAST:event_lbl_minimizeMouseEntered

    private void lbl_minimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseExited
        lbl_minimize.setForeground(Color.WHITE);
    }//GEN-LAST:event_lbl_minimizeMouseExited

    private void lbl_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lbl_closeMouseClicked

    private void lbl_closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseEntered
        lbl_close.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_lbl_closeMouseEntered

    private void lbl_closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseExited
        lbl_close.setForeground(Color.WHITE);
    }//GEN-LAST:event_lbl_closeMouseExited

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Image img = new ImageIcon(this.getClass().getResource("/jframe/icons/service1.jpg")).getImage();
        this.setIconImage(img);
    }//GEN-LAST:event_formWindowActivated

    private void panel_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_logoutMouseClicked
        LoginPage login = new LoginPage();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panel_logoutMouseClicked

    private void panel_logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_logoutMouseEntered
        Color ent = new Color(255,51,51);
        panel_logout.setBackground(ent);
    }//GEN-LAST:event_panel_logoutMouseEntered

    private void panel_logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_logoutMouseExited
        Color ext = new Color(255,189,165);
        panel_logout.setBackground(ext);
    }//GEN-LAST:event_panel_logoutMouseExited

    private void rSMaterialButtonCircle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle4ActionPerformed
            
        if(changeCurrentLocation())
            JOptionPane.showMessageDialog(this, "Current Location changed.");
        else
            JOptionPane.showMessageDialog(this, "Current Location cannot be changed.");
        
    }//GEN-LAST:event_rSMaterialButtonCircle4ActionPerformed

    private void checkBox_availableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_availableActionPerformed
        
        if(changeAvailabilityStatus())
            JOptionPane.showMessageDialog(this, "Available status changed.");
        else
            JOptionPane.showMessageDialog(this, "Available status cannot be changed.");
        
    }//GEN-LAST:event_checkBox_availableActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String name = lbl_userName1.getText();
        editDriverDetails editDetails = new editDriverDetails(name);
        editDetails.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(DriverPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DriverPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DriverPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DriverPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DriverPage(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBox_available;
    private javax.swing.JComboBox<String> combo_currentLocation;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_currentLocation;
    private javax.swing.JLabel lbl_minimize;
    private javax.swing.JLabel lbl_numberPlate;
    private javax.swing.JLabel lbl_userContact;
    private javax.swing.JLabel lbl_userName;
    private javax.swing.JLabel lbl_userName1;
    private javax.swing.JLabel lbl_vehicleId;
    private javax.swing.JPanel panel_logout;
    // End of variables declaration//GEN-END:variables
}
