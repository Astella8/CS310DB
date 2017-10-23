package edu.jsu.mcis;


import org.junit.*;
import static org.junit.Assert.*;

public class Feature3 {
    
    private TASDatabase db;
    
    @Before
    public void setup() {
        db = new TASDatabase();
    }
    
    @Test
    public void testAdjustPunchesShift1Weekday() {
		
		/* Get Shift Ruleset and Punch Data */
        
        Shift s1 = db.getShift(1);

        Punch p1 = db.getPunch(3634);
        Punch p2 = db.getPunch(3687);
        Punch p3 = db.getPunch(3688);
        Punch p4 = db.getPunch(3716);
		
		/* Adjust Punches According to Shift Rulesets */
        
        p1.adjust(s1);
        p2.adjust(s1);
        p3.adjust(s1);
        p4.adjust(s1);
		
		/* Compare Adjusted Timestamps to Expected Values */

        assertEquals(p1.printOriginalTimestamp(), "#28DC3FB8 CLOCKED IN: FRI 09/08/2017 06:50:35");
        assertEquals(p1.printAdjustedTimestamp(), "#28DC3FB8 CLOCKED IN: FRI 09/08/2017 07:00:00 (Shift Start)");
        
        assertEquals(p2.printOriginalTimestamp(), "#28DC3FB8 CLOCKED OUT: FRI 09/08/2017 12:03:54");
        assertEquals(p2.printAdjustedTimestamp(), "#28DC3FB8 CLOCKED OUT: FRI 09/08/2017 12:00:00 (Lunch Start)");
        
        assertEquals(p3.printOriginalTimestamp(), "#28DC3FB8 CLOCKED IN: FRI 09/08/2017 12:23:41");
        assertEquals(p3.printAdjustedTimestamp(), "#28DC3FB8 CLOCKED IN: FRI 09/08/2017 12:30:00 (Lunch Stop)");

        assertEquals(p4.printOriginalTimestamp(), "#28DC3FB8 CLOCKED OUT: FRI 09/08/2017 15:34:13");
        assertEquals(p4.printAdjustedTimestamp(), "#28DC3FB8 CLOCKED OUT: FRI 09/08/2017 15:30:00 (Shift Stop)");
        
    }

    @Test
    public void testAdjustPunchesShift1Weekend() {
		
		/* Get Shift Ruleset and Punch Data */
        
        Shift s1 = db.getShift(1);

        Punch p1 = db.getPunch(1087);
        Punch p2 = db.getPunch(1162);
		
		/* Adjust Punches According to Shift Rulesets */
        
        p1.adjust(s1);
        p2.adjust(s1);
		
		/* Compare Adjusted Timestamps to Expected Values */

        assertEquals(p1.printOriginalTimestamp(), "#F1EE0555 CLOCKED IN: SAT 08/12/2017 05:54:58");
        assertEquals(p1.printAdjustedTimestamp(), "#F1EE0555 CLOCKED IN: SAT 08/12/2017 06:00:00 (Interval Round)");
        
        assertEquals(p2.printOriginalTimestamp(), "#F1EE0555 CLOCKED OUT: SAT 08/12/2017 12:04:02");
        assertEquals(p2.printAdjustedTimestamp(), "#F1EE0555 CLOCKED OUT: SAT 08/12/2017 12:00:00 (Interval Round)");
        
    }
    
    @Test
    public void testAdjustPunchesShift2Weekday() {
		
		/* Get Shift Ruleset and Punch Data */
        
        Shift s2 = db.getShift(2);

        Punch p1 = db.getPunch(4943);
        Punch p2 = db.getPunch(5004);
		
		/* Adjust Punches According to Shift Rulesets */
        
        p1.adjust(s2);
        p2.adjust(s2);
		
		/* Compare Adjusted Timestamps to Expected Values */

        assertEquals(p1.printOriginalTimestamp(), "#08D01475 CLOCKED IN: TUE 09/19/2017 11:59:33");
        assertEquals(p1.printAdjustedTimestamp(), "#08D01475 CLOCKED IN: TUE 09/19/2017 12:00:00 (Shift Start)");
        
        assertEquals(p2.printOriginalTimestamp(), "#08D01475 CLOCKED OUT: TUE 09/19/2017 21:30:27");
        assertEquals(p2.printAdjustedTimestamp(), "#08D01475 CLOCKED OUT: TUE 09/19/2017 21:30:27 (None)");
        
    }
    
    @Test
    public void testAdjustPunchesShift2Weekend() {
		
		/* Get Shift Ruleset and Punch Data */
        
        Shift s2 = db.getShift(2);

        Punch p1 = db.getPunch(5463);
        Punch p2 = db.getPunch(5541);
		
		/* Adjust Punches According to Shift Rulesets */
        
        p1.adjust(s2);
        p2.adjust(s2);
		
		/* Compare Adjusted Timestamps to Expected Values */

        assertEquals(p1.printOriginalTimestamp(), "#08D01475 CLOCKED IN: SAT 09/23/2017 05:49:00");
        assertEquals(p1.printAdjustedTimestamp(), "#08D01475 CLOCKED IN: SAT 09/23/2017 05:45:00 (Interval Round)");
        
        assertEquals(p2.printOriginalTimestamp(), "#08D01475 CLOCKED OUT: SAT 09/23/2017 12:04:15");
        assertEquals(p2.printAdjustedTimestamp(), "#08D01475 CLOCKED OUT: SAT 09/23/2017 12:00:00 (Interval Round)");
        
    }
    
}






