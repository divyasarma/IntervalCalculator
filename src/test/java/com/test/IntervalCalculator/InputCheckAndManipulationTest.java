package com.test.IntervalCalculator;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class InputCheckAndManipulationTest {

	InputCheckAndManipulation inp = new InputCheckAndManipulation();
	List<Interval> inputInterval = new ArrayList<Interval>();

	@Before
	public void initTest() {
		inputInterval.add(new Interval(10, 100));
		inputInterval.add(new Interval(200, 300));

	}

	@Test
	public void testcheckForOverlapInputInterval() {
		assertNotNull(inp.checkForOverlapInputInterval(inputInterval));
		assertEquals(inputInterval, inp.checkForOverlapInputInterval(inputInterval));
	}

	@Test
	public void testinputCheckInvalid() {
      assertThrows(InvalidInputException.class, () -> inp.inputCheck("10--100"));
	}
	
	@Test
	public void testinputCheckValid() {
      assertDoesNotThrow( () -> inp.inputCheck("10-100,200-300"));
	}
	
	@Test
	public void testcheckForOverlapInputInterval1() {
		List<Interval> otp = new ArrayList<Interval>();
    	otp.add(new Interval(10,100));
    	otp.add(new Interval(20,30));
    	List<Interval> otp1 = new ArrayList<Interval>();
    	otp1.add(new Interval(10,100));
		assertArrayEquals(otp1.toArray(), inp.checkForOverlapInputInterval(otp).toArray());
	}

}
