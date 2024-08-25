//Author: SAM
//Transport Management System


package jframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class Booking extends javax.swing.JFrame {
    
    Toolkit tk = Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
    Dimension screenSize = tk.getScreenSize();
    
    public Booking() {
        
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initialEdits();
        setVehicleDetailsToTable();
        
    }
    
    //Method to add transportation transaction ID
    public void initialEdits() {
        
        int ID;
        
        try {
            
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT Transport_ID FROM booking_details ORDER BY Transport_ID DESC");
            
            if(rs.next())
            {
                ID = rs.getInt("Transport_ID") + 1;
                lbl_transportID.setText("Transportation ID:     " + ID);
            
            }else
                lbl_transportID.setText("Transportation ID:     1");
             
            
            con.close();
            st.close();
            rs.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
 
    DefaultTableModel model; 
    //method to set vehicle details to table
    public void setVehicleDetailsToTable() {
        
        model = (DefaultTableModel) tbl_vehicleDetails.getModel();
        model.setRowCount(0);
        String numberPlate, driverName, location;
        int ID, capacity;
        boolean status;
        
        try {
            
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from vehicle_details where Availability = 1");
            
            while(rs.next()) {
                
                ID = rs.getInt("ID");
                numberPlate = rs.getString("NumberPlate");
                driverName = rs.getString("DriverName");
                status = rs.getBoolean("Availability");
                location = rs.getString("CurrentLocation");
                capacity = rs.getInt("LoadCapacity");
                
                Object[] obj = {ID, numberPlate, driverName, status, location, capacity};
                model =(DefaultTableModel) tbl_vehicleDetails.getModel();
                model.addRow(obj);
                
                //set available column text to green
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
        DefaultTableModel tModel = (DefaultTableModel) tbl_vehicleDetails.getModel();
        tModel.setRowCount(0);
    }
    
    

    //Method to check fields have been filled appropriately
    public boolean checkFields() {
        
        boolean feildSpace = true;
        
        try {
            if(txt_vehicleID.getText().equals(""))
            {
                lbl_vehicleIdUnfilled.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/imp.png")));
                feildSpace = false;
            }else
                lbl_vehicleIdUnfilled.setIcon(null);

            if(txt_customerName.getText().equals(""))
            {
                lbl_customerNameUnfilled1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/imp.png")));
                feildSpace = false;
            }else
                lbl_customerNameUnfilled1.setIcon(null);

            if(cal_loadDate.getDatoFecha() == null)
            {
                lbl_loadDateNull.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/imp.png")));
                feildSpace = false;
            }else
                lbl_loadDateNull.setIcon(null);

            if(cal_deliveryDate.getDatoFecha() == null)
            {
                lbl_deliveryDateNull.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/imp.png")));
                feildSpace = false;
            }else
                lbl_deliveryDateNull.setIcon(null);

            if(txt_product.getText().equals(""))
            {
                lbl_productNull.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/imp.png")));
                feildSpace = false;
            }else
                lbl_productNull.setIcon(null);

            if(txt_km.getText().equals(""))
            {
                lbl_kmNull.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/imp.png")));
                feildSpace = false;
            }else
                lbl_kmNull.setIcon(null);

            if(combo_loadPoint.getSelectedItem() == combo_deliveryPoint.getSelectedItem())
            {
                lbl_locationSame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/imp.png")));
                feildSpace = false;
            }else
                lbl_locationSame.setIcon(null);
            
            if(txt_weight.getText().equals(""))
            {
                lbl_weightNull.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/imp.png")));
                feildSpace = false;
            }else
                lbl_weightNull.setIcon(null);
        
            return feildSpace;

        } catch (Exception e) {
            return false;
        }
    }
    
    //method to insert booking details to database
    public boolean bookTransportTransaction() {
        
        if(checkFields() == false)
            return false;
        
        if(!termsAndCondition.isSelected())
        {
            JOptionPane.showMessageDialog(this, "Please confirm terms and conditions.");
            return false;
        }
            
        boolean isBooked = false;
        
        int rowNo = tbl_vehicleDetails.getSelectedRow();
        TableModel tModel = tbl_vehicleDetails.getModel();
        String driverName = tModel.getValueAt(rowNo, 2).toString();
        
        int vehicleID = Integer.parseInt(txt_vehicleID.getText());
        String customerName = txt_customerName.getText();
        String loadingPoint = (String) combo_loadPoint.getSelectedItem();
        String deliveryPoint = (String) combo_deliveryPoint.getSelectedItem();
        String packageName = (String) combo_package.getSelectedItem();
        String product = txt_product.getText();
        int amount = Integer.parseInt(txt_km.getText());
        
        
        Date uloadDate = cal_loadDate.getDatoFecha();
        Date udeliveryate = cal_deliveryDate.getDatoFecha();
        
        Long t1 = uloadDate.getTime();
        Long t2 = udeliveryate.getTime();
        
        java.sql.Date sLoadDate = new java.sql.Date(t1);
        java.sql.Date sDeleveryDate = new java.sql.Date(t2);
        
        try {
            
            Connection con = DBConnection.getConnection();
            String str = "insert into booking_details(Vehicle_ID, customer_name, loading_point, delivery_point, load_date, delivery_date, product, weight, price, package, status, driver_name) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(str);
            
            pst.setInt(1,vehicleID);
            pst.setString(2,customerName);
            pst.setString(3,loadingPoint);
            pst.setString(4,deliveryPoint);
            pst.setDate(5,sLoadDate);
            pst.setDate(6,sDeleveryDate);
            pst.setString(7,product);
            pst.setInt(8,amount);
            pst.setDouble(9, getPriceOfTransaction());
            pst.setString(10, packageName);
            pst.setString(11, "Ongoing");
            pst.setString(12, driverName);
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                updateVehicleStatus();
                isBooked = true;
            }
            
            con.close();
            pst.close();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return isBooked;
    }
    
    
    //Method to update availability status of vehicle (in vehicle_details db)
    public void updateVehicleStatus() {

        int vehicleID = Integer.parseInt(txt_vehicleID.getText());

        try {

            Connection con = DBConnection.getConnection();
            String str = "update vehicle_details set Availability = 0 where ID = ?";
            PreparedStatement pst = con.prepareStatement(str);
            pst.setInt(1,vehicleID);

            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                
                con.close();
                pst.close();
        }
            
        }catch (Exception e) {

            e.printStackTrace();
        }
    }

    //method to calculate price of transport transaction
    public double getPriceOfTransaction() {
        
        int vehicleID = Integer.parseInt(txt_vehicleID.getText());
        int dist = Integer.parseInt(txt_km.getText());
        double cost, price;
        
        try {
            
            Connection con = DBConnection.getConnection();
            String str = "select * from vehicle_details where ID = ?";
            
            PreparedStatement pst = con.prepareStatement(str);
            pst.setInt(1,vehicleID);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                
                cost = rs.getDouble("cost_perKm");
                price = dist*cost;
                lbl_price.setText("Price:    " + price);
                return price;
            }
            
            con.close();
            pst.close();
            rs.close();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return 0;
    }
    
    //Method to clear form
    public void clearForm() {
        
        lbl_transportID.setText("Transportation ID: ");
        txt_vehicleID.setText("");
        txt_customerName.setText("");
        combo_loadPoint.setSelectedIndex(0);
        combo_deliveryPoint.setSelectedIndex(0);
        combo_package.setSelectedIndex(0);
        txt_product.setText("");
        txt_km.setText("");
        cal_loadDate.setDatoFecha(null);
        cal_deliveryDate.setDatoFecha(null);
        lbl_costPerKm.setText("Cost (/km): ");
        lbl_price.setText("Price: ");
        
        setVehicleDetailsToTable();
       
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lbl_ = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        rSMaterialButtonCircle4 = new rojerusan.RSMaterialButtonCircle();
        jPanel6 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        cal_loadDate = new rojeru_san.componentes.RSDateChooser();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        cal_deliveryDate = new rojeru_san.componentes.RSDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_vehicleDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel8 = new javax.swing.JLabel();
        txt_vehicleID = new app.bolivia.swing.JCTextField();
        lbl_transportID = new javax.swing.JLabel();
        combo_loadPoint = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        combo_deliveryPoint = new javax.swing.JComboBox<>();
        lbl_price = new javax.swing.JLabel();
        txt_product = new app.bolivia.swing.JCTextField();
        txt_km = new app.bolivia.swing.JCTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txt_customerName = new app.bolivia.swing.JCTextField();
        lbl_vehicleIdUnfilled = new javax.swing.JLabel();
        lbl_locationSame = new javax.swing.JLabel();
        lbl_productNull = new javax.swing.JLabel();
        lbl_customerNameUnfilled1 = new javax.swing.JLabel();
        lbl_deliveryDateNull = new javax.swing.JLabel();
        combo_package = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        lbl_weightNull = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbl_loadDateNull = new javax.swing.JLabel();
        lbl_costPerKm = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbl_close = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_minimize = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tAndC = new javax.swing.JLabel();
        termsAndCondition = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();
        txt_weight = new app.bolivia.swing.JCTextField();
        lbl_kmNull = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1920, 1324));
        setSize(new java.awt.Dimension(1920, 1324));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_.setBackground(new java.awt.Color(245, 245, 245));
        lbl_.setForeground(new java.awt.Color(51, 51, 255));
        lbl_.setMinimumSize(new java.awt.Dimension(1480, 810));
        lbl_.setName(""); // NOI18N
        lbl_.setPreferredSize(new java.awt.Dimension(1920, 1324));
        lbl_.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(245, 245, 245));
        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Available Vehicles");
        jLabel7.setOpaque(true);
        lbl_.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 240, 50));

        rSMaterialButtonCircle4.setBackground(new java.awt.Color(255, 0, 51));
        rSMaterialButtonCircle4.setText("Book");
        rSMaterialButtonCircle4.setFont(new java.awt.Font("Roboto Medium", 1, 17)); // NOI18N
        rSMaterialButtonCircle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle4ActionPerformed(evt);
            }
        });
        lbl_.add(rSMaterialButtonCircle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 940, 310, 90));

        jPanel6.setBackground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lbl_.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 130, 260, 5));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("Vehicle ID:");
        lbl_.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 250, 190, 30));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setText("Load Date:");
        lbl_.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 700, 150, 30));

        cal_loadDate.setBackground(new java.awt.Color(245, 245, 245));
        cal_loadDate.setColorBackground(new java.awt.Color(77, 76, 125));
        cal_loadDate.setColorButtonHover(new java.awt.Color(77, 76, 125));
        cal_loadDate.setColorForeground(new java.awt.Color(51, 51, 51));
        cal_loadDate.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cal_loadDate.setFormatoFecha("dd/MM/yyyy");
        cal_loadDate.setPlaceholder("Select issue date..");
        lbl_.add(cal_loadDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 690, 290, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("Loading Point:");
        lbl_.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 440, 170, 30));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setText("Delivery Date:");
        lbl_.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 770, 150, 30));

        cal_deliveryDate.setBackground(new java.awt.Color(245, 245, 245));
        cal_deliveryDate.setColorBackground(new java.awt.Color(77, 76, 125));
        cal_deliveryDate.setColorButtonHover(new java.awt.Color(77, 76, 125));
        cal_deliveryDate.setColorForeground(new java.awt.Color(51, 51, 51));
        cal_deliveryDate.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cal_deliveryDate.setFormatoFecha("dd/MM/yyyy");
        cal_deliveryDate.setPlaceholder("Select due date..");
        lbl_.add(cal_deliveryDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 760, 290, -1));

        tbl_vehicleDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vehicle ID", "Number Plate", "Driver Name", "Available", "Location", "Capacity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_vehicleDetails.setColorBackgoundHead(new java.awt.Color(0, 51, 102));
        tbl_vehicleDetails.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tbl_vehicleDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_vehicleDetails.setColorSelBackgound(new java.awt.Color(233, 213, 202));
        tbl_vehicleDetails.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tbl_vehicleDetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tbl_vehicleDetails.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tbl_vehicleDetails.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tbl_vehicleDetails.setRowHeight(40);
        tbl_vehicleDetails.getTableHeader().setReorderingAllowed(false);
        tbl_vehicleDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_vehicleDetailsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_vehicleDetails);
        tbl_vehicleDetails.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        lbl_.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 830, 850));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 153));
        jLabel8.setText("Book Vehicle");
        lbl_.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 80, 160, 50));

        txt_vehicleID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(77, 76, 125)));
        txt_vehicleID.setForeground(new java.awt.Color(0, 0, 255));
        txt_vehicleID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_vehicleID.setDisabledTextColor(new java.awt.Color(0, 0, 204));
        txt_vehicleID.setEnabled(false);
        txt_vehicleID.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_vehicleID.setPhColor(new java.awt.Color(77, 76, 125));
        txt_vehicleID.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_vehicleIDCaretUpdate(evt);
            }
        });
        txt_vehicleID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_vehicleIDActionPerformed(evt);
            }
        });
        lbl_.add(txt_vehicleID, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 240, 190, 40));

        lbl_transportID.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbl_transportID.setForeground(new java.awt.Color(102, 102, 102));
        lbl_transportID.setText("Transportation ID:");
        lbl_.add(lbl_transportID, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 180, 370, 30));

        combo_loadPoint.setBackground(new java.awt.Color(245, 245, 245));
        combo_loadPoint.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        combo_loadPoint.setForeground(new java.awt.Color(102, 102, 102));
        combo_loadPoint.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jalgaon", "Pune", "Mumbai", "Aurangabad", "Nashik", "Bhusawal" }));
        lbl_.add(combo_loadPoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 440, 190, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setText("Delivery Point:");
        lbl_.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 510, 170, 30));

        combo_deliveryPoint.setBackground(new java.awt.Color(245, 245, 245));
        combo_deliveryPoint.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        combo_deliveryPoint.setForeground(new java.awt.Color(102, 102, 102));
        combo_deliveryPoint.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jalgaon", "Pune", "Mumbai", "Aurangabad", "Nashik", "Bhusawal" }));
        lbl_.add(combo_deliveryPoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 510, 190, -1));

        lbl_price.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbl_price.setForeground(new java.awt.Color(255, 51, 0));
        lbl_price.setText("Price:");
        lbl_.add(lbl_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 860, 280, 60));

        txt_product.setBackground(new java.awt.Color(245, 245, 245));
        txt_product.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(77, 76, 125)));
        txt_product.setForeground(new java.awt.Color(51, 51, 51));
        txt_product.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_product.setPhColor(new java.awt.Color(77, 76, 125));
        txt_product.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_productFocusLost(evt);
            }
        });
        txt_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_productActionPerformed(evt);
            }
        });
        lbl_.add(txt_product, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 580, 120, 40));

        txt_km.setBackground(new java.awt.Color(245, 245, 245));
        txt_km.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(77, 76, 125)));
        txt_km.setForeground(new java.awt.Color(51, 51, 51));
        txt_km.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_km.setPhColor(new java.awt.Color(77, 76, 125));
        txt_km.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_kmFocusLost(evt);
            }
        });
        txt_km.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kmActionPerformed(evt);
            }
        });
        lbl_.add(txt_km, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 500, 120, 40));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Km:");
        lbl_.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 510, 80, 30));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 102, 102));
        jLabel37.setText("Product:");
        lbl_.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 590, 90, 30));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(102, 102, 102));
        jLabel38.setText("Customer Name:\n");
        lbl_.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 330, 190, 30));

        txt_customerName.setBackground(new java.awt.Color(245, 245, 245));
        txt_customerName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(77, 76, 125)));
        txt_customerName.setForeground(new java.awt.Color(51, 51, 51));
        txt_customerName.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_customerName.setPhColor(new java.awt.Color(77, 76, 125));
        txt_customerName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_customerNameFocusLost(evt);
            }
        });
        txt_customerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_customerNameActionPerformed(evt);
            }
        });
        lbl_.add(txt_customerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 320, 190, 40));

        lbl_vehicleIdUnfilled.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_.add(lbl_vehicleIdUnfilled, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 260, 30, 20));

        lbl_locationSame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_.add(lbl_locationSame, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 500, 30, 20));

        lbl_productNull.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_.add(lbl_productNull, new org.netbeans.lib.awtextra.AbsoluteConstraints(1520, 590, 30, 20));

        lbl_customerNameUnfilled1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_.add(lbl_customerNameUnfilled1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 340, 30, 20));

        lbl_deliveryDateNull.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_.add(lbl_deliveryDateNull, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 780, 30, 20));

        combo_package.setBackground(new java.awt.Color(245, 245, 245));
        combo_package.setEditable(true);
        combo_package.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        combo_package.setForeground(new java.awt.Color(102, 102, 102));
        combo_package.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Metal load", "Glassware", "Wooden", "Electronic Gadgets", "Textile", "Vehicle", "Food items", "House Shifting", "Animals", "Other" }));
        combo_package.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_packageActionPerformed(evt);
            }
        });
        lbl_.add(combo_package, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 580, 200, 40));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 102));
        jLabel33.setText("Package:");
        lbl_.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 590, 90, 30));

        lbl_weightNull.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_.add(lbl_weightNull, new org.netbeans.lib.awtextra.AbsoluteConstraints(1800, 590, 30, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/delivery1.jpg"))); // NOI18N
        lbl_.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 150, 390, 280));

        lbl_loadDateNull.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_.add(lbl_loadDateNull, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 710, 30, 20));

        lbl_costPerKm.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbl_costPerKm.setForeground(new java.awt.Color(102, 102, 102));
        lbl_costPerKm.setText("Cost (/km):");
        lbl_.add(lbl_costPerKm, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 860, 280, 60));

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(lbl_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1860, 10, 50, 50));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/male_user_50px.png"))); // NOI18N
        jLabel4.setText("Admin");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1650, 10, 130, 50));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/icons/icons8-back-arrow-64.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 70));

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
        jPanel1.add(lbl_minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1800, 10, 60, 40));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1640, 10, 140, 50));

        lbl_.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 70));

        tAndC.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        tAndC.setForeground(new java.awt.Color(0, 51, 204));
        tAndC.setText("terms and conditions");
        tAndC.setToolTipText("terms and conditions");
        tAndC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tAndC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tAndCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tAndCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tAndCMouseExited(evt);
            }
        });
        lbl_.add(tAndC, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 960, 210, 30));

        termsAndCondition.setBackground(new java.awt.Color(245, 245, 245));
        termsAndCondition.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        termsAndCondition.setForeground(new java.awt.Color(102, 102, 102));
        termsAndCondition.setSelected(true);
        termsAndCondition.setActionCommand("Agree to terms and conditions");
        termsAndCondition.setIconTextGap(8);
        termsAndCondition.setLabel("Agree to");
        termsAndCondition.setMargin(new java.awt.Insets(0, 0, 0, 0));
        termsAndCondition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                termsAndConditionActionPerformed(evt);
            }
        });
        lbl_.add(termsAndCondition, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 950, 120, 50));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Weight:");
        lbl_.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1590, 590, 80, 30));

        txt_weight.setBackground(new java.awt.Color(245, 245, 245));
        txt_weight.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(77, 76, 125)));
        txt_weight.setForeground(new java.awt.Color(51, 51, 51));
        txt_weight.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_weight.setPhColor(new java.awt.Color(77, 76, 125));
        txt_weight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_weightFocusLost(evt);
            }
        });
        txt_weight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_weightActionPerformed(evt);
            }
        });
        lbl_.add(txt_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(1680, 580, 120, 40));

        lbl_kmNull.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_.add(lbl_kmNull, new org.netbeans.lib.awtextra.AbsoluteConstraints(1620, 510, 30, 20));

        getContentPane().add(lbl_, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1340));

        setSize(new java.awt.Dimension(1920, 1324));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Image img = new ImageIcon(this.getClass().getResource("/jframe/icons/service1.jpg")).getImage();
        this.setIconImage(img);
    }//GEN-LAST:event_formWindowActivated

    private void txt_kmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_kmFocusLost
        
        if(!txt_km.getText().equals("") && !txt_vehicleID.getText().equals(""))
            getPriceOfTransaction();
        
    }//GEN-LAST:event_txt_kmFocusLost

    private void txt_kmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kmActionPerformed
        
    }//GEN-LAST:event_txt_kmActionPerformed

    private void rSMaterialButtonCircle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle4ActionPerformed
        
        if(bookTransportTransaction())
        {
            String transportID = lbl_transportID.getText();
            String vehicleID = txt_vehicleID.getText();
            String customerName = txt_customerName.getText();
            String loadingPoint = (String) combo_loadPoint.getSelectedItem();
            String deliveryPoint = (String) combo_deliveryPoint.getSelectedItem();
            String km = txt_km.getText();
            String packageName = (String) combo_package.getSelectedItem();
            String product = txt_product.getText();
            String weight = txt_km.getText();
            String price = lbl_price.getText();


            Date uloadDate = cal_loadDate.getDatoFecha();
            Date udeliveryate = cal_deliveryDate.getDatoFecha();

            Long t1 = uloadDate.getTime();
            Long t2 = udeliveryate.getTime();

            java.sql.Date sLoadDate = new java.sql.Date(t1);
            java.sql.Date sDeliveryDate = new java.sql.Date(t2);
            
            String loadDate = sLoadDate.toString();
            String deliveryDate = sDeliveryDate.toString();
            
            String [] obj = {transportID, vehicleID, customerName, loadingPoint, deliveryPoint, km, packageName, product, weight, loadDate, deliveryDate, price};
            Receipt receipt = new Receipt(obj);
            receipt.setVisible(rootPaneCheckingEnabled);
            clearForm();
        }
        else
            JOptionPane.showMessageDialog(this, "Transaction cannot be done.\n Please fill all the fields");
    }//GEN-LAST:event_rSMaterialButtonCircle4ActionPerformed

    private void txt_vehicleIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_vehicleIDActionPerformed
        
    }//GEN-LAST:event_txt_vehicleIDActionPerformed

    private void txt_productFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_productFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_productFocusLost

    private void txt_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_productActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_productActionPerformed

    private void tbl_vehicleDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_vehicleDetailsMouseClicked
        
        int rowNo = tbl_vehicleDetails.getSelectedRow();
        TableModel tModel = tbl_vehicleDetails.getModel();
        
        txt_vehicleID.setText(tModel.getValueAt(rowNo, 0).toString());
        int vehicleID = Integer.parseInt(txt_vehicleID.getText());
        
        double cost;
        try {
            
            Connection con = DBConnection.getConnection();
            String str = "select * from vehicle_details where ID = ?";
            PreparedStatement pst = con.prepareStatement(str);
            
            vehicleID = (Integer) vehicleID;
            pst.setInt(1,vehicleID);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                
                cost = rs.getDouble("cost_perKm");
                lbl_costPerKm.setText("Cost (/km):     " + cost);
            }
            
            con.close();
            pst.close();
            rs.close();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_tbl_vehicleDetailsMouseClicked

    private void txt_customerNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_customerNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_customerNameFocusLost

    private void txt_customerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_customerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_customerNameActionPerformed

    private void lbl_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lbl_closeMouseClicked

    private void lbl_closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseEntered
        lbl_close.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_lbl_closeMouseEntered

    private void lbl_closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseExited
        lbl_close.setForeground(Color.WHITE);
    }//GEN-LAST:event_lbl_closeMouseExited

    private void txt_vehicleIDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_vehicleIDCaretUpdate
           
        if(!txt_km.getText().equals("") && !txt_vehicleID.getText().equals(""))
            getPriceOfTransaction();
        
    }//GEN-LAST:event_txt_vehicleIDCaretUpdate

    private void combo_packageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_packageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_packageActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void lbl_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lbl_minimizeMouseClicked

    private void lbl_minimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseEntered
        lbl_minimize.setForeground(Color.GRAY);
    }//GEN-LAST:event_lbl_minimizeMouseEntered

    private void lbl_minimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizeMouseExited
        lbl_minimize.setForeground(Color.WHITE);
    }//GEN-LAST:event_lbl_minimizeMouseExited

    private void termsAndConditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_termsAndConditionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_termsAndConditionActionPerformed

    private void tAndCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tAndCMouseExited
        tAndC.setText("terms and conditions");
    }//GEN-LAST:event_tAndCMouseExited

    private void tAndCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tAndCMouseEntered
        tAndC.setText("<html><u>terms and conditions</u></html>");
    }//GEN-LAST:event_tAndCMouseEntered

    private void tAndCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tAndCMouseClicked
        TermsAndConditions tc = new TermsAndConditions();
        tc.setVisible(true);
    }//GEN-LAST:event_tAndCMouseClicked

    private void txt_weightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_weightFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_weightFocusLost

    private void txt_weightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_weightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_weightActionPerformed

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
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Booking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private rojeru_san.componentes.RSDateChooser cal_deliveryDate;
    private rojeru_san.componentes.RSDateChooser cal_loadDate;
    private javax.swing.JComboBox<String> combo_deliveryPoint;
    private javax.swing.JComboBox<String> combo_loadPoint;
    private javax.swing.JComboBox<String> combo_package;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel lbl_;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_costPerKm;
    private javax.swing.JLabel lbl_customerNameUnfilled1;
    private javax.swing.JLabel lbl_deliveryDateNull;
    private javax.swing.JLabel lbl_kmNull;
    private javax.swing.JLabel lbl_loadDateNull;
    private javax.swing.JLabel lbl_locationSame;
    private javax.swing.JLabel lbl_minimize;
    private javax.swing.JLabel lbl_price;
    private javax.swing.JLabel lbl_productNull;
    private javax.swing.JLabel lbl_transportID;
    private javax.swing.JLabel lbl_vehicleIdUnfilled;
    private javax.swing.JLabel lbl_weightNull;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle4;
    private javax.swing.JLabel tAndC;
    private rojeru_san.complementos.RSTableMetro tbl_vehicleDetails;
    private javax.swing.JCheckBox termsAndCondition;
    private app.bolivia.swing.JCTextField txt_customerName;
    private app.bolivia.swing.JCTextField txt_km;
    private app.bolivia.swing.JCTextField txt_product;
    private app.bolivia.swing.JCTextField txt_vehicleID;
    private app.bolivia.swing.JCTextField txt_weight;
    // End of variables declaration//GEN-END:variables
}
