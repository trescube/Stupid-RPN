package com.stupidplebs.rpn.operations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.stupidplebs.rpn.Value;

public class OperationTest {
	@Test
	public void plusSignShouldReturnAddOperation() {
		Operation op = Operation.getInstance("+", new Value(3.0), new Value(4.0));
		
		assertThat(op, is(instanceOf(AddOperation.class)));
		assertThat(op.calculateValue(), is(7.0));
	}
	
	@Test
	public void minusSignShouldReturnSubtractOperation() {
		Operation op = Operation.getInstance("-", new Value(3.0), new Value(4.0));
		
		assertThat(op, is(instanceOf(SubtractOperation.class)));
		assertThat(op.calculateValue(), is(-1.0));
	}
	
	@Test
	public void asteriskShouldReturnMultiplyOperation() {
		Operation op = Operation.getInstance("*", new Value(3.0), new Value(4.0));
		
		assertThat(op, is(instanceOf(MultiplyOperation.class)));
		assertThat(op.calculateValue(), is(12.0));
	}
	
	@Test
	public void forwardSlashShouldReturnDivideOperation() {
		Operation op = Operation.getInstance("/", new Value(3.0), new Value(4.0));
		
		assertThat(op, is(instanceOf(DivideOperation.class)));
		assertThat(op.calculateValue(), is(0.75));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void unknownCharacterShouldThrowException() {
		Operation.getInstance("&", new Value(3.0), new Value(4.0));
	}
	
}
