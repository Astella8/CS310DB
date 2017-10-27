package edu.jsu.mcis;


/**
 *
 * @author Wright Media
 */
public class Shift {
    
    private int shiftId;
    private String shiftDescription;
    private String start;
    private String stop;
    private String interval;
    private String gracePeriod;
    private String dockTime;
    private String lunchStart;
    private String lunchStop;
    private int lunchDeduct;
    private int maxTime;
    private int overtimeThreshold;
    
    /**
     * Creates Shift object
     */
    public Shift(){
        shiftId = 0;
        shiftDescription ="";
        start = "";
        stop = "";
        interval = "";
        gracePeriod ="";
        dockTime = "";
        maxTime=0;
        overtimeThreshold =0;
    }

    /**
     *
     * @param id
     * @param desc
     * @param start
     * @param stop
     * @param lunchStrt
     * @param lunchEnd
     * @param lunchDeduct
     * @param maxTime
     */
    public Shift(String id, String desc, String start, String stop, String lunchStrt, String lunchEnd, int lunchDeduct, int maxTime) {
        this.shiftId = id;
        this.shiftDescription = desc;
        this.start = start.substring(0, 5);
        this.stop = stop.substring(0,5);
        this.lunchStart = lunchStrt.substring(0,5);
        this.lunchStop = lunchEnd.substring(0,5);
        this.lunchDeduct = lunchDeduct;
        this.maxTime = maxTime;
        
    }
    
    /**
     *
     * @return
     */
    public int getShiftId(){
        return shiftId;
        
        }

    /**
     *
     * @param shiftId
     */
    public void setShiftId(int shiftId){
           this.shiftId = shiftId;
       }
       
    /**
     *
     * @return
     */
    public String getShiftDescription(){
           return shiftDescription;
       }
       
    /**
     *
     * @param shiftDescription
     */
    public void setShiftDescription(String shiftDescription){
           this.shiftDescription = shiftDescription;
       }

    /**
     *
     * @return
     */
    public String getStart(){
        return start;
        
        }

    /**
     *
     * @param start
     */
    public void setStart(String start){
           this.start = start;
       }
       
    /**
     *
     * @return
     */
    public String getStop(){
           return stop;
       }
       
    /**
     *
     * @param stop
     */
    public void setStop(String stop){
           this.stop = stop;
       }

    /**
     *
     * @return
     */
    public String getInterval(){
           return interval;
       }
       
    /**
     *
     * @param interval
     */
    public void setInterval(String interval){
           this.interval = interval;
       }
       
    /**
     *
     * @return gracePeriod
     */
    public String getGracePeriod(){
           return gracePeriod;
       }
       
    /**
     *
     * @param gracePeriod
     */
    public void setGracePeriod(String gracePeriod){
           this.gracePeriod = gracePeriod;
       }
       
    /**
     *
     * @return dockTime
     */
    public String getDockTime(){
           return dockTime;
       }
       
    /**
     *
     * @param dockTime
     */
    public void setDockTime(String dockTime){
           this.dockTime = dockTime;
       }
        
    /**
     *
     * @return lunchStart
     */
    public String getLunchStart(){
           return lunchStart;
       }
       
    /**
     *
     * @param lunchStart
     */
    public void setLunchStart(String lunchStart){
           this.lunchStart = lunchStart;
       }
       
    /**
     *
     * @return lunchStop
     */
    public String getLunchStop(){
           return lunchStop;
       }
       
    /**
     *
     * @param lunchStop
     */
    public void setLunchStop(String lunchStop){
           this.lunchStop = lunchStop;
       }
        
    /**
     *
     * @return lunchDeduct
     */
    public int getLunchDeduct(){
           return lunchDeduct;
       }
       
    /**
     *
     * @param lunchDeduct
     */
    public void setLunchDeduct(int lunchDeduct){
           this.lunchDeduct = lunchDeduct;
       }
       
    /**
     *
     * @return maxTime
     */
    public int getMaxTime(){
           return maxTime;
       }
       
    /**
     *
     * @param maxTime
     */
    public void setMaxTime(int maxTime){
           this.maxTime = maxTime;
       }
       
    /**
     *
     * @return overtimeThreshold
     */
    public int getOvertimeThreshold(){
           return overtimeThreshold;
       }
       
    /**
     *
     * @param overtimeThreshold
     */
    public void setOvertimeThreshold(int overtimeThreshold){
           this.overtimeThreshold = overtimeThreshold;
       }
    /**
     * 
     * @return finalString 
     */
    @Override
    public String toString(){
        String finalString;
        finalString = (shiftDescription + ": " + start + " - "+ stop + " (510 minutes); Lunch: " + lunchStart + " - " + lunchStop + " (30 minutes)");
        return finalString;
       }
}