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
	private int index;

	public HasAttribute(String attribute, int index) {
		this.attribute = new Attribute(attribute);
		this.index = index;
	}

	@Override
	protected void describe(Description arg0) {
		arg0.appendText("Has attribute on index " + this.index + ": "
				+ attribute);
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
		DomAttr attr = (DomAttr) element.getAttributes().item(this.index);
		if (attr == null) {
			return String.format("On %s did not have attribute on index %s",
					element, this.index);
		}

		if (!matchesName(attr)) {
			return String.format(
					"On %s on attribute %s did not match expected name=%s",
					element, attr, this.attribute.getName());
		}

		if (!matchesValue(attr)) {
			return String.format(
					"On %s on attribute %s did not match expected value=%s",
					element, attr, this.attribute.getValue());
		}
		return null;
	}

	private boolean matchesName(Node node) {
		return node.getNodeName().equals(this.attribute.getName());
	}

	private boolean matchesValue(Node node) {
		return node.getNodeValue().equals(this.attribute.getValue());
	}

	public int getIndex() {
		return index;
	}

	@Factory
	public static HasAttribute hasAttribute(String attribute, int index) {
		return new HasAttribute(attribute, index);
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
		return new EqualsBuilder().append(this.index, rhs.index)
				.append(this.attribute.getValue(), rhs.attribute.getValue())
				.append(this.attribute.getName(), rhs.attribute.getName())
				.isEquals();

	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(index)
				.append(attribute.getName()).append(attribute.getValue())
				.toHashCode();
	}

}
