package com.test.IntervalCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputCheckAndManipulation {

	
	
	public List<Interval> checkForOverlapInputInterval(List<Interval> inputInterval) {
		
		// check for overlap between input intervals(if there is overlap form a new
		// interval with start - minValue and end-maxvalue among both

		List<Interval> outputIntervals = new ArrayList<Interval>();
		
		if(inputInterval.size() == 1) {
			return inputInterval;
		}

		for (int i = 0; i < inputInterval.size(); i++) {

			for (int j = 0; j < inputInterval.size(); j++) {

				if (!(inputInterval.get(j).getStart() == inputInterval.get(i).getStart()
						&& inputInterval.get(j).getEnd() == inputInterval.get(i).getEnd())) {
					// check for overlap
					if (!(inputInterval.get(j).getStart() > inputInterval.get(i).getEnd()
							|| inputInterval.get(i).getStart() > inputInterval.get(j).getEnd())) {
                        //there is overlap
						outputIntervals.add(new Interval(
								Math.min(inputInterval.get(i).getStart(), inputInterval.get(j).getStart()),
								Math.max(inputInterval.get(i).getEnd(), inputInterval.get(j).getEnd())));
						inputInterval.remove(i);
					} else {
						//there is no overlap so add the current object to output list
						outputIntervals.add(inputInterval.get(i));
					}
				}
			}
		}
		//remove duplicates from output list
		Set<Interval> outputSet = outputIntervals.stream().collect(Collectors.toSet());

		return new ArrayList<>(outputSet);
	}

	
	public void inputCheck(String str) throws InvalidInputException {
		
		//check for input format eg:10-100

		String pattern = "^(\\d+[-]\\d+[,]\\d+)(.*)";

		Pattern r = Pattern.compile(pattern);

		Matcher m = r.matcher(str);

		if (m.find()) {

			System.out.println("Valid input");

		} else {

			throw new InvalidInputException();
		}

	}
}
