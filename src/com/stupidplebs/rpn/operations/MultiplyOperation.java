package com.stupidplebs.rpn.operations;

import com.stupidplebs.rpn.Node;

public class MultiplyOperation extends Operation {
	public MultiplyOperation(final Node leftNode, final Node rightNode) {
		super(leftNode, rightNode);
	}

	@Override
	public Double calculateValue() {
		return leftNode.calculateValue() * rightNode.calculateValue();
	}

	public String toString() {
		return super.toString() + "*";
	}
	
}
