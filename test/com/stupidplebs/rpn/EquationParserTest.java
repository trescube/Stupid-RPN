package com.stupidplebs.rpn;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.stupidplebs.rpn.operations.AddOperation;
import com.stupidplebs.rpn.operations.DivideOperation;
import com.stupidplebs.rpn.operations.SubtractOperation;

public class EquationParserTest {
	private EquationParser parser = new EquationParser();
	
	@Test
	public void singleValueParse() {
		Node root = parser.parse("2");
		
		assertThat(root, is(instanceOf(Value.class)));
		assertThat(root.calculateValue(), is(2.0));
	}
	
	@Test
	public void simpleParse() {
		Node root = parser.parse("1 2 +");
		
		assertThat(root, is(instanceOf(AddOperation.class)));
		assertThat(root.calculateValue(), is(3.0));
	}
	
	@Test
	public void anotherParse() {
		Node root = parser.parse("1 2 3 + -");
		
		assertThat(root, is(instanceOf(SubtractOperation.class)));
		assertThat(root.calculateValue(), is(-4.0));
	}
	
	@Test
	public void complicatedParse() {
		Node root = parser.parse("48 3 8 * /");
		
		assertThat(root, is(instanceOf(DivideOperation.class)));
		assertThat(root.calculateValue(), is(2.0));
	}
	
	@Test
	public void crazyParse() {
		Node root = parser.parse("3 4 6 - 7 + * 5 /");
		
		assertThat(root.calculateValue(), is(3.0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void tooManyThingsLeftOverShouldThrowException() {
		parser.parse("1 3 4 +");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void unknownCharacterShouldThrowException() {
		parser.parse("3 4 #");
	}
	
	@Test
	public void spuriousSpacesShouldBeIgnored() {
		Node root = parser.parse("   3    5  +   ");
		
		assertThat(root.calculateValue(), is(8.0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void onlyNumberShouldThrowException() {
		parser.parse("3 4");
	}
	
}
