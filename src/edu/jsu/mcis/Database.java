package edu.jsu.mcis;

import java.sql.*;  


public class Database {
    
    Connection conn;
    
    public Database() {
        
        try {
        
            Class.forName("com.mysql.jdbc.Driver").newInstance();  
            String u = "root";
            String p = "root";
            
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
        
        catch (SQLException e) {
            System.err.println(e.toString());
        }
        
        return b;
        
    }
    public Punch getPunch(String id) {
        
        Punch p = null;
        
        try {
            
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM event WHERE id='" + id + "'");
            
            while(rs.next()) {
                int shiftId = rs.getInt(5);
                String badgeId = rs.getString(3);
                // Punch Description
                int terminalId = rs.getInt(2);
                Long orginalts = rs.getLong(4);
                Long adjustedts = null;
                String originalts = null;
                
                
        
                p = new Punch(terminalId, badgeId, shiftId, originalts, adjustedts);
                System.out.println("#" + badgeId + "CLOCKED IN:"  + originalts);
            }

        }
        
        catch (SQLException e) {
            System.err.println(e.toString());
        }
        
        return p;
        
    }

    public static void main(String args[]) {
        
        Database db = new Database();
        Punch b = db.getPunch("93");
        System.out.println(b);
        
    }
}