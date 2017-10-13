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
    
    private int shiftId;
    private String shiftDescription;
    private String start;
    private String stop;
    private String interval;
    private String gracePeriod;
    private String dock;
    private String lunchStart;
    private String lunchStop;
    private String lunchDeduct;
    private String maxTime;
    private String overtimeThreshold;
    

    public Shift(){
        shiftId = 0;
        shiftDescription ="";
        start = "";
        stop = "";
        interval = "";
        gracePeriod ="";
        dock ="";
        lunchStart="";
        lunchStop="";
        lunchDeduct="";
        maxTime="";
        overtimeThreshold ="";
    }
    
      public int getShiftId(){
        return shiftId;
        
        }
       public void setShiftId(int shiftId){
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
        
       public String getLunchDeduct(){
           return lunchDeduct;
       }
       
       public void setLunchDeduct(String lunchDeduct){
           this.lunchDeduct = lunchDeduct;
       }
       
       public String getMaxTime(){
           return maxTime;
       }
       
       public void setMaxTime(String maxTime){
           this.maxTime = maxTime;
       }
       
       public String getOvertimeThreshold(){
           return overtimeThreshold;
       }
       
       public void setOvertimeThreshold(String overtimeThreshold){
           this.overtimeThreshold = overtimeThreshold;
       }
}
