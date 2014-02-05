package com.moonillusions.htmlUnitMatchers;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Description;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomNode;

public class HasAttribute extends ChainableMatcher<DomNode> {

	public HasAttribute(Attribute attribute) {
		addMatcher(new IsAttribute(attribute));
	}

	@Override
	protected void chainedDescribeTo(Description desc) {
		desc.appendText("Has attribute xxx: ");
	}
	
	@Override
	protected void chainedMismatch(DomNode item, Description desc) {
		desc.appendText("failed");
	}

//	private Node getAttribute() {
//		return Arrays.asList(item.getAttributes().item(this.index));
//	}
	
	@Override
	protected List<Node> matchAgainst(DomNode item) {
//		return Arrays.asList(item.getAttributes().item(this.index));
		return null;
	}

	@Override
	protected boolean match(List arg0) {
		return false;
	}

	

}
