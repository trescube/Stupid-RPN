package com.stupidplebs.rpn.operations;

import org.apache.commons.lang.Validate;

import com.stupidplebs.rpn.Node;

public abstract class Operation extends Node {
	protected final Node leftNode;
	protected final Node rightNode;
	
	public Operation(final Node leftNode, 
			final Node rightNode) {
		Validate.notNull(leftNode, "leftNode parameter cannot be null");
		Validate.notNull(rightNode, "rightNode parameter cannot be null");
		
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		
		sb.append(leftNode.toString());
		sb.append(" ");
		sb.append(rightNode.toString());
		sb.append(" ");
		
		return sb.toString();
	}
	
	public static Operation getInstance(final String token, 
			final Node leftNode, final Node rightNode) {
		if (token.equals("+")) {
			return new AddOperation(leftNode, rightNode);
		} else if (token.equals("-")) {
			return new SubtractOperation(leftNode, rightNode);
		} else if (token.equals("*")) {
			return new MultiplyOperation(leftNode, rightNode);	
		} else if (token.equals("/")) {
			return new DivideOperation(leftNode, rightNode);
		}

		throw new IllegalArgumentException("Encountered unknown operation '" + token + "'");		
	}
	
}
