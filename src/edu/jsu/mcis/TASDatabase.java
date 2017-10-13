package edu.jsu.mcis;

import java.sql.*;  


public class TASDatabase {
    
    Connection conn;
    
    public TASDatabase() {
        
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
    public Punch getPunch(int id) {
        
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
                    
                }
        }
        
        catch (SQLException e) {
            System.err.println(e.toString());
        }
        
        return p;
        
    }
    public Shift getShift(int idd){
        
        String id = Integer.toString(idd);
        
        
        Shift s = null;
        
        try{
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM shift WHERE id='" + id + "'");
            
            while(rs.next()) {
                String desc = rs.getString(2);
                String start = rs.getString(3);
                String end = rs.getString(4);
                String lunchstrt = rs.getString(8);
                String lunchend = rs.getString(9);
                int lunchdeduct = rs.getInt(10);
                int maxtime = rs.getInt(11);
                
                s = new Shift(id, desc, start, end, lunchstrt, lunchend, lunchdeduct, maxtime);
                System.out.println(desc + ": " + start + " - "+ end + " ("+ String.valueOf(maxtime - lunchdeduct) +
                        " minutes); Lunch: " + lunchstrt + " - " + lunchend + " (30 minutes)");
            }
        }
        
        catch (SQLException e) {
            System.err.println(e.toString());
        }
        return s;
    }

    public static void main(String args[]) {
        TASDatabase db = new TASDatabase();
        Shift s = db.getShift(2);
    }
}