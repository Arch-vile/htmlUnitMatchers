package foo;


import org.hamcrest.Factory;

import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.DomNode;

import static foo.HasAttribute.hasAttribute;

public class HasAttributes extends MyCombiningTypeSafeMatcher<DomNode> {

	public HasAttributes(DomAttr... attributes) {
		addMatcher(new MatcherPair(
				AttributeCountMatcher.hasAttributes(attributes.length),
				new MatchableExtractor<DomNode, DomNode>()));

		for (int i = 0; i < attributes.length; i++) {
			HasAttribute hasAttr = hasAttribute(attributes[i], i);
			MatcherPair matcherPair = new MatcherPair(hasAttr,new MatchableExtractor<DomNode, DomNode>());
			addMatcher(matcherPair);
		}
	}
	
	@Factory
	public static HasAttributes hasAttributes(DomAttr...attr) {
	    return new HasAttributes(attr);
	}
}
