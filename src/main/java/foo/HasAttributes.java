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

public class HasAttributes extends MyCombiningTypeSafeMatcher<DomNode> {

	Attribute[] attributes;
	
	public HasAttributes(Attribute ... attributes) {
		this.attributes = attributes;
	}

	@Override
	public void describe(Description desc) {
		desc.appendText("DomNode that in order:");
		
	}
	
	@Override
	protected void mismatch(DomNode item, Description mismatchDescription) {
		mismatchDescription.appendText("On " + StringUtils.print(item));
		printAttributes(item,mismatchDescription);
	}

	@Override
	protected List<MatcherPair> createMatcherPairs(DomNode arg0) {
		List<MatcherPair> matchers = new ArrayList<MatcherPair>();
		for(int i = 0; i < this.attributes.length; i++) {
			IsAttribute isAttr = new IsAttribute(this.attributes[i]);
			Node node = getAttributes(arg0).get(i);
			MatcherPair matcherPair = new MatcherPair(isAttr, node);
			matchers.add(matcherPair);
		}
		
		return matchers;
	}


	private List<Node> getAttributes(DomNode item) {
		List<Node> nodes = new ArrayList<Node>();
		for(int i = 0; i < item.getAttributes().getLength(); i++) {
			nodes.add(item.getAttributes().item(i));
		}
		return nodes;
	}


	private void printAttributes(DomNode item, Description desc) {
		List<Node> nodes = getAttributes(item);
		desc.appendValueList("\nOn Attributes[", ",", "]",nodes );
	}


	@Factory
	public static HasAttributes hasAttributes(Attribute...attr) {
	    return new HasAttributes(attr);
	}
}
