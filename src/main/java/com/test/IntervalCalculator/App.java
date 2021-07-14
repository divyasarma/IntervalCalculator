package com.test.IntervalCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 
 *
 */
public class App {
 
	public static void main(String[] args) throws InvalidInputException {

		List<Interval> includeInterval = new ArrayList<Interval>();
		List<Interval> excludeInterval = new ArrayList<Interval>();

		InputCheckAndManipulation inp = new InputCheckAndManipulation();

		// Take input from console using scanner
		Scanner sc = new Scanner(System.in);

		System.out.println("Please enter include intervals(eg: 10-100,200-300) :");
		String include = sc.nextLine();
        
		//format include interval input
		include = formatInput(include);
		
		System.out.println("Please enter exclude intervals(eg: 10-100,200-300) :");
		String exclude = sc.nextLine();
		
		//format exclude interval input
		exclude = formatInput(exclude);
		sc.close();
		

		if (include.isEmpty())
			throw new InvalidInputException();

		// convert into Interval array from string input
		
		includeInterval = splitInputString(include);
		excludeInterval = splitInputString(exclude);
		
		// sort includeInterval and excludeInterval array
		Collections.sort(includeInterval, Comparator.comparingInt(Interval::getStart));
		Collections.sort(excludeInterval, Comparator.comparingInt(Interval::getStart));

		// find nonOverlapping Intervals and print it as output
		Calculator calc = new Calculator();

		List<Interval> outputList = calc.findNonOverlappingIntervals(inp.checkForOverlapInputInterval(includeInterval),
				inp.checkForOverlapInputInterval(excludeInterval));

		Collections.sort(outputList, Comparator.comparingInt(Interval::getStart));
		
		//print output
		System.out.println("Output:");
		StringBuilder sb = new StringBuilder();

		outputList.stream().forEach(e -> sb.append(e.toString() + ","));
		System.out.print(sb.substring(0, sb.length() - 1));

	}
	
	public static String formatInput(String str) {
		
		InputCheckAndManipulation inp = new InputCheckAndManipulation();
		
		//remove unwanted spaces from input and check for the pattern match
		str.trim();
		str = str.replaceAll(" ", "");

		try {
			if ((!str.isEmpty()) && (str.split(",").length > 1))
				inp.inputCheck(str);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public static List<Interval> splitInputString(String inputStr) {
		
		List<Interval> outputInterval = new ArrayList<Interval>();
				
		//split comma separated values from input and add it to a list of interval objects
		
		if ((!inputStr.isEmpty()) && (inputStr.split(",").length > 1)) {
			for (String str : inputStr.split(",")) {
				outputInterval
						.add(new Interval(Integer.parseInt(str.split("-")[0]), Integer.parseInt(str.split("-")[1])));
			}

		} else {
			if (inputStr.contains("-"))
				outputInterval.add(
						new Interval(Integer.parseInt(inputStr.split("-")[0]), Integer.parseInt(inputStr.split("-")[1])));

		}
		return outputInterval;
	}
}
