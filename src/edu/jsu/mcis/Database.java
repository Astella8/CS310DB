package edu.jsu.mcis;

import java.sql.*;  
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * Project from Snellan CS310 Software Engineering Class
 * Feature 1 of 4
 * Badge ID, Database and Shift Classes
 * @author Amberley Echols, Brandon Morris, Matthew Price, 
 *  Braden Norris Hearn, Hannah Cronen, Abby Waddell 
 */

public class Database {
    
    Connection conn;
    
    public Database() {
        
        try {
        
            Class.forName("com.mysql.jdbc.Driver").newInstance();  
            String u = "root";
            String p = "norris";
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tas",u,p);
            
        }
        
        catch (Exception e) {
            System.err.println(e.toString());
        }
        
    }

    public Badge getBadge(String id) {
        
        Badge b = null;
        
        try {
            
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM badge WHERE id='" + id + "'");
            
            while(rs.next()) {
                String badgeid = rs.getString(1);
                String description = rs.getString(2);
                b = new Badge(badgeid, description);
            }

        }
        
        catch (Exception e) {
            System.err.println(e.toString());
        }
        
        return b;
        
    }

    public static void main(String args[]) {
        
        Database db = new Database();
        Badge b = db.getBadge("021890C0");
        System.out.println(b);
        
    }
    
}