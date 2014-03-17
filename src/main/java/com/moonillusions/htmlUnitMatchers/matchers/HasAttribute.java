package com.moonillusions.htmlUnitMatchers.matchers;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.core.Attribute;
import com.moonillusions.htmlUnitMatchers.core.ElementMatcher;

public class HasAttribute extends ElementMatcher<DomNode> {

	private Attribute attribute;

	public HasAttribute(String attribute) {
		this.attribute = new Attribute(attribute);
	}

	@Override
	protected void describe(Description arg0) {
		arg0.appendText(String.format("Has attribute %s", attribute));
	}

	@Override
	protected void mismatch(DomNode item, Description mismatchDescription) {
		mismatchDescription.appendText(this.matches(item));
	}

	@Override
	protected boolean match(DomNode element) {
		return matches(element) == null;
	}

	private String matches(DomNode element) {
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
	public static HasAttribute hasAttribute(String attribute) {
		return new HasAttribute(attribute);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		HasAttribute rhs = (HasAttribute) obj;
		return new EqualsBuilder()
				.append(this.attribute.getValue(), rhs.attribute.getValue())
				.append(this.attribute.getName(), rhs.attribute.getName())
				.isEquals();

	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(attribute.getName())
				.append(attribute.getValue()).toHashCode();
	}

}
