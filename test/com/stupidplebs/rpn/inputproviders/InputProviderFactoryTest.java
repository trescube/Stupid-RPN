package com.stupidplebs.rpn.inputproviders;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class InputProviderFactoryTest {
	@Test
	public void nullInputShouldReturnInteractive() {
		InputProvider provider = InputProviderFactory.getInstance(null);
		assertThat(provider, is(instanceOf(InteractiveInputProvider.class)));
	}
	
	@Test
	public void emptyArrayShouldReturnInteractive() {
		InputProvider provider = InputProviderFactory.getInstance(new String[]{});
		assertThat(provider, is(instanceOf(InteractiveInputProvider.class)));
	}

	@Test
	public void helpFlagShouldOutputUsageMessage() {
		String[] args = new String[1];
		args[0] = "--help";

		// used to capture System.out
		OutputStream stdOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(stdOut));
		
		InputProvider provider = InputProviderFactory.getInstance(args);
		assertThat(provider, is(instanceOf(NullInputProvider.class)));
		assertThat(stdOut.toString(), is("Usage: \n"));
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void eFlagWithoutArgumentShouldThrowException() {
		String[] args = new String[2];
		args[0] = "-e";
		args[1] = "";
		
		InputProviderFactory.getInstance(args);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void eFlagWithExpressionNotStartingWithParentheseShouldThrowException() {
		String[] args = new String[2];
		args[0] = "-e";
		args[1] = "3 4 +\"  ";
		
		InputProviderFactory.getInstance(args);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void eFlagWithExpressionNotEndingWithParentheseShouldThrowException() {
		String[] args = new String[2];
		args[0] = "-e";
		args[1] = "  \"3 4 +";
		
		InputProviderFactory.getInstance(args);
		
	}
	
	@Test
	public void eFlagWithValidArgumentShouldReturnProgramArgumentProvider() {
		String[] args = new String[2];
		args[0] = "-e";
		args[1] = "\"3 4 +\"";
		
		InputProvider provider = InputProviderFactory.getInstance(args);
		assertThat(provider, is(instanceOf(ProgramArgumentInputProvider.class)));
		assertThat(provider.getInputs().get(0), is("3 4 +"));
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void fFlagWithoutFollowupArgumentShouldThrowException() {
		String[] args = new String[1];
		args[0] = "-f";

		InputProvider provider = InputProviderFactory.getInstance(args);
		assertThat(provider, is(instanceOf(FileInputProvider.class)));
		
	}
	
	@Test
	public void fFlagWithValidArgumentShouldReturnFileProvider() throws IOException {
		File file = File.createTempFile("input", null);
		
		String[] args = new String[2];
		args[0] = "-f";
		args[1] = file.getAbsolutePath();

		InputProvider provider = InputProviderFactory.getInstance(args);
		assertThat(provider, is(instanceOf(FileInputProvider.class)));
		
	}

	@Test
	public void iFlagShouldReturnInteractiveProvider() {
		String[] args = new String[1];
		args[0] = "-i";

		InputProvider provider = InputProviderFactory.getInstance(args);
		assertThat(provider, is(instanceOf(InteractiveInputProvider.class)));
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void unknownFlagShouldThrowException() {
		String[] args = new String[1];
		args[0] = "-R";
		
		InputProviderFactory.getInstance(args);
	}
	
}
