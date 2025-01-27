//Author: SAM
//Transport Management System


package jframe;

import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;


public class HomePage extends javax.swing.JFrame {
    
    
    public HomePage() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTransportDetailsToTable();
        setDataToCards();
    }
    
    
    DefaultTableModel model;
    Color enterColor = new Color(0,0,0);
    Color exitColor = new Color(51,51,51);
    
    
    //method to set transport details to table
    public void setTransportDetailsToTable() {
        
        int transportID;
        String  driverName, loadPoint, deliveryPoint, packageName, status;
        
        try {
            
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from booking_details");
            
            while(rs.next()) {
                
                transportID = rs.getInt("Transport_ID");
                driverName = rs.getString("driver_name");
                loadPoint = rs.getString("loading_point");
                deliveryPoint = rs.getString("delivery_point");
                packageName = rs.getString("package");
                status = rs.getString("status");
                
                
                Object[] obj = {transportID, driverName, loadPoint, deliveryPoint, packageName, status};
                model =(DefaultTableModel) tbl_studentDetails.getModel();
                model.addRow(obj);
            }
            
            con.close();
            st.close();
            rs.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //method to set data to cards
    public void setDataToCards() {
        
        try {
            
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("select * from vehicle_details");
            rs.last();
            lbl_totalVehicles.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select * from vehicle_details WHERE  Availability = 0");
            rs.last();
            lbl_dispatchedVehicles.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select * from booking_details");
            rs.last();
            lbl_ordersDone.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select * from booking_details");
            rs.last();
            lbl_customerServed.setText(Integer.toString(rs.getRow()));
            
            con.close();
            st.close();
            rs.close();
            
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbl_minimize = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_close = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        panel_logout = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        panel_veiwBookedVehicles = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        panel_orderBook = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        panel_addNewAdmin = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        panel_aboutUs = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        panel_addNewDriver = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lbl_totalVehicles = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbl_dispatchedVehicles = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        lbl_ordersDone = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        lbl_customerServed = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/icons8_menu_48px_1.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 50));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 5, 50));

        lbl_minimize.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 48)); // NOI18N
        lbl_minimize.setForeground(new java.awt.Color(255, 255, 255));
        lbl_minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_minimize.setText("-");
        lbl_minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jPanel1.add(lbl_minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1790, 10, 60, 40));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Transportation Management System");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 420, 50));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/male_user_50px.png"))); // NOI18N
        jLabel4.setText("Welcome, Admin");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 10, 220, -1));

        lbl_close.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 30)); // NOI18N
        lbl_close.setForeground(new java.awt.Color(255, 255, 255));
        lbl_close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_close.setText("X");
        lbl_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jPanel1.add(lbl_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1850, 10, 60, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1930, 70));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 79, 141));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/icons8_Home_26px_2.png"))); // NOI18N
        jLabel5.setText("Home Page  ");
        jLabel5.setIconTextGap(6);
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 170, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 340, 60));

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

        jPanel3.add(panel_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 820, 340, 60));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("FEATURES ");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 110, -1));

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Dashbaord ");
        jLabel11.setIconTextGap(6);
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 210, -1));

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 340, 60));

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));
        jPanel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel10MouseExited(evt);
            }
        });
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/icons8-check-book-30.png"))); // NOI18N
        jLabel12.setText("Booking");
        jLabel12.setIconTextGap(6);
        jPanel10.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 220, -1));

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 340, 60));

        panel_veiwBookedVehicles.setBackground(new java.awt.Color(51, 51, 51));
        panel_veiwBookedVehicles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_veiwBookedVehicles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_veiwBookedVehiclesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_veiwBookedVehiclesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_veiwBookedVehiclesMouseExited(evt);
            }
        });
        panel_veiwBookedVehicles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/icons8-bookmark-32.png"))); // NOI18N
        jLabel13.setText("Booked Vehicles");
        jLabel13.setIconTextGap(5);
        panel_veiwBookedVehicles.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 200, -1));

        jPanel3.add(panel_veiwBookedVehicles, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 340, 60));

        panel_orderBook.setBackground(new java.awt.Color(51, 51, 51));
        panel_orderBook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_orderBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_orderBookMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_orderBookMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_orderBookMouseExited(evt);
            }
        });
        panel_orderBook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/icons8-diary-24.png"))); // NOI18N
        jLabel14.setText("Order Book");
        jLabel14.setIconTextGap(6);
        panel_orderBook.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 220, 30));

        jPanel3.add(panel_orderBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 340, 60));

        panel_addNewAdmin.setBackground(new java.awt.Color(51, 51, 51));
        panel_addNewAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_addNewAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_addNewAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_addNewAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_addNewAdminMouseExited(evt);
            }
        });
        panel_addNewAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/icons8-add-user-male-32.png"))); // NOI18N
        jLabel16.setText("Add New Admin");
        jLabel16.setIconTextGap(5);
        panel_addNewAdmin.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 220, -1));

        jPanel3.add(panel_addNewAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 340, 60));

        panel_aboutUs.setBackground(new java.awt.Color(51, 51, 51));
        panel_aboutUs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_aboutUs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_aboutUsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_aboutUsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_aboutUsMouseExited(evt);
            }
        });
        panel_aboutUs.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/icons8-about-24.png"))); // NOI18N
        jLabel17.setText("About us");
        jLabel17.setIconTextGap(7);
        panel_aboutUs.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 220, 30));

        jPanel3.add(panel_aboutUs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 340, 60));

        panel_addNewDriver.setBackground(new java.awt.Color(51, 51, 51));
        panel_addNewDriver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_addNewDriver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_addNewDriverMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_addNewDriverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_addNewDriverMouseExited(evt);
            }
        });
        panel_addNewDriver.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/icons8-steering-wheel-32.png"))); // NOI18N
        jLabel15.setText("Add New driver");
        panel_addNewDriver.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 220, -1));

        jPanel3.add(panel_addNewDriver, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 340, 60));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 340, 1010));

        jPanel6.setBackground(new java.awt.Color(245, 245, 245));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(0, 79, 141)));

        lbl_totalVehicles.setFont(new java.awt.Font("Segoe UI Black", 0, 50)); // NOI18N
        lbl_totalVehicles.setForeground(new java.awt.Color(102, 102, 102));
        lbl_totalVehicles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_totalVehicles.setText("10");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lbl_totalVehicles, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbl_totalVehicles, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 260, 140));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Transport Details");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 180, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Despatched Vehicles");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 200, -1));

        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(0, 79, 141)));

        lbl_dispatchedVehicles.setFont(new java.awt.Font("Segoe UI Black", 0, 50)); // NOI18N
        lbl_dispatchedVehicles.setForeground(new java.awt.Color(102, 102, 102));
        lbl_dispatchedVehicles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_dispatchedVehicles.setText("10");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lbl_dispatchedVehicles, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbl_dispatchedVehicles, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 260, 140));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Total Orders Done");
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, 180, -1));

        jPanel17.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(0, 79, 141)));

        lbl_ordersDone.setFont(new java.awt.Font("Segoe UI Black", 0, 50)); // NOI18N
        lbl_ordersDone.setForeground(new java.awt.Color(102, 102, 102));
        lbl_ordersDone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ordersDone.setText("10");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lbl_ordersDone, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbl_ordersDone, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, 260, 140));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Customers Served");
        jPanel6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 40, 180, -1));

        jPanel18.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(0, 79, 141)));

        lbl_customerServed.setFont(new java.awt.Font("Segoe UI Black", 0, 50)); // NOI18N
        lbl_customerServed.setForeground(new java.awt.Color(102, 102, 102));
        lbl_customerServed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_customerServed.setText("10");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lbl_customerServed, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbl_customerServed, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 80, 260, 140));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("Total Vehicles");
        jPanel6.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 160, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/Free Vector _ Transportation merchandise logistic cargo cartoon.jpeg"))); // NOI18N
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 390, 590, 490));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 1590, 1010));

        setSize(new java.awt.Dimension(1931, 1081));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lbl_minimizeMouseClicked

    private void lbl_minimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseEntered
        lbl_minimize.setForeground(Color.GRAY);
    }//GEN-LAST:event_lbl_minimizeMouseEntered

    private void lbl_minimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseExited
        lbl_minimize.setForeground(Color.WHITE);
    }//GEN-LAST:event_lbl_minimizeMouseExited

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        Booking booking = new Booking();
        booking.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseEntered
        jPanel10.setBackground(enterColor);
    }//GEN-LAST:event_jPanel10MouseEntered

    private void jPanel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseExited
        jPanel10.setBackground(exitColor);
    }//GEN-LAST:event_jPanel10MouseExited

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

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Image img = new ImageIcon(this.getClass().getResource("/jframe/icons/service1.jpg")).getImage();
        this.setIconImage(img);
    }//GEN-LAST:event_formWindowActivated

    private void panel_veiwBookedVehiclesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_veiwBookedVehiclesMouseClicked
        ViewBookedVehicles veiwBookedVehicles = new ViewBookedVehicles();
        veiwBookedVehicles.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panel_veiwBookedVehiclesMouseClicked

    private void panel_veiwBookedVehiclesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_veiwBookedVehiclesMouseEntered
        panel_veiwBookedVehicles.setBackground(enterColor);
    }//GEN-LAST:event_panel_veiwBookedVehiclesMouseEntered

    private void panel_veiwBookedVehiclesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_veiwBookedVehiclesMouseExited
        panel_veiwBookedVehicles.setBackground(exitColor);
    }//GEN-LAST:event_panel_veiwBookedVehiclesMouseExited

    private void panel_orderBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_orderBookMouseClicked
        OrderBook orderBook = new OrderBook();
        orderBook.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panel_orderBookMouseClicked

    private void panel_orderBookMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_orderBookMouseEntered
        panel_orderBook.setBackground(enterColor);
    }//GEN-LAST:event_panel_orderBookMouseEntered

    private void panel_orderBookMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_orderBookMouseExited
        panel_orderBook.setBackground(exitColor);
    }//GEN-LAST:event_panel_orderBookMouseExited

    private void lbl_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lbl_closeMouseClicked

    private void lbl_closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseEntered
        lbl_close.setForeground(Color.GRAY);
    }//GEN-LAST:event_lbl_closeMouseEntered

    private void lbl_closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseExited
        lbl_close.setForeground(Color.WHITE);
    }//GEN-LAST:event_lbl_closeMouseExited

    private void panel_addNewDriverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_addNewDriverMouseClicked
        AddNewDriver addNewDriver = new AddNewDriver();
        addNewDriver.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panel_addNewDriverMouseClicked

    private void panel_addNewDriverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_addNewDriverMouseEntered
        panel_addNewDriver.setBackground(enterColor);
    }//GEN-LAST:event_panel_addNewDriverMouseEntered

    private void panel_addNewDriverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_addNewDriverMouseExited
        panel_addNewDriver.setBackground(exitColor);
    }//GEN-LAST:event_panel_addNewDriverMouseExited

    private void panel_addNewAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_addNewAdminMouseClicked
        AddNewAdmin addNewUser = new AddNewAdmin();
        addNewUser.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panel_addNewAdminMouseClicked

    private void panel_addNewAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_addNewAdminMouseEntered
        panel_addNewAdmin.setBackground(enterColor);
    }//GEN-LAST:event_panel_addNewAdminMouseEntered

    private void panel_addNewAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_addNewAdminMouseExited
        panel_addNewAdmin.setBackground(exitColor);
    }//GEN-LAST:event_panel_addNewAdminMouseExited

    private void panel_aboutUsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_aboutUsMouseClicked
        AboutUs about = new AboutUs();
        about.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_panel_aboutUsMouseClicked

    private void panel_aboutUsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_aboutUsMouseEntered
        panel_aboutUs.setBackground(enterColor);
    }//GEN-LAST:event_panel_aboutUsMouseEntered

    private void panel_aboutUsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_aboutUsMouseExited
        panel_aboutUs.setBackground(exitColor);
    }//GEN-LAST:event_panel_aboutUsMouseExited

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
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_customerServed;
    private javax.swing.JLabel lbl_dispatchedVehicles;
    private javax.swing.JLabel lbl_minimize;
    private javax.swing.JLabel lbl_ordersDone;
    private javax.swing.JLabel lbl_totalVehicles;
    private javax.swing.JPanel panel_aboutUs;
    private javax.swing.JPanel panel_addNewAdmin;
    private javax.swing.JPanel panel_addNewDriver;
    private javax.swing.JPanel panel_logout;
    private javax.swing.JPanel panel_orderBook;
    private javax.swing.JPanel panel_veiwBookedVehicles;
    // End of variables declaration//GEN-END:variables
}
