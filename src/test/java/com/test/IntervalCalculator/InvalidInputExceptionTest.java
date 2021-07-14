package com.test.IntervalCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class InvalidInputExceptionTest {

	InputCheckAndManipulation inp = new InputCheckAndManipulation();
	InvalidInputException e = new InvalidInputException();
	
	@Test
	public void testException() {
		assertThrows(InvalidInputException.class, () -> inp.inputCheck("10--100"), "Invalid input format");
		assertEquals("Invalid input format", e.getMessage());
	}
}