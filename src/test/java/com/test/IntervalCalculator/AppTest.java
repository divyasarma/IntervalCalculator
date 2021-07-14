package com.test.IntervalCalculator;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
	
	App app = new App();
	
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    public void testformatInput() {
    	assertEquals("10-100,20-30", app.formatInput(" 10-100, 20-30 "));
    }
    
    @Test
    public void testsplitInputString() {
    	List<Interval> otp = new ArrayList<Interval>();
    	otp.add(new Interval(10,100));
    	otp.add(new Interval(20,30));
    	List<Interval> otp1 = new ArrayList<Interval>();
    	otp1.add(new Interval(10,100));
    	assertEquals(otp1, app.splitInputString("10-100"));
    	otp1.get(0).setStart(10);
    	otp1.get(0).setEnd(100);
    	assertEquals("10-100",otp1.get(0).toString());
    }
}
