package edu.jsu.mcis;

import java.util.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author Braden
 */
public class Punch {

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
    }

    public Punch(String badgeId, int terminalId, int punchTypeId){
        this.badgeId = badgeId;
        this.terminalId = terminalId;
        this.punchTypeId = 0;
        original = new GregorianCalendar();
        adjusted = new GregorianCalendar();
        
    }
    
    public GregorianCalendar adjust(long originalts, long adjustedts){
    adjusted = new GregorianCalendar();
    adjustedts = (originalts * 1000);
    adjusted.setTimeInMillis(adjustedts);
        
    
    
    return adjusted;
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


