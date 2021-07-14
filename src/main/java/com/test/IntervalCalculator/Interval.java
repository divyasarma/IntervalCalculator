package com.test.IntervalCalculator;

public class Interval {

	public int start;
	public int end;
	
	public Interval(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return  start + "-" + end ;
	}
	@Override
    public boolean equals(Object other){
        return (this.start == ((Interval) other).start) && (this.end == ((Interval) other).end);
    }
	
	@Override public int hashCode() {
		   return start;
		}
}
