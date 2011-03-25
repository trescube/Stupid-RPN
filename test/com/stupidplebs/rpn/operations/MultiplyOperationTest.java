package com.stupidplebs.rpn.operations;

import org.junit.Test;

import com.stupidplebs.rpn.Node;
import com.stupidplebs.rpn.Value;
import com.stupidplebs.rpn.operations.MultiplyOperation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MultiplyOperationTest {
	@Test(expected=IllegalArgumentException.class)
	public void leftNodeCannotBeNull() {
		new MultiplyOperation(null, new Value(new Double(0)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void rightNodeCannotBeNull() {
		new MultiplyOperation(new Value(new Double(0)), null);
	}
	
	@Test
	public void numbersShouldBeMultiplied() {
		Node leftNode = new Value(new Double(2));
		Node rightNode = new Value(new Double(3));
		
		Node o = new MultiplyOperation(leftNode, rightNode);
		
		assertThat(o.calculateValue(), is(new Double(6)));
		assertThat(o.toString(), is("2.0 3.0 *"));

	}

}
