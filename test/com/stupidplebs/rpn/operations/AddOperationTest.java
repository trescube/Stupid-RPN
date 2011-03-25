package com.stupidplebs.rpn.operations;

import org.junit.Test;

import com.stupidplebs.rpn.Node;
import com.stupidplebs.rpn.Value;
import com.stupidplebs.rpn.operations.AddOperation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AddOperationTest {
	@Test(expected=IllegalArgumentException.class)
	public void leftNodeCannotBeNull() {
		new AddOperation(null, new Value(new Double(0)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void rightNodeCannotBeNull() {
		new AddOperation(new Value(new Double(0)), null);
	}
	
	@Test
	public void numbersShouldBeAdded() {
		Node leftNode = new Value(new Double(2));
		Node rightNode = new Value(new Double(3));
		
		Operation o = new AddOperation(leftNode, rightNode);
		
		assertThat(o.calculateValue(), is(new Double(5)));
		assertThat(o.toString(), is("2.0 3.0 +"));
	
	}
}
