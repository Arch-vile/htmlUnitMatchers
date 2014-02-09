package foo;


import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Factory;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.Attribute;

public class HasAttributes extends MyCombiningTypeSafeMatcher<DomNode> {

	Attribute[] attributes;
	
	public HasAttributes(Attribute ... attributes) {
		this.attributes = attributes;
	}
	
	@Override
	protected List<MatcherPair> createMatcherPairs(DomNode arg0) {
		List<MatcherPair> matchers = new ArrayList<MatcherPair>();
		
		matchers.add(new MatcherPair(AttributeCountMatcher.hasAttributes(this.attributes.length), arg0));
		
		for(int i = 0; i < this.attributes.length; i++) {
			HasAttribute hasAttr = new HasAttribute(this.attributes[i], i);
			MatcherPair matcherPair = new MatcherPair(hasAttr, arg0);
			matchers.add(matcherPair);
		}
		
		return matchers;
	}


	@Factory
	public static HasAttributes hasAttributes(Attribute...attr) {
	    return new HasAttributes(attr);
	}
}
