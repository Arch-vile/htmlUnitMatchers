package com.moonillusions.htmlUnitMatchers.matchers;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hamcrest.Description;
import org.hamcrest.Factory;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.core.ElementMatcher;
import com.moonillusions.htmlUnitMatchers.utils.StringUtils;

public class AttributeCountMatcher extends ElementMatcher<DomNode> {

	private int count;

	public AttributeCountMatcher(int count) {
		this.count = count;
	}

	@Override
	protected void mismatch(DomNode item, Description mismatchDescription) {
		mismatchDescription.appendText(StringUtils.print(item)
				+ String.format(
						" has %s attributes instead of the expected %s",
						attributeCount(item), this.count));

	}

	@Override
	protected boolean match(DomNode item) {
		return attributeCount(item) == this.count;
	}

	private int attributeCount(DomNode item) {
		return item.getAttributes().getLength();
	}

	@Override
	protected void describe(Description arg0) {
		arg0.appendText(String.format("Has %s attributes", this.count));

	}

	@Factory
	public static AttributeCountMatcher hasAttributes(int count) {
		return new AttributeCountMatcher(count);
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
		AttributeCountMatcher rhs = (AttributeCountMatcher) obj;
		return new EqualsBuilder().append(this.count, rhs.count).isEquals();

	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 39).append(count).toHashCode();
	}

}
