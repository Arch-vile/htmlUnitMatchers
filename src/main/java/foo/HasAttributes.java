package foo;


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
import com.moonillusions.htmlUnitMatchers.Attribute;
import com.moonillusions.htmlUnitMatchers.StringUtils;

public class HasAttributes extends TypeSafeMatcher<DomNode> {

	Attribute[] attributes;
	private MatcherPair failedMatcher;
	List<MatcherPair> matchers = new ArrayList<MatcherPair>();
	
	public HasAttributes(Attribute ... attributes) {
		this.attributes = attributes;
	}
	

	@Factory
	public static HasAttributes hasAttributes(Attribute...attr) {
	    return new HasAttributes(attr);
	}

	@Override
	protected void describeMismatchSafely(DomNode item,
			Description mismatchDescription) {
		mismatchDescription.appendText("On " + StringUtils.print(item));
		printAttributes(item,mismatchDescription);
		mismatchDescription.appendText("\n");
		this.failedMatcher.getMatcher().describeMismatch(this.failedMatcher.getMatchee(), mismatchDescription);
	}
	
	private void printAttributes(DomNode item, Description desc) {
		List<Node> nodes = getAttributes(item);
		desc.appendValueList("\nOn Attributes[", ",", "]",nodes );
	}

	public void describeTo(Description desc) {
		desc.appendText("DomNode that in order:");
		for(MatcherPair pair : this.matchers) {
			desc.appendText("\n");
			desc.appendDescriptionOf(pair.getMatcher());
		}
	}


	@Override
	protected boolean matchesSafely(DomNode arg0) {
		
		createMatcherPairs(arg0);

		for(MatcherPair pair : this.matchers) {
			if(!pair.isMatch()) {
				this.failedMatcher = pair;
				return false;
			}
		}
		
		return true;
	}
	

	private void createMatcherPairs(DomNode arg0) {
		for(int i = 0; i < this.attributes.length; i++) {
			IsAttribute isAttr = new IsAttribute(this.attributes[i]);
			Node node = getAttributes(arg0).get(i);
			MatcherPair matcherPair = new MatcherPair(isAttr, node);
			this.matchers.add(matcherPair);
		}
	}


	private List<Node> getAttributes(DomNode item) {
		List<Node> nodes = new ArrayList<Node>();
		for(int i = 0; i < item.getAttributes().getLength(); i++) {
			nodes.add(item.getAttributes().item(i));
		}
		return nodes;
	}


}
