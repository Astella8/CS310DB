package edu.jsu.mcis;

import java.sql.*;

/**
 * The TASDatabase class connects to the mySQL database and gets necessary data
 * from the specified table.
 *
 * @author Brandon
 */

public class TASDatabase {

    Connection conn;

    /**
     * Creates connection to the database.
     */
    public TASDatabase() {

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String u = "root";
            String p = "norris";

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tas", u, p);

        } catch (Exception e) {
            System.err.println(e.toString());
        }

    }

    /**
     * Executes query needed to select badgeID, description And creates a new
     * badge object with those parameters
     * @param id of the badge needed from SQL
     * @return the created Badge object
     */
    public Badge getBadge(String id) {

        Badge b = null;

        try {

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM badge WHERE id='" + id + "'");

            while (rs.next()) {
                String badgeid = rs.getString(1);
                String description = rs.getString(2);
                b = new Badge(badgeid, description);
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
        }

        return b;

    }

    /**
     * Executes Query needed to select multiple variables from the table event.
     * And creates a Punch object with said variables.
     * @param id of the punch needed from SQL
     * @return the create Punch object.
     */
    public Punch getPunch(int id) {

        Punch p = null;

        try {

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT UNIX_TIMESTAMP(originaltimestamp),terminalid,eventtypeid,badgeid FROM event WHERE id='" + id + "'");
            while (rs.next()) {
                int original = rs.getInt(1);
                int terminalId = rs.getInt(2);
                int shiftId = rs.getInt(3);
                int eventTypeId = rs.getInt(3);
                String badgeId = rs.getString(4);
                int adjusted = 0;
                p = new Punch(terminalId, badgeId, shiftId, original, adjusted, eventTypeId);

            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        }

        return p;

    }

    /**
     * Executes Query needed to select multiple variables from the shift table.
     * Creates a Shift object with those variables.
     * @param id of the punch needed from SQL
     * @return the created Shift object
     */
    public Shift getShift(int id) {

        Shift s = null;

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM shift WHERE id='" + id + "'");

            while (rs.next()) {
                String shiftId = rs.getString(1);
                String desc = rs.getString(2);
                String start = rs.getString(3);
                String end = rs.getString(4);
                String lunchstrt = rs.getString(8);
                String lunchend = rs.getString(9);
                int lunchdeduct = rs.getInt(10);
                int maxtime = rs.getInt(11);

                s = new Shift(shiftId, desc, start, end, lunchstrt, lunchend, lunchdeduct, maxtime);
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return s;
    }
    /**
     * Main function creates database connection the runs test.
     * @param args 
     */
    public static void main(String args[]) {
        TASDatabase db = new TASDatabase();
        Shift s = db.getShift(1);
        System.out.println(s);
    }
}
