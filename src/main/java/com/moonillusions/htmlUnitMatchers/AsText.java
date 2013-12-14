package com.moonillusions.htmlUnitMatchers;


import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.gargoylesoftware.htmlunit.html.DomNode;

import static org.hamcrest.Matchers.equalTo;

public class AsText extends TypeSafeMatcher<DomNode>  {

	private String text;
	
	public AsText(String text) {
		this.text = text;
	}
	
	public void describeTo(Description description) {
		description.appendText("Expected DomNode with asText() value '" + text + "'");
	}

	@Override
	protected boolean matchesSafely(DomNode node) {
		return equalTo(this.text).matches(node.asText());
	}
	
	@Factory
	public static <T> Matcher<DomNode> asText(String textToCheck) {
	    return new AsText(textToCheck);
	}

}
