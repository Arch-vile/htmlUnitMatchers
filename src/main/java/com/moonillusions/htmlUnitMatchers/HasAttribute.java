package com.moonillusions.htmlUnitMatchers;

import java.util.Arrays;
import java.util.List;

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
		
		if(matchingAttribute(item) != null) {
			desc.appendText("On Attribute[" + matchingAttribute(item) + "] failed because ");
			desc.appendText("value '" + value(item) + "' did not match expected value of '" + this.attribute.getValue() + "'");
		} else {
			desc.appendText("not found at all");
		}
	}

	@Override
	protected boolean match(List nodes2) {
		List<DomNode> nodes = (List<DomNode>)nodes2;
		
		boolean matchedAll = true;
		for(DomNode node : nodes) {
			
			Attribute matchingAttr = matchingAttribute(node);
			
			if(matchingAttr != null) {
				String value = value(node);
				if( !value.equals(this.attribute.getValue()) ) {
					matchedAll = false;
				}
			} else {
				matchedAll = false;
			}
		}
			
		return matchedAll;
	}
	
	private String value(DomNode node) {
		return matchingAttribute(node).getValue();
	}
	
	private Attribute matchingAttribute(DomNode node) {
		NamedNodeMap attributes = node.getAttributes();
		Node namedItem = attributes.getNamedItem(this.attribute.getName());
		if(namedItem != null) {
			return new Attribute(namedItem);
		} else {
			return null;
		}
	}

	@Override
	protected List<DomNode> matchAgainst(DomNode item) {
		return Arrays.asList(item);
	}

	

}
