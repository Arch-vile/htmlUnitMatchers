package com.moonillusions.htmlUnitMatchers;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Description;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomNode;

public class IsAttribute extends ChainableMatcher<Node> {

	Attribute attribute;
	
	private String failReason;
	
	public IsAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	@Override
	protected void chainedDescribeTo(Description desc) {
		desc.appendText("Has attribute: " + attribute);
	}
	
	@Override
	protected void chainedMismatch(Object item, Description desc) {
		desc.appendText("On attribute: " + item + " did not match expected " + failReason);
	}

	@Override
	protected boolean match(Object item) {
		if(!matchesName((Node)item)) {
			this.failReason = "name=" + attribute.getName();
			return false;
		}
		
		if(!matchesValue((Node)item)) {
			this.failReason = "value=" + attribute.getValue();
			return false;
		}
		return true;
	}

	private boolean matchesName(Node node) {
		return node.getNodeName().equals(attribute.getName());
	}

	private boolean matchesValue(Node node) {
		return node.getNodeValue().equals(attribute.getValue());
	}
	

}
