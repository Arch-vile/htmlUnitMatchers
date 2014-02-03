package com.moonillusions.htmlUnitMatchers;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Description;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomNode;

public class IsAttribute extends ChainableMatcher<Node> {

	Attribute attribute;
	
	public IsAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	@Override
	protected void chainedDescribeTo(Description desc) {
		desc.appendText("Has attribute: " + attribute);
	}
	
	@Override
	protected void chainedMismatch(Node item, Description desc) {
		desc.appendText("On attribute: " + item + " did not match expected ");
	}

	@Override
	protected boolean match(List nodes2) {
		Node node = (Node)nodes2.get(0);
		return matchesValue(node) && matchesName(node);
	}

	private boolean matchesName(Node node) {
		return node.getNodeName().equals(attribute.getName());
	}

	private boolean matchesValue(Node node) {
		return node.getNodeValue().equals(attribute.getValue());
	}
	
	@Override
	protected List<Node> matchAgainst(Node item) {
		return Arrays.asList(item);
	}

}
