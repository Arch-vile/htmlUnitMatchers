package com.moonillusions.htmlUnitMatchers;

import org.hamcrest.Description;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomNode;

public class HasAttribute extends ChainableMatcher<DomNode> {

	Attribute attribute;
	
	public HasAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	@Override
	protected void chainedDescribeTo(Description desc) {
		desc.appendText("Has attribute: " + attribute.getName() + "=" + attribute.getValue());
	}
	
	@Override
	protected void chainedMismatch(DomNode item, Description desc) {
		desc.appendText("on Attribute[" + matchingAttribute(item) + "] failed because ");
		desc.appendText("value '" + value(item) + "' did not match expected value of '" + this.attribute.getValue() + "'");
	}

	@Override
	protected boolean match(DomNode node) {
		return value(node).equals(this.attribute.getValue());
	}
	
	private String value(DomNode node) {
		return matchingAttribute(node).getValue();
	}
	
	private Attribute matchingAttribute(DomNode node) {
		NamedNodeMap attributes = node.getAttributes();
		Node namedItem = attributes.getNamedItem(this.attribute.getName());
		return new Attribute(namedItem);
	}

}
