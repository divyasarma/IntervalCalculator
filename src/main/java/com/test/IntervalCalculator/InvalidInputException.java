package com.test.IntervalCalculator;

public class InvalidInputException extends Exception{

	private String message ="Invalid input format";

	public String getMessage() {
		return message;
	}

}
