package com.moonillusions.htmlUnitMatchers;

import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomNode;

public class AttributeExtractor extends MatchableExtractor {

	private int index;

	public AttributeExtractor(int i) {
		this.index = i;
	}

	@Override
	protected Object getMatchable(Object from) {
		DomNode node = (DomNode)from;
		Node item = node.getAttributes().item(this.index);
		return item;
	}

	
	
	
}
