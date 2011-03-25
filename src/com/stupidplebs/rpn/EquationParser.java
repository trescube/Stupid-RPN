package com.stupidplebs.rpn;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.stupidplebs.rpn.operations.Operation;

public class EquationParser {
	public EquationParser() {
		// empty by design... for now
	}
	
	public Node parse(final String input) {
		Validate.notNull(input, "String input cannot be null");
		
		final List<String> tokens = Arrays.asList(StringUtils.split(input));

		final Stack<Node> stack = new Stack<Node>();
		
		for (final String token : tokens) {
			if (parseableDouble(token)) {
				stack.push(new Value(Double.parseDouble(token)));
			} else {
				final Node rightNode = stack.pop();
				final Node leftNode = stack.pop();
				
				stack.push(Operation.getInstance(token, leftNode, rightNode));
			}
			
		}

		if (stack.size() != 1) {
			throw new IllegalArgumentException("Could not parse input: " + input);
		}
		
		return stack.pop();
	}
	
	private boolean parseableDouble(final String t) {
		try {
			Double.parseDouble(t);
			return true;
		}
		catch (final NumberFormatException e) {
			return false;
		}
	}
}
