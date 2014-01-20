package com.moonillusions.htmlUnitMatchers;

public abstract class BaseTester<T> {

	public abstract boolean matches(T tested);
	
	public abstract String getMismatch();
	
}
