package com.moonillusions.htmlUnitMatchers.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

import com.gargoylesoftware.htmlunit.html.DomElement;

public class NotNullElement extends TypeSafeMatcher<DomElement> {

	private final String elementType;

	public NotNullElement(String elementType) {
		this.elementType = elementType;
	}

	@Factory
	public static NotNullElement notNullElement(String elementType) {
		return new NotNullElement(elementType);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(String.format("given %s element",
				this.elementType));

	}

	@Override
	protected boolean matchesSafely(DomElement item) {
		return item != null;
	}

}
