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

public class HasAttributes extends ChainableMatcher<DomNode> {

	
	public HasAttributes(Attribute ... attributes) {
		for(int i = 0; i < attributes.length; i++) {
			IsAttribute m = new IsAttribute(attributes[i]);
			m.setExtractor(new AttributeExtractor(i));
			addMatcher(m);
		}
	}
	

	@Factory
	public static ChainableMatcher<DomNode> hasAttributes(Attribute...attr) {
	    return new HasAttributes(attr);
	}
	
	

	protected void chainedMismatch(DomNode item, Description desc){
		desc.appendText("On attributes: ");
		printAttributes(item,desc);
	}
	
	private void printAttributes(DomNode item, Description desc) {
		List<Node> nodes = getAttributes(item);
		desc.appendValueList("[", ",", "]",nodes );
	}

	private List<Node> getAttributes(DomNode item) {
		List<Node> nodes = new ArrayList<Node>();
		for(int i = 0; i < item.getAttributes().getLength(); i++) {
			nodes.add(item.getAttributes().item(i));
		}
		return nodes;
	}

	@Override
	protected void chainedDescribeTo(Description desc) {
		desc.appendText("DomNode that in order:");
	}


}
