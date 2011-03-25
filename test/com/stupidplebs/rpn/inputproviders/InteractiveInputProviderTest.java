package com.stupidplebs.rpn.inputproviders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.Test;

public class InteractiveInputProviderTest {
	@Test
	public void inputShouldBeReadCorrectly() {
		// used to capture System.out
		OutputStream stdOut = new ByteArrayOutputStream();
		
		System.setIn(new ByteArrayInputStream("test input".getBytes()));
		System.setOut(new PrintStream(stdOut));
		
		InputProvider provider = new InteractiveInputProvider();
		
		List<String> inputs = provider.getInputs();
		
		assertThat(inputs.size(), is(1));
		assertThat(inputs.get(0), is("test input"));
		assertThat(stdOut.toString(), is("Enter a Reverse Polish Notation (RPN) equation: "));
	}
	
	@Test
	public void errorReadingInputShouldWriteToSystemErr() {
		// used to capture System.err
		OutputStream stdErr = new ByteArrayOutputStream();
		
		System.setIn(new BadStream());
		System.setErr(new PrintStream(stdErr));
		
		InputProvider provider = new InteractiveInputProvider();
		
		List<String> inputs = provider.getInputs();
		
		assertThat(inputs.size(), is(0));
		assertThat(stdErr.toString(), is("An IOException occurred attempting to " +
				"read interactively: This is intentionally a bad stream\n"));
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void inputsListCannotBeAddedTo() throws IOException {
		System.setIn(new ByteArrayInputStream("test input".getBytes()));
		
		InputProvider provider = new InteractiveInputProvider();
		
		List<String> inputs = provider.getInputs();
		inputs.add("this will fail");
	}
	

}

class BadStream extends InputStream {
	@Override
	public int read() throws IOException {
		throw new IOException("This is intentionally a bad stream");
	}
	
}
