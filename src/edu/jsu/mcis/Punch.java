package edu.jsu.mcis;

import java.util.*;
import java.text.SimpleDateFormat;

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
    private int terminalId;
    private GregorianCalendar original;
    private GregorianCalendar adjusted;
    private String sdf;
    private int eventtypeid;
    private int punchTypeId;
    private int timestamp;

    /**
     *
     * @param terminalId
     * @param badgeId
     * @param shiftId
     * @param originalts
     * @param adjustedts
     * @param eventtypeid
     */
    public Punch(int terminalId, String badgeId, int shiftId, long originalts, long adjustedts, int eventtypeid) {
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
        //String testdate =  format.format(adjusted.getTime());
        sdf = new SimpleDateFormat("EEE MM/dd/YYYY HH:mm:ss").format(original.getTime()).toUpperCase();
        GregorianCalendar gc = new GregorianCalendar(2017,9,25,7,0);
        GregorianCalendar graceIn = new GregorianCalendar();
        graceIn.setTimeInMillis(gc.getTimeInMillis());
        graceIn.roll(Calendar.MINUTE, 5);
        GregorianCalendar dockIn = new GregorianCalendar();
        dockIn.setTimeInMillis(gc.getTimeInMillis());
        dockIn.roll(Calendar.MINUTE, 15);
        GregorianCalendar graceOut = new GregorianCalendar();
        graceOut.setTimeInMillis(gc.getTimeInMillis());
        graceOut.roll(Calendar.MINUTE, -5);
        GregorianCalendar dockOut = new GregorianCalendar();
        dockOut.setTimeInMillis(gc.getTimeInMillis());
        dockOut.roll(Calendar.MINUTE, -15);
        //Lunch Start and Stop not set to times yet
        GregorianCalendar lunchStart = new GregorianCalendar();
        GregorianCalendar lunchStop = new GregorianCalendar();84
        
    }

    public Punch(String badgeId, int terminalId, int punchTypeId){
        this.badgeId = badgeId;
        this.terminalId = terminalId;
        this.punchTypeId = 0;
        original = new GregorianCalendar();
        adjusted = new GregorianCalendar();
        
    }
    
    public GregorianCalendar adjust(Shift shift){
        String shiftStart = shift.getStart();
        
        GregorianCalendar lunchIn = new GregorianCalendar();
        GregorianCalendar lunchOut = new GregorianCalendar();
        GregorianCalendar dockIn = new GregorianCalendar();
        GregorianCalendar dockOut = new GregorianCalendar();
        GregorianCalendar graceInsa = new GregorianCalendar();
        GregorianCalendar graceOutsa = new GregorianCalendar();
        GregorianCalendar graceInso = new GregorianCalendar();
        GregorianCalendar graceOutso = new GregorianCalendar();
        GregorianCalendar shiftSa = new GregorianCalendar();
        GregorianCalendar shiftSo = new GregorianCalendar();
        
        lunchIn = shift.getStart();
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
    public int geteventtypeid(){
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
    
    public String printAdjustedTimestamp(){
        
        
        return null;
    }


}


