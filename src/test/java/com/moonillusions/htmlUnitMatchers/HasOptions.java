package com.moonillusions.htmlUnitMatchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.gargoylesoftware.htmlunit.html.DomNode;

public class HasOptions extends TypeSafeMatcher<DomNode>  {

	public void describeTo(Description arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean matchesSafely(DomNode arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Factory
	public static <T> Matcher<DomNode> hasOptions() {
	    return new HasOptions();
	}
	

}
