package com.stupidplebs.rpn;

import com.stupidplebs.rpn.inputproviders.InputProvider;
import com.stupidplebs.rpn.inputproviders.InputProviderFactory;


public class Main {
	public static void main(final String[] args) {
		try {
			InputProvider provider = InputProviderFactory.getInstance(args);
			EquationParser equationParser = new EquationParser();
			
			for (final String input : provider.getInputs()) {
				final Node root =  equationParser.parse(input);
				
				final String output = String.format(
						"Input \"%s\" yielded result: %d", 
						input, root.calculateValue());
				
				System.out.println(output);
			}
		}
		catch (final IllegalArgumentException e) {
			
		}
	}
}
