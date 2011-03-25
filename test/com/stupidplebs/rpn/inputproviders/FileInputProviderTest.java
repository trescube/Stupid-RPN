package com.stupidplebs.rpn.inputproviders;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.stupidplebs.rpn.inputproviders.FileInputProvider;
import com.stupidplebs.rpn.inputproviders.InputProvider;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FileInputProviderTest {
	@Test(expected=IllegalArgumentException.class)
	public void nullInputShouldThrowException() {
		new FileInputProvider(null);
	}
	
	@Test
	public void inputsShouldBeReadFromFile() throws IOException {
		File file = File.createTempFile("rpnInputs", null);
		
		List<String> lines = new ArrayList<String>();
		lines.add("a");
		lines.add("b");
		
		FileUtils.writeLines(file, lines);
		
		InputProvider provider = new FileInputProvider(file);
		
		List<String> inputs = provider.getInputs();
		assertThat(inputs.size(), is(2));
		assertThat(inputs.get(0), is("a"));
		assertThat(inputs.get(1), is("b"));
		
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void inputsListCannotBeAddedTo() throws IOException {
		File file = File.createTempFile("rpnInputs", null);
		
		FileUtils.writeLines(file, new ArrayList<String>());
		
		InputProvider provider = new FileInputProvider(file);
		
		List<String> inputs = provider.getInputs();
		inputs.add("this will fail");
	}
	
	@Test
	public void ioExceptionShouldBeReportedToStdErr() throws IOException {
		// used to capture System.err
		OutputStream stdErr = new ByteArrayOutputStream();
		System.setErr(new PrintStream(stdErr));
		
		File file = File.createTempFile("input", null);
		file.setReadable(false);
		
		new FileInputProvider(file);
		
		String expectedErrorMessage = "Encountered IOException reading input file '" + 
			file.getAbsolutePath() + "': File '" + file.getAbsolutePath() + "' cannot be read\n";
		
		assertThat(stdErr.toString(), is(expectedErrorMessage));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nonExistentFileShouldThrowException() throws IOException {
		File file = new File(System.getProperty("java.io.tmpdir"), "THISFILESHOULDNOTEXIST.txt");
		// first assert that the file doesn't exist
		assertThat(file.exists(), is(false));
		
		new FileInputProvider(file);
	}
	
}
