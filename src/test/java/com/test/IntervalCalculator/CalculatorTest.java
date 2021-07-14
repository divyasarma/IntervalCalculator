package com.test.IntervalCalculator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	Calculator calc = new Calculator();
	List<Interval> includeInterval = new ArrayList<Interval>();
	List<Interval> excludeInterval = new ArrayList<Interval>();
	List<Interval> outputInterval = new ArrayList<Interval>();
	List<Interval> excludeEmptyInterval = new ArrayList<Interval>();
	
	@Before
	public void initTest() {
		includeInterval.add(new Interval(10, 100));
		excludeInterval.add(new Interval(20, 30));
		outputInterval.add(new Interval(10, 19));
		outputInterval.add(new Interval(31, 100));

	}
	@Test
	public void testfindNonOverlappingIntervals() {
		assertNotNull(calc.findNonOverlappingIntervals(includeInterval, excludeInterval));
		assertArrayEquals(outputInterval.toArray(), calc.findNonOverlappingIntervals(includeInterval, excludeInterval).toArray());
		assertArrayEquals(includeInterval.toArray(), calc.findNonOverlappingIntervals(includeInterval, excludeEmptyInterval).toArray());
		
	}
	
	@Test
	public void testsplitIntoNonOverlappingIntervals() {
		
		assertNotNull(calc.splitIntoNonOverlappingIntervals(new Interval(10, 100), new Interval(20, 30)));
		List<Interval> otp = new ArrayList<Interval>();
    	otp.add(new Interval(10,19));
    	otp.add(new Interval(31,100));
		assertArrayEquals(otp.toArray(), calc.splitIntoNonOverlappingIntervals(new Interval(10, 100), new Interval(20, 30)).toArray());
		List<Interval> otp1 = new ArrayList<Interval>();
    	otp1.add(new Interval(31,100));
		assertArrayEquals(otp1.toArray(), calc.splitIntoNonOverlappingIntervals(new Interval(10, 100), new Interval(10, 30)).toArray());
		List<Interval> otp2 = new ArrayList<Interval>();
    	otp2.add(new Interval(10,69));
		assertArrayEquals(otp2.toArray(), calc.splitIntoNonOverlappingIntervals(new Interval(10, 100), new Interval(70, 100)).toArray());
		
	}
}
