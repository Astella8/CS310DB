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
    private String adjustDescription;

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
        /*
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
        GregorianCalendar LunchStart = new GregorianCalendar();
        GregorianCalendar LunchStop = new GregorianCalendar();
        **/
        
    }

    public Punch(String badgeId, int terminalId, int punchTypeId){
        this.badgeId = badgeId;
        this.terminalId = terminalId;
        this.punchTypeId = 0;
        original = new GregorianCalendar();
        adjusted = new GregorianCalendar();
        
    }
    
    public GregorianCalendar adjust(Shift shift){
        GregorianCalendar start = new GregorianCalendar(original.get(Calendar.YEAR), original.get(Calendar.MONTH), original.get(Calendar.DAY_OF_MONTH), shift.getStart().getHours(), shift.getStart().getMinutes());
        GregorianCalendar stop = new GregorianCalendar(original.get(Calendar.YEAR), original.get(Calendar.MONTH), original.get(Calendar.DAY_OF_MONTH), shift.getStop().getHours(), shift.getStop().getMinutes());
        GregorianCalendar LunchStart = new GregorianCalendar(original.get(Calendar.YEAR), original.get(Calendar.MONTH), original.get(Calendar.DAY_OF_MONTH), shift.getLunchStart().getHours(), shift.getLunchStart().getMinutes());
        GregorianCalendar LunchStop = new GregorianCalendar(original.get(Calendar.YEAR), original.get(Calendar.MONTH), original.get(Calendar.DAY_OF_MONTH), shift.getLunchStop().getHours(), shift.getLunchStop().getMinutes());
        
        if(original.get(Calendar.DAY_OF_WEEK) == 7 || original.get(Calendar.DAY_OF_WEEK) == 1){
            adjusted.setTimeInMillis(original.getTimeInMillis());
            adjusted.set(Calendar.SECOND, 0);
            if(original.get(Calendar.MINUTE) % 15 != 0){
                if(original.get(Calendar.MINUTE) % 15 <= 15/2){
                    adjusted.set(Calendar.MINUTE, original.get(Calendar.MINUTE) - original.get(Calendar.MINUTE) % 15);
                }
                else{
                    adjusted.set(Calendar.MINUTE, original.get(Calendar.MINUTE) + (15 - original.get(Calendar.MINUTE) % 15));
                }
            }
            adjustDescription = " (Interval Round)";
        }
        else if(eventtypeid == 1){
            if(original.getTimeInMillis() > LunchStart.getTimeInMillis()){
                adjusted.setTimeInMillis(LunchStop.getTimeInMillis());
                adjustDescription = " (Lunch Stop)";
            }
            else if((original.get(Calendar.MINUTE) <= (start.get(Calendar.MINUTE) + shift.getGracePeriod())) && (original.get(Calendar.HOUR_OF_DAY) == start.get(Calendar.HOUR_OF_DAY))){
                adjusted.setTimeInMillis(start.getTimeInMillis());
                adjustDescription = " (Shift Start)";
            }
            else if((original.get(Calendar.MINUTE) >= (start.get(Calendar.MINUTE) + (60 - shift.getDockTime())))  && (original.get(Calendar.HOUR_OF_DAY) == start.get(Calendar.HOUR_OF_DAY) -1)){
                adjusted.setTimeInMillis(start.getTimeInMillis());
                adjustDescription = " (Shift Start)";
            }
            else if((original.getTimeInMillis() > (start.get(Calendar.MINUTE) + shift.getGracePeriod())) && (original.getTimeInMillis() <= start.get(Calendar.MINUTE) + shift.getDockTime())){
                adjusted.setTimeInMillis(start.getTimeInMillis());
                adjusted.roll(Calendar.MINUTE, shift.getDockTime());
                adjustDescription = " (Shift Start)";
            }
            else{
                adjusted.setTimeInMillis(original.getTimeInMillis());
                if(original.get(Calendar.MINUTE) % 15 != 0){
                    adjusted.set(Calendar.SECOND, 0);
                    if(original.get(Calendar.MINUTE) % 15 <= 15/2){
                        adjusted.set(Calendar.MINUTE, original.get(Calendar.MINUTE) - original.get(Calendar.MINUTE) % 15); 
                    }
                    else{
                        adjusted.set(Calendar.MINUTE, original.get(Calendar.MINUTE) + (15 - original.get(Calendar.MINUTE) % 15));
                    }
                    adjustDescription = " (Shift Start)";
                }
                else {adjustDescription = " (None)";}
            }
        }
        else if(eventtypeid == 0){
            if(original.getTimeInMillis() < LunchStop.getTimeInMillis()){
                adjusted.setTimeInMillis(LunchStart.getTimeInMillis());
                adjustDescription = " (Lunch Start)";
            }
            else if((original.get(Calendar.MINUTE) >= (60 - shift.getGracePeriod())) && (original.get(Calendar.HOUR_OF_DAY) == stop.get(Calendar.HOUR_OF_DAY) - 1)){
                adjusted.setTimeInMillis(stop.getTimeInMillis());
                adjustDescription = " (Shift Stop)";
            }
            else if((original.get(Calendar.MINUTE) <= (stop.get(Calendar.MINUTE) + shift.getDockTime())) && (original.get(Calendar.HOUR_OF_DAY) == stop.get(Calendar.HOUR_OF_DAY))){
                adjusted.setTimeInMillis(stop.getTimeInMillis());
                adjustDescription = " (Shift Stop)";
            }
            else if((original.getTimeInMillis() < (60 - shift.getGracePeriod())) && (original.getTimeInMillis() >= (60 - shift.getDockTime()))){
                adjusted.setTimeInMillis(stop.getTimeInMillis());
                adjusted.roll(Calendar.MINUTE, 60 - shift.getDockTime());
                adjustDescription = " (Shift Stop)";
            }
            else{
                adjusted.setTimeInMillis(original.getTimeInMillis());
                if(original.get(Calendar.MINUTE) % 15 != 0){
                    adjusted.set(Calendar.SECOND, 0);
                    if(original.get(Calendar.MINUTE) % 15 <= 15/2){
                        adjusted.set(Calendar.MINUTE, original.get(Calendar.MINUTE) - original.get(Calendar.MINUTE) % 15);
                    }
                    else{
                        adjusted.set(Calendar.MINUTE, original.get(Calendar.MINUTE) + (15 - original.get(Calendar.MINUTE) % 15));
                    }
                    adjustDescription= " (Shift Stop)";
                }
                else {adjustDescription = " (None)";}
            }
        }
        
    }

    
    
    /**
     * @return 
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
    public GregorianCalendar getOriginalTimestamp() {
        return original;
    }

    /**
     *
     * @return adjusted
     */
    public GregorianCalendar getAdjustedTimestamp() {
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
    public String printoriginal() {
        String Status = "";
        switch (eventtypeid) {
            case 1:
                Status = " CLOCKED IN: ";
                break;
            case 0:
                Status = " CLOCKED OUT: ";
                break;
            default:
                Status = " TIMED OUT: ";
                break;
        }
        System.out.println("#" + badgeId + Status + sdf);
        return "#" + badgeId + Status + sdf;
    }
    
    public String printadjusted(){
        String Status = "";
        switch (eventtypeid) {
            case 1:
                Status = " CLOCKED IN: ";
                break;
            case 0:
                Status = " CLOCKED OUT: ";
                break;
            default:
                Status = " TIMED OUT: ";
                break;
        }
        System.out.println("#" + badgeId + Status + sdf);
        return "#" + badgeId + Status + sdf;
        
    }


}
