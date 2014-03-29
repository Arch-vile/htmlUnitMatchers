package com.moonillusions.htmlUnitMatchers.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.core.Attribute;
import com.moonillusions.htmlUnitMatchers.core.MatchableExtractor;

public class HasAttribute extends DerivedMatchableMatcher<DomNode, DomNode> {

	private Attribute attribute;

	public HasAttribute(String attr,
			MatchableExtractor<DomNode, DomNode> extractor) {
		super(extractor);
		this.attribute = new Attribute(attr);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(String.format("Has attribute %s", attribute));

	}

	@Override
	protected void describeMismatchSafely(DomNode item,
			Description mismatchDescription) {
		mismatchDescription.appendText(this.matches(item));
	}

	@Override
	protected boolean matchesSafely(DomNode item) {
		return matches(item) == null;
	}

	private String matches(DomNode from) {
		DomNode element = matchable(from);

		DomAttr attr = (DomAttr) element.getAttributes().getNamedItem(
				this.attribute.getName());
		if (attr == null) {
			return String.format("On %s did not have expected attribute %s",
					element, this.attribute);
		}

		if (!matchesValue(attr)) {
			return String.format(
					"On %s on attribute %s did not match expected value: [%s]",
					element, attr, this.attribute.getValue());
		}
		return null;
	}

	private boolean matchesValue(Node node) {
		return node.getNodeValue().equals(this.attribute.getValue());
	}

	@Factory
	public static HasAttribute hasAttribute(String attr,
			MatchableExtractor<DomNode, DomNode> extractor) {
		return new HasAttribute(attr, extractor);

	}

	@Factory
	public static HasAttribute hasAttribute(String attr) {
		return new HasAttribute(attr, null);

	}

}
