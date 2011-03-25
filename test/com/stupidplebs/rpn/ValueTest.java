package com.stupidplebs.rpn;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ValueTest {
	@Test
	public void inputShouldBeOutput() {
		Double input = 3.5;
		
		Node node = new Value(input);
		
		assertThat(node.calculateValue(), is(input));
	}
	
}
