package com.stupidplebs.rpn.operations;

import org.junit.Test;

import com.stupidplebs.rpn.Node;
import com.stupidplebs.rpn.Value;
import com.stupidplebs.rpn.operations.SubtractOperation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SubtractOperationTest {
	@Test(expected=IllegalArgumentException.class)
	public void leftNodeCannotBeNull() {
		new SubtractOperation(null, new Value(new Double(0)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void rightNodeCannotBeNull() {
		new SubtractOperation(new Value(new Double(0)), null);
	}
	
	@Test
	public void numbersShouldBeSubtracted() {
		Node leftNode = new Value(new Double(2));
		Node rightNode = new Value(new Double(3));
		
		Node o = new SubtractOperation(leftNode, rightNode);
		
		assertThat(o.calculateValue(), is(new Double(-1)));
		assertThat(o.toString(), is("2.0 3.0 -"));
	}

}
