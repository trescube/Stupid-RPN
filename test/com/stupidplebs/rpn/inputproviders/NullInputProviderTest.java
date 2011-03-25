package com.stupidplebs.rpn.inputproviders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class NullInputProviderTest {
	@Test
	public void emptyListShouldAlwaysBeReturned() {
		InputProvider provider = new NullInputProvider();
		assertThat(provider.getInputs().size(), is(0));
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void inputListCannotBeAddedTo() {
		InputProvider provider = new NullInputProvider();
		
		List<String> inputs = provider.getInputs();
		inputs.add("this will fail");
		
	}
	
}
