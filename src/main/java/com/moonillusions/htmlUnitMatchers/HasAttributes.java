package com.moonillusions.htmlUnitMatchers;


import java.util.ArrayList;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.hamcrest.TypeSafeMatcher;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;

public class HasAttributes extends BaseElementMatcher<DomNode>  {

	private List<Attribute> attributes;
	
	public HasAttributes() {
	}
	
	public HasAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	

	
	@Override
	protected boolean matchesSafely(DomNode domNode) {
		NamedNodeMap mp = domNode.getAttributes();
		
		List<Attribute> actual = new ArrayList<Attribute>();
		for(int i = 0; i < mp.getLength(); i++){
			actual.add(new Attribute(mp.item(i)));
		}
		
		if(this.attributes.isEmpty()) {
			Matcher matcher = empty();
			if(!applyMatcher(actual, matcher)) {
				setMismatchMessage("found");
				return false;
			} else {
				return true;
			}
		}
		
		Matcher<Iterable<? extends Object>> matcher = contains(this.attributes.toArray());
		if(!applyMatcher(actual, matcher)) {
			return false;
		}
		
		return true;
	}

	
	
	@Factory
	public static <T> Matcher<DomNode> hasAttributes(Attribute...attr) {
	    return new HasAttributes(Arrays.asList(attr));
	}
	
	@Factory
	public static <T> Matcher<DomNode> hasAttributes(List<Attribute> attrs) {
	    return new HasAttributes(attrs);
	}

	@Override
	protected Description getDescribeTo(Description description) {
		return description.appendText("DomNode with attributes: ");
	}

}
