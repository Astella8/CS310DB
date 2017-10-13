package edu.jsu.mcis;

import java.util.*;

public class Punch {
	
	private int shiftId;
        private String punchId;
	private String badgeId;
        private String punchDescription;
	private int terminalId;
	private GregorianCalendar original;
	private GregorianCalendar adjusted;
	
		public Punch(int terminalId, String badgeId, int shiftId, long originalts, long adjustedts){
		
			original = new GregorianCalendar();
			adjusted = new GregorianCalendar();
                        original.setTimeInMillis(originalts);
                        adjusted.setTimeInMillis(adjustedts);
			this.terminalId = terminalId;
			this.badgeId = badgeId;
			this.shiftId = shiftId;
		}
		
		//Add Getters

   public String getPunchDescription(){
       return punchDescription;
   }
   public void setDescription(String punchDescription){
       this.punchDescription = punchDescription;
   } 
   public String getPunchId(){
       return punchId;
   }
   public void setPunchId(String punchId){
       this.punchId = punchId;
   }
   public String getBadgeId(){
       return badgeId;
   }
   public int getTerminalId(){
       return terminalId;
   }
   public int getShiftId(){
       return shiftId;
   }
    public GregorianCalendar getOriginal(){
       return original;
   }
    public GregorianCalendar getAdjusted(){
       return adjusted;
   }
	
}