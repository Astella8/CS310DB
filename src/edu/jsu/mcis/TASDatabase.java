package edu.jsu.mcis;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The TASDatabase class connects to the mySQL database and gets necessary data
 * from the specified table.
 *
 * @author EveryONE!!!!!
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
     *
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
     *
     * @param id of the punch needed from SQL
     * @return the create Punch object.
     */
    public Punch getPunch(int id) {

        Punch p = null;

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT UNIX_TIMESTAMP(originaltimestamp),terminalid,eventtypeid,badgeid,Year(originaltimestamp) as StartYear, Month(originaltimestamp) as StartMonth, DAY(originaltimestamp) as StartDay FROM event WHERE id='" + id + "'");
            while (rs.next()) {
                int original = rs.getInt(1);
                int terminalId = rs.getInt(2);
                int shiftId = rs.getInt(3);
                int eventTypeId = rs.getInt(3);
                String badgeId = rs.getString(4);
                int startYear = rs.getInt(5);
                int startMonth = rs.getInt(6);
                int startDay = rs.getInt(7);
                int adjusted = 0;
                p = new Punch(terminalId, badgeId, shiftId, original, adjusted, eventTypeId, startYear, startMonth, startDay);

            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        }

        return p;

    }
    
    public int getShiftByBadge(String badgeid) {

        int shiftid = -1;

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT shiftid FROM employee WHERE badgeid = '" + badgeid + "'");
            while (rs.next()) {
                shiftid = rs.getInt(1);
                
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        }

        return shiftid;

    }

    /**
     * Executes Query needed to select multiple variables from the shift table.
     * Creates a Shift object with those variables.
     *
     * @param id of the punch needed from SQL
     * @return the created Shift object
     */
    public Shift getShift(int id) {

        Shift s = null;

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT *,Hour(start) as StartHour, Minute(start) as StartMinute\n"
                    + ",Hour(lunchstart) as LunchStartHour, Minute(lunchstart) as LunchStartMinute\n"
                    + ",Hour(lunchstop) as LunchStopHour, Minute(lunchstop) as LunchStopMinute\n"
                    + ",Hour(stop) as StopHour, Minute(stop) as StopMinute\n"
                    + "FROM tas.shift s WHERE id = '" + id + "'");

            while (rs.next()) {
                int shiftId = rs.getInt(1);
                String desc = rs.getString(2);
                int lunchdeduct = rs.getInt(10);
                int maxtime = rs.getInt(11);
                int interval = rs.getInt(5);
                int startHour = rs.getInt(13);
                int startMinute = rs.getInt(14);
                int lunchStartHour = rs.getInt(15);
                int lunchStartMinute = rs.getInt(16);
                int lunchStopHour = rs.getInt(17);
                int lunchStopMinute = rs.getInt(18);
                int stopHour = rs.getInt(19);
                int stopMinute = rs.getInt(20);

                s = new Shift(shiftId, desc, lunchdeduct, maxtime, interval, startHour, startMinute, lunchStartHour, lunchStartMinute, lunchStopHour, lunchStopMinute, stopHour, stopMinute);
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return s;
    }

    /**
     * Insert punch object into database
     *
     * @param event
     * @return ID created in database
     */
    public int insertPunch(Punch event) {
        int key = 0;
        try {
            int result;
            Punch p = null;
            String badgeId = event.getBadgeId();
            int terminalId = event.getTerminalId();
            int punchTypeId = event.getPunchTypeId();
            GregorianCalendar originalTimestamp = event.getOriginaltimestamp();
            originalTimestamp = new GregorianCalendar();
            Statement stmt = conn.createStatement();
            ResultSet keys;
            String sql = "INSERT INTO event (badgeid, originaltimestamp, terminalid, eventtypeid) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, badgeId);
            ps.setString(2, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(originalTimestamp.getTime()));
            ps.setInt(3, 101);
            ps.setInt(4, 1);
            result = ps.executeUpdate();
            if (result == 1) {
                keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    key = keys.getInt(1);
                }
            }
        } catch (SQLException ex) {
        }
        return key;
    }

    public int updateQuery(int id, GregorianCalendar adjusted, String eventdata) {
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSet generatedKeys = null;
        String query, update;
        int result = 0, key = 0;
        try {
            stmt = conn.createStatement();
            update = "update event set adjustedtimestamp='" + adjusted + ", eventdata = " + eventdata + "'where id='" + id + "'";

        } catch (SQLException ex) {
            Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;

    }

    public int getClockTimes(String id) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM event WHERE eventtypeid='" + id + "'");
            while (rs.next()) {
                int shiftId = rs.getInt(7);
                return shiftId;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    private ArrayList<Punch> getPunchList(String badgeid, String sdf) {
        Punch newPunch = null;
        ArrayList<Punch> punchList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String state = "SELECT *, UNIX_TIMESTAMP(originaltimestamp) FROM event WHERE badgeid='" + badgeid + "' AND originaltimestamp LIKE '" + sdf + "%' ORDER BY originaltimestamp;";
            ResultSet rs = stmt.executeQuery(state);
                while(rs.next()){
                   int id = rs.getInt(1);
                   int terminalid = rs.getInt(2);
                   int eventtypeid = rs.getInt(5);
                   String eventdata = rs.getString(6);
                   long unixots = rs.getLong(8);
                   newPunch = new Punch(badgeid, terminalid, eventtypeid);
                   newPunch.setPunchId(String.valueOf(id));
                   newPunch.getOriginaltimestamp().setTimeInMillis(unixots * 1000);
                   newPunch.getAdjustedTimeStamp().setTimeInMillis(0);
                   punchList.add(newPunch);
                }
        }
        catch (SQLException ex){
        }
        return punchList;
    }
    
    public int getMinutesAccrued(Punch p) {
        String badgeid = p.getBadgeId();
        GregorianCalendar OTS = p.getOriginaltimestamp();
        String sdf = new SimpleDateFormat("YYYY-MM-dd").format(OTS.getTime());
        Deque s = new ArrayDeque();
        Punch newPunch = null;
        int shiftid = getShiftByBadge(badgeid);
        boolean inBlock = false;
        int totalMinutes = 0;
        long difference = 0;
        ArrayList<Punch> punchList = getPunchList(badgeid, sdf);
        for (int i = 0; i < punchList.size(); ++i) {
            punchList.get(i).adjust(getShift(shiftid));
            System.out.println(punchList.get(i).printOriginalTimestamp() + "->" + punchList.get(i).printAdjustedTimestamp());
             
        }
        for (int i = 0; i < punchList.size(); ++i) {
            if ((punchList.get(i).getPunchTypeId() == 1) && !(inBlock)) {
                inBlock = true;
                difference = punchList.get(i).getOriginaltimestamp().getTimeInMillis();
            }
            if ((punchList.get(i).getPunchTypeId() == 0) && (inBlock)) {
                inBlock = false;
                difference = punchList.get(i).getOriginaltimestamp().getTimeInMillis() - difference;
                totalMinutes += difference / 60000;
            }
            if ((punchList.get(i).getPunchTypeId() == 2) && (inBlock)) {
                inBlock = false;
            }
            
        }
        return totalMinutes;
    }

    /**
     * Main function creates database connection the runs test.
     *
     * @param args
     */
    public static void main(String args[]) {
        TASDatabase db = new TASDatabase();
        //int shiftid = db.getShiftByBadge("28DC3FB8");
        //System.out.println(shiftid);
        Punch p = db.getPunch(3634);
        int minutes = db.getMinutesAccrued(p);
        System.out.println(minutes);
    }

}
