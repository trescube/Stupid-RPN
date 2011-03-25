package com.stupidplebs.rpn.operations;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.stupidplebs.rpn.Node;
import com.stupidplebs.rpn.Value;
import com.stupidplebs.rpn.operations.DivideOperation;

public class DivideOperationTest {
	@Test(expected=IllegalArgumentException.class)
	public void leftNodeCannotBeNull() {
		new DivideOperation(null, new Value(new Double(0)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void rightNodeCannotBeNull() {
		new DivideOperation(new Value(new Double(0)), null);
	}
	
	@Test
	public void numbersShouldBeDivided() {
		Node leftNode = new Value(new Double(6));
		Node rightNode = new Value(new Double(3));
		
		Node o = new DivideOperation(leftNode, rightNode);
		
		assertThat(o.calculateValue(), is(new Double(2)));
		assertThat(o.toString(), is("6.0 3.0 /"));

	}

}
