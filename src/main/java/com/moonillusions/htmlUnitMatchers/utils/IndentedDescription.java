package com.moonillusions.htmlUnitMatchers.utils;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;

public class IndentedDescription implements Description {

	private Description description;
	private int indent = 0;
	
	public IndentedDescription(Description mismatchDescription) {
		this.description = mismatchDescription;
	}

	public Description appendDescriptionOf(SelfDescribing arg0) {
		return this.description.appendDescriptionOf(arg0);
	}

	public Description appendList(String arg0, String arg1, String arg2,
			Iterable<? extends SelfDescribing> arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	public Description appendText(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Description appendValue(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Description appendValueList(String arg0, String arg1,
			String arg2, T... arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Description appendValueList(String arg0, String arg1,
			String arg2, Iterable<T> arg3) {
		// TODO Auto-generated method stub
		return null;
	}

}
