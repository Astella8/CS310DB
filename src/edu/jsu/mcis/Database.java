package edu.jsu.mcis;

import java.sql.*;  


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
        
        catch (SQLException e) {
            System.err.println(e.toString());
        }
        
        return b;
        
    }
    public Punch getPunch(String id) {
        
        Punch p = null;
        int adjusted = 0;
        
        try {
            
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT UNIX_TIMESTAMP(originaltimestamp),terminalid,eventtypeid,badgeid FROM event WHERE id='" + id + "'");
                while(rs.next()) {
                    int original = rs.getInt(1);
                    int shiftId = rs.getInt(3);
                    String badgeId = rs.getString(4);
                    // Punch Description
                    int terminalId = rs.getInt(2);
                    //Long adjusted = getLong.GregorianCalendar
                    p = new Punch(terminalId, badgeId, shiftId, original, adjusted);
                    System.out.println("#" + badgeId + "CLOCKED IN:"  + adjusted);
                }
        }
        
        catch (SQLException e) {
            System.err.println(e.toString());
        }
        
        return p;
        
    }

    public static void main(String args[]) {
        
        Database db = new Database();
        Punch p = db.getPunch("93");
        System.out.println(p);
        
    }
}