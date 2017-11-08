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
        this.eventData = "";
        //String testdate =  format.format(adjusted.getTime());
        sdf = new SimpleDateFormat("EEE MM/dd/YYYY HH:mm:ss").format(original.getTime()).toUpperCase();
        /*
        gc = new GregorianCalendar(2017,9,25,7,0);
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
        GregorianCalendar lunchStop = new GregorianCalendar();
        **/
        
    }

    public Punch(String badgeId, int terminalId, int punchTypeId){
        this.badgeId = badgeId;
        this.terminalId = terminalId;
        this.punchTypeId = 0;
        original = new GregorianCalendar();
        adjusted = new GregorianCalendar();
        
    }
    
   /** public GregorianCalendar adjust(Shift shift){
        GregorianCalendar currentgc = new GregorianCalendar();
        GregorianCalendar shiftSa = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStartHour(), shift.getStartMinute()); //m1
        GregorianCalendar shiftSo = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStopHour(), shift.getStopMinute()); //m2
        GregorianCalendar intIn = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStartHour(), shift.getStartMinute()); //m3
        intIn.add(Calendar.MINUTE, -15);
        GregorianCalendar dockIn = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStartHour(), shift.getStartMinute()); //m4
        dockIn.add(Calendar.MINUTE, 15);
        GregorianCalendar graceIn = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStartHour(), shift.getStartMinute()); //m5
        graceIn.add(Calendar.MINUTE, 5);
        GregorianCalendar dockOut = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStopHour(), shift.getStopMinute()); //m6
        dockOut.add(Calendar.MINUTE, -15);
        GregorianCalendar graceOut = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStopHour(), shift.getStopMinute()); //m7
        graceOut.add(Calendar.MINUTE, -5);
        GregorianCalendar intOut = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStopHour(), shift.getStopMinute()); //m8
        intOut.add(Calendar.MINUTE, 15);
        GregorianCalendar lunchIn = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getLunchStartHour(), shift.getLunchStartMinute()); //m9
        GregorianCalendar lunchOut = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getLunchStopHour(), shift.getLunchStopMinute()); //m10
        if((original.after(intIn)) && (original.before(graceIn))) {
            adjusted = shiftSa;
            }
        if((original.after(graceIn)) && (original.before(dockIn))) {
            adjusted = dockIn;
            }
        if((original.after(lunchIn)) && (original.before(lunchOut)) && (eventtypeid == 0)){
            adjusted = lunchIn;
            }
        if((original.after(lunchIn)) && (original.before(lunchOut)) && (eventtypeid == 1)){
            adjusted = lunchOut;
            }
        if ((original.after(dockOut)) && (original.before(graceOut))){
            adjusted = dockOut;
            }       
        if ((original.after(graceOut)) && (original.before(intOut))) {
            adjusted = shiftSo;
        }
        else{
            int unroundedMinutes = original.get(Calendar.MINUTE);
            int mod = unroundedMinutes % 15;
            adjusted.add(Calendar.MINUTE, mod < 8 ? -mod : (15-mod));
        }
        
        return adjusted;
  }**/
    public void adjust(Shift shift) {
        boolean adj = false;
        GregorianCalendar gc = new GregorianCalendar();
        int punchMinute = original.get(Calendar.MINUTE);
        int interval = shift.getInterval();
        int day = original.get(Calendar.DAY_OF_WEEK);
        int adjustedMin;
        adjustedMin = 0;
        
        GregorianCalendar currentgc = new GregorianCalendar();
        GregorianCalendar shiftSa = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStartHour(), shift.getStartMinute()); //m1
        GregorianCalendar shiftSo = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStopHour(), shift.getStopMinute()); //m2
        GregorianCalendar intIn = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStartHour(), shift.getStartMinute()); //m3
        intIn.add(Calendar.MINUTE, -15);
        GregorianCalendar dockIn = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStartHour(), shift.getStartMinute()); //m4
        dockIn.add(Calendar.MINUTE, 15);
        GregorianCalendar graceIn = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStartHour(), shift.getStartMinute()); //m5
        graceIn.add(Calendar.MINUTE, 5);
        GregorianCalendar dockOut = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStopHour(), shift.getStopMinute()); //m6
        dockOut.add(Calendar.MINUTE, -15);
        GregorianCalendar graceOut = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStopHour(), shift.getStopMinute()); //m7
        graceOut.add(Calendar.MINUTE, -5);
        GregorianCalendar intOut = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getStopHour(), shift.getStopMinute()); //m8
        intOut.add(Calendar.MINUTE, 15);
        GregorianCalendar lunchIn = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getLunchStartHour(), shift.getLunchStartMinute()); //m9
        GregorianCalendar lunchOut = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, shift.getLunchStopHour(), shift.getLunchStopMinute()); //m10
        if((original.after(intIn)) && (original.before(graceIn))) {
            adjusted = shiftSa;
            }
        if((original.after(graceIn)) && (original.before(dockIn))) {
            adjusted = dockIn;
            }
        if((original.after(lunchIn)) && (original.before(lunchOut)) && (eventtypeid == 0)){
            adjusted = lunchIn;
            }
        if((original.after(lunchIn)) && (original.before(lunchOut)) && (eventtypeid == 1)){
            adjusted = lunchOut;
            }
        if ((original.after(dockOut)) && (original.before(graceOut))){
            adjusted = dockOut;
            }       
        if ((original.after(graceOut)) && (original.before(intOut))) {
            adjusted = shiftSo;
        }
        else{
            int unroundedMinutes = original.get(Calendar.MINUTE);
            int mod = unroundedMinutes % 15;
            adjusted.add(Calendar.MINUTE, mod < 8 ? -mod : (15-mod));
        }
        
        // Generate Gregorian Calendar Objects
        if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY)){
            
            if (punchTypeId == 1) {
                // Check Rules for clock in punches; Flip adjusted to True if rule applies
                
                
            }
            else if (punchTypeId == 0) {
                // Check Rules for clock out punches; Flip adjusted to True if rule applies
                
            }
        }
        if (!adj) {
            if (punchMinute % interval != 0) {
                if (punchMinute % interval < (interval/2)) {
                    adjustedMin = (Math.round(punchMinute / interval) * interval); // Round DOWN
                }
                else {
                    adjustedMin = (Math.round(punchMinute / interval) * interval);//Round Up
                }
                adjusted.add(Calendar.MINUTE,(adjustedMin - punchMinute)); 
                adjusted.set(Calendar.SECOND,0);
                eventData = "Interval Round";
            }
            else {
                eventData = "None";
            }
        }// Apply adjustment to "ajustedtimestamp"
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
        String AdjustedTimeStamp = "Laugh";
        
        return null;
    }


}
