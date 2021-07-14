package com.test.IntervalCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Calculator {

	public List<Interval> findNonOverlappingIntervals(List<Interval> includeInterval, List<Interval> excludeInterval) {

		List<List<Interval>> interIntervals = new ArrayList<List<Interval>>();
		List<Interval> outputIntervals = new ArrayList<Interval>();
		List<Integer> startList = new ArrayList<Integer>();

		int counter = 0;
		
		//sort includeInterval and excludeInterval array
				Collections.sort(includeInterval, Comparator.comparingInt(Interval :: getStart));
				Collections.sort(excludeInterval, Comparator.comparingInt(Interval :: getStart));

		// if exclude interval set is empty
		if (excludeInterval.isEmpty()) {

			return includeInterval;

		} else {

			for (int i = 0; i < includeInterval.size(); i++) {

				for (int j = 0; j < excludeInterval.size(); j++) {

					// check for overlap
					if (!(excludeInterval.get(j).getStart() > includeInterval.get(i).getEnd()
							|| includeInterval.get(i).getStart() > excludeInterval.get(j).getEnd())) {

						counter++;
						startList.add(includeInterval.get(i).getStart());
						interIntervals
								.add(splitIntoNonOverlappingIntervals(includeInterval.get(i), excludeInterval.get(j)));
					}
				}

			}
			if (counter == 0) {
				interIntervals.add(includeInterval);
			}
			for (Interval inv1 : includeInterval) {
                if(!startList.contains(inv1.getStart()))
				outputIntervals.add(inv1);
			}
		}

		for (List<Interval> inv : interIntervals) {

			for (Interval inv1 : inv) {

				outputIntervals.add(inv1);
			}
		}

		Collections.sort(outputIntervals, Comparator.comparingInt(Interval::getStart));
		Set<Interval> outputSet = outputIntervals.stream().collect(Collectors.toSet());
        
		return new ArrayList<>(outputSet);
	}

	public List<Interval> splitIntoNonOverlappingIntervals(Interval includeInterval, Interval excludeInterval) {

		List<Interval> outputIntervals = new ArrayList<Interval>();

		
		//LHS overlap
		if (excludeInterval.getStart() <= includeInterval.getStart()
				&& excludeInterval.getEnd() < includeInterval.getEnd()) {

			outputIntervals.add(new Interval(excludeInterval.getEnd() + 1, includeInterval.getEnd()));
		} else if (excludeInterval.getStart() > includeInterval.getStart()
				&& excludeInterval.getEnd() >= includeInterval.getEnd()) {
          //RHS overlap
			if (excludeInterval.getStart() != 0) {

				outputIntervals.add(new Interval(includeInterval.getStart(), excludeInterval.getStart() - 1));

			} else {
				outputIntervals.add(new Interval(includeInterval.getStart(), 0));
			}

		} else if(excludeInterval.getStart() > includeInterval.getStart() && excludeInterval.getEnd() < includeInterval.getEnd()) {
         //exclude interval falls in the middle of include interval
			if (excludeInterval.getStart() != 0) {
				outputIntervals.add(new Interval(includeInterval.getStart(), excludeInterval.getStart() - 1));
			} else {
				outputIntervals.add(new Interval(includeInterval.getStart(), 0));
			}
			outputIntervals.add(new Interval(excludeInterval.getEnd() + 1, includeInterval.getEnd()));

		}else {
			return outputIntervals;
		}

		return outputIntervals;
	}

}
