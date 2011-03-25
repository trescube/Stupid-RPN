package com.stupidplebs.rpn;

public class Value extends Node {
	private final Double value;
	
	public Value(final Double value) {
		this.value = value;
	}

	@Override
	public Double calculateValue() {
		return value;
	}

	public String toString() {
		return String.valueOf(value);
	}

}
