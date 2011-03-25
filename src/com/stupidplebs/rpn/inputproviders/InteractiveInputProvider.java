package com.stupidplebs.rpn.inputproviders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class InteractiveInputProvider extends InputProvider {
	public InteractiveInputProvider() {}

	public List<String> getInputs() {
		System.out.print("Enter a Reverse Polish Notation (RPN) equation: ");
		
		final BufferedReader br = 
			new BufferedReader(new InputStreamReader(System.in));
		
		try {
			inputs.add(br.readLine());
		}
		catch (final IOException e) {
			System.err.println("An IOException occurred attempting to read " +
					"interactively: " + e.getMessage());
		}
		
		return Collections.unmodifiableList(inputs);
	}
	
}
