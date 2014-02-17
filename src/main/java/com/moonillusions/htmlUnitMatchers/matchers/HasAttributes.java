package com.moonillusions.htmlUnitMatchers.matchers;


import org.hamcrest.Factory;

import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.core.MatchableExtractor;
import com.moonillusions.htmlUnitMatchers.core.MatcherPair;
import com.moonillusions.htmlUnitMatchers.core.MatcherCollection;

import static com.moonillusions.htmlUnitMatchers.matchers.HasAttribute.hasAttribute;

public class HasAttributes extends MatcherCollection<DomNode> {

	public HasAttributes(DomAttr... attributes) {
		addMatcher(new MatcherPair<DomNode, DomNode>(
				AttributeCountMatcher.hasAttributes(attributes.length),
				new MatchableExtractor<DomNode, DomNode>()));

		for (int i = 0; i < attributes.length; i++) {
			HasAttribute hasAttr = hasAttribute(attributes[i], i);
			MatcherPair<DomNode,DomNode> matcherPair = new MatcherPair<DomNode,DomNode>(
					hasAttr,
					new MatchableExtractor<DomNode, DomNode>());
			addMatcher(matcherPair);
		}
	}
	
	@Factory
	public static HasAttributes hasAttributes(DomAttr...attr) {
	    return new HasAttributes(attr);
	}
}
