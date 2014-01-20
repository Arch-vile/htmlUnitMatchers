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
		List<HasAttribute> matchers = new ArrayList<HasAttribute>();
		for(Attribute attribute : attributes) {
			addMatcher(new HasAttribute(attribute));
		}
	}

	@Factory
	public static Matcher<DomNode> hasAttributes(Attribute...attr) {
	    return new HasAttributes2(attr);
	}

	@Override
	protected void chainedDescribeTo(Description desc) {
		desc.appendText("DomNode that:");
	}

	@Override
	protected void chainedMismatch(DomNode item, Description desc) {
		desc.appendText("On DomNode " + StringUtils.print(item));
	}

	@Override
	protected boolean match(DomNode arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
