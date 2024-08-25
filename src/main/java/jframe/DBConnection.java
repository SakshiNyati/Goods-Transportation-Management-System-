//Author: SAM
//Transport Management System


package jframe;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
    
    static Connection con = null;
    
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:7600/transport_ms","root","");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return con;
    }
}
