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
    public Shift(int shiftId, String shiftDescription, int interval, int gracePeriod, int dockTime, int lunchDeduct, int maxTime, int overtimeThreshold, int startHour, int startMinute, int lunchStartHour, int lunchStartMinute, int lunchStopHour, int lunchStopMinute, int stopHour, int stopMinute) {
        this.shiftId = shiftId;
        this.shiftDescription = shiftDescription;
        this.interval = interval;
        this.gracePeriod = gracePeriod;
        this.dockTime = dockTime;
        this.lunchDeduct = lunchDeduct;
        this.maxTime = maxTime;
        this.overtimeThreshold = overtimeThreshold;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.lunchStartHour = lunchStartHour;
        this.lunchStartMinute = lunchStartMinute;
        this.lunchStopHour = lunchStopHour;
        this.lunchStopMinute = lunchStopMinute;
        this.stopHour = stopHour;
        this.stopMinute = stopMinute;
    }
**/
    Shift(int shiftId, String desc, int lunchdeduct, int maxtime, int interval ,int startHour, int startMinute, int lunchStartHour, int lunchStartMinute, int lunchStopHour, int lunchStopMinute, int stopHour, int stopMinute) {
         this.shiftId = shiftId;
        this.shiftDescription = shiftDescription;
        this.gracePeriod = gracePeriod;
        this.dockTime = dockTime;
        this.lunchDeduct = lunchDeduct;
        this.maxTime = maxTime;
        this.interval = interval;
        this.overtimeThreshold = overtimeThreshold;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.lunchStartHour = lunchStartHour;
        this.lunchStartMinute = lunchStartMinute;
        this.lunchStopHour = lunchStopHour;
        this.lunchStopMinute = lunchStopMinute;
        this.stopHour = stopHour;
        this.stopMinute = stopMinute;
    }
    
    
    public int getShiftId() {
        return shiftId;
    }
    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }
    public String getShiftDescription() {
        return shiftDescription;
    }
    public void setShiftDescription(String shiftDescription) {
        this.shiftDescription = shiftDescription;
    }
    public int getInterval() {
        return interval;
    }
    public void setInterval(int interval) {
        this.interval = interval;
    }
    public int getGracePeriod() {
        return gracePeriod;
    }
    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }
    public int getDockTime() {
        return dockTime;
    }
    public void setDockTime(int dockTime) {
        this.dockTime = dockTime;
    }
    public int getLunchDeduct() {
        return lunchDeduct;
    }
    public void setLunchDeduct(int lunchDeduct) {
        this.lunchDeduct = lunchDeduct;
    }
    public int getMaxTime() {
        return maxTime;
    }
    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }
    public int getOvertimeThreshold() {
        return overtimeThreshold;
    }
    public void setOvertimeThreshold(int overtimeThreshold) {
        this.overtimeThreshold = overtimeThreshold;
    }
    public int getStartHour() {
        return startHour;
    }
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }
    public int getStartMinute() {
        return startMinute;
    }
    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }
    public int getLunchStartHour() {
        return lunchStartHour;
    }
    public void setLunchStartHour(int lunchStartHour) {
        this.lunchStartHour = lunchStartHour;
    }
    public int getLunchStartMinute() {
        return lunchStartMinute;
    }
    public void setLunchStartMinute(int lunchStartMinute) {
        this.lunchStartMinute = lunchStartMinute;
    }
    public int getLunchStopHour() {
        return lunchStopHour;
    }
    public void setLunchStopHour(int lunchStopHour) {
        this.lunchStopHour = lunchStopHour;
    }
    public int getLunchStopMinute() {
        return lunchStopMinute;
    }
    public void setLunchStopMinute(int lunchStopMinute) {
        this.lunchStopMinute = lunchStopMinute;
    }
    public int getStopHour() {
        return stopHour;
    }
    public void setStopHour(int stopHour) {
        this.stopHour = stopHour;
    }
    public int getStopMinute() {
        return stopMinute;
    }
    public void setStopMinute(int stopMinute) {
        this.stopMinute = stopMinute;
    }
    @Override
    public String toString() {
        String finalString;
        finalString = (shiftDescription + ": " + startHour + startMinute + " - " + stopHour + stopMinute + " (510 minutes); Lunch: " + lunchStartHour + lunchStartMinute + " - " + lunchStopHour + lunchStopMinute + " (30 minutes)");
        return finalString;
    }
}
