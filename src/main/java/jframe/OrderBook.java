//Author: SAM
//Transport Management System


package jframe;

import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class OrderBook extends javax.swing.JFrame {

    
    public OrderBook() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBookingDetailsToTable();
    }
    
    //method to set booking details to table
    public void setBookingDetailsToTable() {
        
        clearTable();
        DefaultTableModel model;
        
        int transportID, vehicleID, weight;
        double price;
        String driverName, customerName, loadPoint, deliveryPoint, packageName, product, status, loadDate, deliveryDate;
        
        try {
            
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from booking_details");
            
            while(rs.next()) {
                
                transportID = rs.getInt("Transport_ID");
                vehicleID = rs.getInt("Vehicle_ID");
                driverName = rs.getString("driver_name");
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
                
                
                Object[] obj = {transportID, vehicleID, driverName, customerName, loadPoint, deliveryPoint, packageName, product, weight, loadDate, deliveryDate, price, status};
                model =(DefaultTableModel) tbl_veiwRecords.getModel();
                model.addRow(obj);
            }            
            
            
            con.close();
            st.close();
            rs.close();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    //method to clear table
    public void clearTable() {
        DefaultTableModel tModel = (DefaultTableModel) tbl_veiwRecords.getModel();
        tModel.setRowCount(0);
    }
    
    //method to search records on specified dates
    public void searchRecords() {
        
        clearTable();
        DefaultTableModel model;
        
        int transportID, vehicleID, weight;
        double price;
        String driverName, customerName, loadPoint1, loadPoint2, packageName, product, status, loadDate, deliveryDate;
        
        Date uloadDate = cal_fromDate.getDatoFecha();
        Date udeliveryDate = cal_toDate.getDatoFecha();
        
        Long t1 = uloadDate.getTime();
        Long t2 = udeliveryDate.getTime();
        
        java.sql.Date sloadDate = new java.sql.Date(t1);
        java.sql.Date sdeliveryDate = new java.sql.Date(t2);
        
        try {
            
            Connection con = DBConnection.getConnection();
            String str = "select * from booking_details where load_date BETWEEN ? and ?";
            PreparedStatement pst = con.prepareStatement(str);
            pst.setDate(1,sloadDate);
            pst.setDate(2,sdeliveryDate);
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                
                transportID = rs.getInt("Transport_ID");
                vehicleID = rs.getInt("Vehicle_ID");
                driverName = rs.getString("driver_name");
                customerName = rs.getString("customer_name");
                loadPoint1 = rs.getString("loading_point");
                loadPoint2 = rs.getString("loading_point");
                packageName = rs.getString("package");
                product = rs.getString("product");
                weight = rs.getInt("weight");
                loadDate = rs.getString("load_date");
                deliveryDate = rs.getString("delivery_date");
                price = rs.getDouble("price");
                status = rs.getString("status");
                
                
                Object[] obj = {transportID, vehicleID, driverName, customerName, loadPoint1, loadPoint2, packageName, product, weight, loadDate, deliveryDate, price, status};
                model =(DefaultTableModel) tbl_veiwRecords.getModel();
                model.addRow(obj);
            }            
            
            
            con.close();
            pst.close();
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
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cal_fromDate = new rojeru_san.componentes.RSDateChooser();
        cal_toDate = new rojeru_san.componentes.RSDateChooser();
        jLabel17 = new javax.swing.JLabel();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbl_minimize = new javax.swing.JLabel();
        lbl_close = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_veiwRecords = new rojeru_san.complementos.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/icons8-back-arrow-64.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 70));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("View Records");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, 260, 70));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 110, 280, 5));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("From Date:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 100, 40));

        cal_fromDate.setColorBackground(new java.awt.Color(0, 79, 141));
        cal_fromDate.setColorButtonHover(new java.awt.Color(0, 79, 141));
        cal_fromDate.setColorForeground(new java.awt.Color(51, 51, 51));
        cal_fromDate.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cal_fromDate.setFormatoFecha("dd/MM/yyyy");
        cal_fromDate.setPlaceholder("Select issue date..");
        jPanel1.add(cal_fromDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, 290, -1));

        cal_toDate.setColorBackground(new java.awt.Color(0, 79, 141));
        cal_toDate.setColorButtonHover(new java.awt.Color(0, 79, 141));
        cal_toDate.setColorForeground(new java.awt.Color(51, 51, 51));
        cal_toDate.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cal_toDate.setFormatoFecha("dd/MM/yyyy");
        cal_toDate.setPlaceholder("Select due date..");
        jPanel1.add(cal_toDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 190, 290, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("To Date:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 190, 130, 40));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(0, 79, 141));
        rSMaterialButtonCircle2.setText("Search");
        rSMaterialButtonCircle2.setFont(new java.awt.Font("Roboto Medium", 1, 17)); // NOI18N
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 170, 180, 70));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(0, 79, 141));
        rSMaterialButtonCircle3.setText("View All");
        rSMaterialButtonCircle3.setFont(new java.awt.Font("Roboto Medium", 1, 17)); // NOI18N
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 170, 180, 70));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/male_user_50px.png"))); // NOI18N
        jLabel4.setText("Admin");
        jLabel4.setPreferredSize(new java.awt.Dimension(1920, 1324));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1640, 10, 130, 50));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1620, 10, 160, 50));

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1930, 280));

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_veiwRecords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Vehicle ID", "Driver Name", "Customer Name", "From", "To", "Package", "Product", "Weight", "Load Date", "Delivery Date", "Price", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_veiwRecords.setColorBackgoundHead(new java.awt.Color(0, 79, 141));
        tbl_veiwRecords.setColorBordeFilas(new java.awt.Color(115, 95, 141));
        tbl_veiwRecords.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_veiwRecords.setColorSelBackgound(new java.awt.Color(233, 213, 202));
        tbl_veiwRecords.setFillsViewportHeight(true);
        tbl_veiwRecords.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tbl_veiwRecords.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tbl_veiwRecords.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tbl_veiwRecords.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tbl_veiwRecords.setName(""); // NOI18N
        tbl_veiwRecords.setRowHeight(40);
        tbl_veiwRecords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_veiwRecordsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_veiwRecords);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 1830, 670));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 1930, 800));

        setSize(new java.awt.Dimension(1930, 1080));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        
        if(cal_fromDate.getDatoFecha()!=null && cal_toDate.getDatoFecha()!=null)
            searchRecords();
        else
            JOptionPane.showMessageDialog(this, "Please enter dates.");
        
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        
        cal_fromDate.setDatoFecha(null);
        cal_toDate.setDatoFecha(null);
        setBookingDetailsToTable();
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Image img = new ImageIcon(this.getClass().getResource("/jframe/icons/service1.jpg")).getImage();
        this.setIconImage(img);
    }//GEN-LAST:event_formWindowActivated

    private void tbl_veiwRecordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_veiwRecordsMouseClicked

    }//GEN-LAST:event_tbl_veiwRecordsMouseClicked

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
            java.util.logging.Logger.getLogger(OrderBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser cal_fromDate;
    private rojeru_san.componentes.RSDateChooser cal_toDate;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_minimize;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private rojeru_san.complementos.RSTableMetro tbl_veiwRecords;
    // End of variables declaration//GEN-END:variables
}
