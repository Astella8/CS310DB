package edu.jsu.mcis;

import java.util.*;
import java.text.SimpleDateFormat;

public class Punch {

    private int shiftId;
    private String punchId;
    private String badgeId;
    private String punchDescription;
    private int terminalId;
    private GregorianCalendar original;
    private GregorianCalendar adjusted;
    private String sdf;

    public Punch(int terminalId, String badgeId, int shiftId, long originalts, long adjustedts) {
        original = new GregorianCalendar();
        adjusted = new GregorianCalendar();
        originalts = (originalts * 1000);
        adjustedts = (originalts * 1000);
        original.setTimeInMillis(originalts);
        adjusted.setTimeInMillis(adjustedts);
        this.terminalId = terminalId;
        this.badgeId = badgeId;
        this.shiftId = shiftId;
        //String testdate =  format.format(adjusted.getTime());
        sdf = new SimpleDateFormat("EEE MM/dd/YYYY HH:mm:ss").format(original.getTime()).toUpperCase();
    }

    public String getPunchDescription() {
        return punchDescription;
    }

    public void setDescription(String punchDescription) {
        this.punchDescription = punchDescription;
    }

    public String getPunchId() {
        return punchId;
    }

    public void setPunchId(String punchId) {
        this.punchId = punchId;

    }

    public String getBadgeId() {
        return badgeId;
    }

    public int getTerminalId() {
        return terminalId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public GregorianCalendar getOriginalTimeStamp() {
        return adjusted;
    }

    public GregorianCalendar getAdjustedTimeStamp() {
        return adjusted;
    }

    public String printOriginalTimestamp() {
        String Status = "";
        if (shiftId == 1) {
            Status = " CLOCKED IN: ";
        } else if (shiftId == 0) {
            Status = " CLOCKED OUT: ";
        } else {
            Status = " TIMED OUT: ";
        }
        return "#" + badgeId + Status + sdf;
    }

}
