package com.stupidplebs.rpn.inputproviders;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.Validate;

public class FileInputProvider extends InputProvider {
	public FileInputProvider(final File file) {
		Validate.notNull(file, "File input parameter cannot be null");
		Validate.isTrue(file.exists(), "File input parameter does not exist");
		
		try {
			inputs.addAll(FileUtils.readLines(file));
		}
		catch (final IOException e) {
			System.err.println("Encountered IOException reading input file '" + 
					file.getAbsolutePath() + "': " + e.getMessage());
			
		}
	}
	
}
