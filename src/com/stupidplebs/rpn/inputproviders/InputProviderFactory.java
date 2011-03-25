package com.stupidplebs.rpn.inputproviders;

import java.io.File;

import org.apache.commons.lang.StringUtils;

public class InputProviderFactory {
	public InputProviderFactory() {}
	
	public static InputProvider getInstance(final String[] args) {
		if (null == args || args.length == 0) {
			return new InteractiveInputProvider();
		}
		
		if (indexOf(args, "--help") > -1) {
			System.out.println("Usage: ");
			return new NullInputProvider();
		}
		
		if ("-f".equals(args[0])) {
			if (args.length == 1) {
				throw new IllegalArgumentException("-f option specified without filename");
			} else {
				return new FileInputProvider(new File(args[1]));
			}
			
		}
		
		if ("-e".equals(args[0])) {
			if (args.length == 1) {
				throw new IllegalArgumentException("-e option specified without expression");
				
			} else if (!args[1].matches("\"[^\"]+\"")) {
				throw new IllegalArgumentException("-e expression must be wrapped in double quotes");
				
			} else {
				String expr = StringUtils.trim(args[1]);
				expr = StringUtils.removeStart(expr, "\"");
				expr = StringUtils.removeEnd(expr, "\"");
				
				return new ProgramArgumentInputProvider(expr);
				
			}
			
		}
		
		if ("-i".equals(args[0])) {
			return new InteractiveInputProvider();
		}
		
		throw new IllegalArgumentException("Unknown flag: " + args[0]);
	}
	
	private static int indexOf(final String[] args, final String name) {
		for (int i = 0; i < args.length; i++) {
			if (name.equals(args[i])) {
				return i;
			}
		}
		
		return -1;
	}
	
}
