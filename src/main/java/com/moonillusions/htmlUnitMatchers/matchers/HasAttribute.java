package com.moonillusions.htmlUnitMatchers.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.core.MatchableExtractor;
import com.moonillusions.htmlUnitMatchers.utils.AttributeUtil;

public class HasAttribute extends DerivedMatchableMatcher<DomNode, DomNode> {

	private final String attributeName;
	private final String attributeValue;

	public HasAttribute(String attributeName, String attributeValue,
			MatchableExtractor<DomNode, DomNode> extractor) {
		super(extractor);
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}

	@Override
	public void describeTo(Description description) {
		description
				.appendText(String.format("Has attribute %s", AttributeUtil
						.toString(this.attributeName, this.attributeValue)));

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
				this.attributeName);
		if (attr == null) {
			return String.format("On %s did not have expected attribute %s",
					element, AttributeUtil.toString(this.attributeName,
							this.attributeValue));
		}

		if (!matchesValue(attr)) {
			return String.format(
					"On %s on attribute %s did not match expected value: [%s]",
					element, attr, this.attributeValue);
		}
		return null;
	}

	private boolean matchesValue(Node node) {
		return node.getNodeValue().equals(this.attributeValue);
	}

	@Factory
	public static HasAttribute hasAttribute(String attributeName,
			String attributeValue,
			MatchableExtractor<DomNode, DomNode> extractor) {
		return new HasAttribute(attributeName, attributeValue, extractor);

	}

	@Factory
	public static HasAttribute hasAttribute(String attributeName,
			String attributeValue) {
		return new HasAttribute(attributeName, attributeValue, null);

	}

}
