package com.stupidplebs.rpn.inputproviders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class InputProvider {
	protected final List<String> inputs = new ArrayList<String>();
	
	public List<String> getInputs() {
		return Collections.unmodifiableList(inputs);
	}
}
