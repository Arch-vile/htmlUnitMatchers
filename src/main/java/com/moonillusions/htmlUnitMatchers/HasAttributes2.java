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

public class HasAttributes2 extends ChainableMatcher<DomNode> {

	
	public HasAttributes2(Attribute ... attributes) {
		for(Attribute attribute : attributes) {
			addMatcher(new HasAttribute(attribute));
		}
	}

	public HasAttributes2(List<Attribute> attributes) {
		for(Attribute attribute : attributes) {
			addMatcher(new HasAttribute(attribute));
		}
	}

	@Factory
	public static ChainableMatcher<DomNode> hasAttributes(Attribute...attr) {
	    return new HasAttributes2(attr);
	}
	
	@Factory
	public static ChainableMatcher<DomNode> hasAttributes(List<Attribute> attributes) {
	    return new HasAttributes2(attributes);
	}

	protected void chainedMismatch(DomNode item, Description desc){
		desc.appendText("On attributes ");
		printAttributes(item,desc);
	}
	
	private void printAttributes(DomNode item, Description desc) {
		List<Attribute> nodes = new ArrayList<Attribute>();
		for(int i = 0; i < item.getAttributes().getLength(); i++) {
			nodes.add(new Attribute(item.getAttributes().item(i)));
		}
		desc.appendValueList("[", ",", "]",nodes );
	}

	@Override
	protected void chainedDescribeTo(Description desc) {
		desc.appendText("DomNode that:");
	}

	@Override
	protected List<DomNode> matchAgainst(DomNode item) {
		return Arrays.asList(item);
	}

	@Override
	protected boolean match(List arg0) {
		return false;
	}


}
