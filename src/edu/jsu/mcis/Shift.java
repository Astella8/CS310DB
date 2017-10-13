package edu.jsu.mcis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wright Media
 */
public class Shift {
    
    private String shiftId;
    private String shiftDescription;
    private String start;
    private String stop;
    private String interval;
    private String gracePeriod;
    private String dock;
    private String lunchStart;
    private String lunchStop;
    private int lunchDeduct;
    private int maxTime;
    private int overtimeThreshold;
    

    public Shift(){
        shiftId = String.valueOf(0);
        shiftDescription ="";
        start = "";
        stop = "";
        interval = "";
        gracePeriod ="";
        dock ="";
        lunchStart="";
        lunchStop="";
        lunchDeduct=0;
        maxTime=0;
        overtimeThreshold =0;
    }
    public Shift(String id, String desc, String start, String stop, String lunchStrt, String lunchEnd, int lunchDeduct, int maxTime) {
        this.shiftId = id;
        this.shiftDescription = desc;
        this.start = start;
        this.stop = stop;
        this.lunchStart = lunchStrt;
        this.lunchStop = lunchEnd;
        this.lunchDeduct = lunchDeduct;
        this.maxTime = maxTime;
        
    }
    
      public String getShiftId(){
        return shiftId;
        
        }
       public void setShiftId(String shiftId){
           this.shiftId = shiftId;
       }
       
       public String getShiftDescription(){
           return shiftDescription;
       }
       
       public void setShiftDescription(String shiftDescription){
           this.shiftDescription = shiftDescription;
       }
       public String getStart(){
        return start;
        
        }
       public void setStart(String start){
           this.start = start;
       }
       
       public String getStop(){
           return stop;
       }
       
       public void setStop(String stop){
           this.stop = stop;
       }
        public String getInterval(){
           return interval;
       }
       
       public void setInterval(String interval){
           this.interval = interval;
       }
       
       public String getGracePeriod(){
           return gracePeriod;
       }
       
       public void setGracePeriod(String gracePeriod){
           this.gracePeriod = gracePeriod;
       }
       
        public String getDock(){
           return dock;
       }
       
       public void setDock(String dock){
           this.dock = dock;
       }
        
       public String getLunchStart(){
           return lunchStart;
       }
       
       public void setLunchStart(String lunchStart){
           this.lunchStart = lunchStart;
       }
       
        public String getLunchStop(){
           return lunchStop;
       }
       
       public void setLunchStop(String lunchStop){
           this.lunchStop = lunchStop;
       }
        
       public int getLunchDeduct(){
           return lunchDeduct;
       }
       
       public void setLunchDeduct(int lunchDeduct){
           this.lunchDeduct = lunchDeduct;
       }
       
       public int getMaxTime(){
           return maxTime;
       }
       
       public void setMaxTime(int maxTime){
           this.maxTime = maxTime;
       }
       
       public int getOvertimeThreshold(){
           return overtimeThreshold;
       }
       
       public void setOvertimeThreshold(int overtimeThreshold){
           this.overtimeThreshold = overtimeThreshold;
       }
}
