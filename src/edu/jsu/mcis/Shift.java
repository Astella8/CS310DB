package edu.jsu.mcis;


/**
 *
 * @author Wright Media
 */
public class Shift {
    
    private int shiftId;
    private String shiftDescription;
    private int interval;
    private int gracePeriod;
    private int dockTime;
    private int lunchDeduct;
    private int maxTime;
    private int overtimeThreshold;
    private int startHour;
    private int startMinute;
    private int lunchStartHour;
    private int lunchStartMinute;
    private int lunchStopHour;
    private int lunchStopMinute;
    private int stopHour;
    private int stopMinute;
    
    /**
     * Creates Shift object
     */
    public Shift(){
        shiftId = 0;
        shiftDescription ="";
        interval = 0;
        gracePeriod = 0;
        dockTime = 0;
        maxTime=0;
        overtimeThreshold =0;
        startHour = 0;
        startMinute = 0;
        lunchStartHour = 0;
        lunchStartMinute = 0;
        lunchStopHour = 0;
        lunchStopMinute = 0;
        stopHour = 0;
        stopMinute = 0;
        
    }

    /**
     *
     * @param id
     * @param desc
     * @param lunchDeduct
     * @param maxTime
     */
        
    

    Shift(int shiftId, String desc, int lunchdeduct, int maxtime, int startHour, int startMinute, int lunchStartHour, int lunchStartMinute, int lunchStopHour, int lunchStopMinute, int stopHour, int stopMinute) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        finalString = (shiftDescription + ": " + startHour + startMinute + " - "+ stopHour + stopMinute + " (510 minutes); Lunch: " + lunchStartHour + lunchStartMinute + " - " + lunchStopHour + lunchStopMinute + " (30 minutes)");
        return finalString;
       }
}