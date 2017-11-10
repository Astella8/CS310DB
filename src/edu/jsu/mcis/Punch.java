package edu.jsu.mcis;

import java.util.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.lang.Math;

/**
 *
 * @author Braden
 */
public class Punch {

    private TASDatabase db;

    private int shiftId;
    private String punchId;
    private String badgeId;
    private String punchDescription;
    private String eventData;
    private int terminalId;
    private GregorianCalendar original;
    private GregorianCalendar adjusted;
    private String sdf;
    private String sdf2;
    private int eventtypeid;
    private int punchTypeId;
    private int timestamp;
    private int startYear;
    private int startMonth;
    private int startDay;

    /**
     *
     * @param terminalId
     * @param badgeId
     * @param shiftId
     * @param originalts
     * @param adjustedts
     * @param eventtypeid
     */
    public Punch(int terminalId, String badgeId, int shiftId, long originalts, long adjustedts, int eventtypeid, int startYear, int startMonth, int startDay) {
        original = new GregorianCalendar();
        adjusted = new GregorianCalendar();
        originalts = (originalts * 1000);
        adjustedts = (originalts * 1000);
        original.setTimeInMillis(originalts);
        adjusted.setTimeInMillis(adjustedts);
        this.terminalId = terminalId;
        this.badgeId = badgeId;
        this.shiftId = shiftId;
        this.eventtypeid = eventtypeid;
        this.eventData = "";
        this.startYear = startYear;
        this.startMonth = startMonth - 1;
        this.startDay = startDay;

        //String testdate =  format.format(adjusted.getTime());
        sdf = new SimpleDateFormat("EEE MM/dd/YYYY HH:mm:ss").format(original.getTime()).toUpperCase();
    }

    public Punch(String badgeId, int terminalId, int punchTypeId) {
        this.badgeId = badgeId;
        this.terminalId = terminalId;
        this.punchTypeId = 0;
        original = new GregorianCalendar();
        adjusted = new GregorianCalendar();

    }

    public void adjust(Shift shift) {
        Calendar cal = new GregorianCalendar();
        boolean adj = false;
        GregorianCalendar gc = new GregorianCalendar();
        int punchMinute = original.get(Calendar.MINUTE);
        int interval = shift.getInterval();
        int day = original.get(Calendar.DAY_OF_WEEK);
        int adjustedMin;
        adjustedMin = 0;
        adjusted = new GregorianCalendar();

        GregorianCalendar currentgc = new GregorianCalendar();
        GregorianCalendar shiftSa = new GregorianCalendar(startYear, startMonth, startDay, shift.getStartHour(), shift.getStartMinute()); //m1
        GregorianCalendar shiftSo = new GregorianCalendar(startYear, startMonth, startDay, shift.getStopHour(), shift.getStopMinute()); //m2
        GregorianCalendar intIn = new GregorianCalendar(startYear, startMonth, startDay, shift.getStartHour(), shift.getStartMinute()); //m3
        intIn.add(Calendar.MINUTE, -15);
        GregorianCalendar dockIn = new GregorianCalendar(startYear, startMonth, startDay, shift.getStartHour(), shift.getStartMinute()); //m4
        dockIn.add(Calendar.MINUTE, 15);
        GregorianCalendar graceIn = new GregorianCalendar(startYear, startMonth, startDay, shift.getStartHour(), shift.getStartMinute()); //m5
        graceIn.add(Calendar.MINUTE, 5);
        GregorianCalendar dockOut = new GregorianCalendar(startYear, startMonth, startDay, shift.getStopHour(), shift.getStopMinute()); //m6
        dockOut.add(Calendar.MINUTE, -15);
        GregorianCalendar graceOut = new GregorianCalendar(startYear, startMonth, startDay, shift.getStopHour(), shift.getStopMinute()); //m7
        graceOut.add(Calendar.MINUTE, -5);
        GregorianCalendar intOut = new GregorianCalendar(startYear, startMonth, startDay, shift.getStopHour(), shift.getStopMinute()); //m8
        intOut.add(Calendar.MINUTE, 15);
        GregorianCalendar lunchIn = new GregorianCalendar(startYear, startMonth, startDay, shift.getLunchStartHour(), shift.getLunchStartMinute()); //m9
        GregorianCalendar lunchOut = new GregorianCalendar(startYear, startMonth, startDay, shift.getLunchStopHour(), shift.getLunchStopMinute()); //m10
        if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY)) {
            if (eventtypeid == 1) {
                // Check Rules for clock in punches; Flip adjusted to True if rule applies
                if (original.after(intIn) && (original.before(graceIn))) {
                    adjusted = shiftSa;
                    adj = true;
                    eventData = "Shift Start";
                } else if ((original.after(graceIn)) && (original.before(dockIn))) {
                    adjusted = dockIn;
                    adj = true;
                    eventData = "Shift Dock";
                } else if ((original.after(lunchIn)) && (original.before(lunchOut))) {
                    adjusted = lunchOut;
                    adj = true;
                    eventData = "Lunch Stop";
            } else if (eventtypeid == 0) {
                // Check Rules for clock out punches; Flip adjusted to True if rule applies
                if ((original.after(lunchIn)) && (original.before(lunchOut))) {
                    adjusted = lunchIn;
                    adj = true;
                    eventData = "Lunch Start";
                } 
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                else if ((original.after(graceIn)) && (original.before(dockIn))) {
                    adjusted = dockOut;
                    adj = true;
                    eventData = "Shift Dock";
                } else if ((original.after(graceOut)) && (original.before(intOut))) {
                    adjusted = shiftSo;
                    adj = true;
                    eventData = "Shift Stop";
                }
            }
        }
        if (!adj) {
            adjusted = original;
            if (punchMinute % interval != 0) {
                if (punchMinute % interval < (interval / 2)) {
                    adjustedMin = (Math.round(punchMinute / interval) * interval); // Round DOWN
                    adjusted.add(Calendar.MINUTE, (adjustedMin - punchMinute));
                    adjusted.set(Calendar.SECOND, 0);
                } else {
                    adjusted = original;

                    adjustedMin = (Math.round(punchMinute / interval) * interval + interval);//Round Up
                    adjusted.add(Calendar.MINUTE, (adjustedMin - punchMinute));
                    adjusted.set(Calendar.SECOND, 0);
                }

                adjusted.set(Calendar.SECOND, 0);
                eventData = "Interval Round";
            } else {
                eventData = "None";
            }
        }// Apply adjustment to "ajustedtimestamp"\
        sdf2 = new SimpleDateFormat("EEE MM/dd/YYYY HH:mm:ss").format(adjusted.getTime()).toUpperCase();
    }

    /**
     * @param punchTypeId
     */
    public int getPunchTypeId() {
        return punchTypeId;
    }

    /**
     *
     * @return punchDescription
     */
    public String getPunchDescription() {
        return punchDescription;
    }

    /**
     *
     * @param punchDescription
     */
    public void setDescription(String punchDescription) {
        this.punchDescription = punchDescription;
    }

    /**
     *
     * @return punchId
     */
    public String getPunchId() {
        return punchId;
    }

    /**
     *
     * @param punchId
     */
    public void setPunchId(String punchId) {
        this.punchId = punchId;

    }

    /**
     *
     * @return badgeId
     */
    public String getBadgeId() {
        return badgeId;
    }

    /**
     *
     * @return terminalId
     */
    public int getTerminalId() {
        return terminalId;
    }

    /**
     *
     * @return shiftId
     */
    public int getShiftId() {
        return shiftId;
    }

    /**
     *
     * @return original
     */
    public GregorianCalendar getOriginaltimestamp() {
        return original;
    }

    /**
     *
     * @return adjusted
     */
    public GregorianCalendar getAdjustedTimeStamp() {
        return adjusted;
    }

    /**
     *
     * @return eventtypeid
     */
    public int geteventtypeid() {
        return eventtypeid;
    }

    /**
     *
     * @return String to output onto screen
     */
    public String printOriginalTimestamp() {
        String Status = "";
        if (eventtypeid == 1) {
            Status = " CLOCKED IN: ";
        } else if (eventtypeid == 0) {
            Status = " CLOCKED OUT: ";
        } else {
            Status = " TIMED OUT: ";
        }
        return "#" + badgeId + Status + sdf;
    }

    public String printAdjustedTimestamp() {
        String Status = "";
        if (eventtypeid == 1) {
            Status = " CLOCKED IN: ";
        } else if (eventtypeid == 0) {
            Status = " CLOCKED OUT: ";
        } else {
            Status = " TIMED OUT: ";
        }

        return "#" + badgeId + Status + sdf2 + " (" + eventData + ")";
    }

}
