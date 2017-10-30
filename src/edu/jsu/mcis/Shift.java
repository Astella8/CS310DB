package edu.jsu.mcis;


/**
 *
 * @author Wright Media
 */
public class Shift {
    
    private int shiftId;
    private String shiftDescription;
    private int start;
    private int stop;
    private int interval;
    private int gracePeriod;
    private int dockTime;
    private int lunchStart;
    private int lunchStop;
    private int lunchDeduct;
    private int maxTime;
    private int overtimeThreshold;
    
    /**
     * Creates Shift object
     */
    public Shift(){
        shiftId = 0;
        shiftDescription ="";
        start = 0;
        stop = 0;
        interval = 0;
        gracePeriod = 0;
        dockTime = 0;
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
    public Shift(int id, String desc, int start, int stop, int lunchStrt, int lunchEnd, int lunchDeduct, int maxTime) {
        this.shiftId = id;
        this.shiftDescription = desc;
        this.start = start;
        
        this.stop = stop;
        this.lunchStart = lunchStart;
        this.lunchStop = lunchEnd;
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
    public int getStart(){
        return start;
        
        }

    /**
     *
     * @param start
     */
    public void setStart(int start){
           this.start = start;
       }
       
    /**
     *
     * @return
     */
    public int getStop(){
           return stop;
       }
       
    /**
     *
     * @param stop
     */
    public void setStop(int stop){
           this.stop = stop;
       }

    /**
     *
     * @return
     */
    public int getInterval(){
           return interval;
       }
       
    /**
     *
     * @param interval
     */
    public void setInterval(int interval){
           this.interval = interval;
       }
       
    /**
     *
     * @return gracePeriod
     */
    public int getGracePeriod(){
           return gracePeriod;
       }
       
    /**
     *
     * @param gracePeriod
     */
    public void setGracePeriod(int gracePeriod){
           this.gracePeriod = gracePeriod;
       }
       
    /**
     *
     * @return dockTime
     */
    public int getDockTime(){
           return dockTime;
       }
       
    /**
     *
     * @param dockTime
     */
    public void setDockTime(int dockTime){
           this.dockTime = dockTime;
       }
        
    /**
     *
     * @return lunchStart
     */
    public int getLunchStart(){
           return lunchStart;
       }
       
    /**
     *
     * @param lunchStart
     */
    public void setLunchStart(int lunchStart){
           this.lunchStart = lunchStart;
       }
       
    /**
     *
     * @return lunchStop
     */
    public int getLunchStop(){
           return lunchStop;
       }
       
    /**
     *
     * @param lunchStop
     */
    public void setLunchStop(int lunchStop){
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