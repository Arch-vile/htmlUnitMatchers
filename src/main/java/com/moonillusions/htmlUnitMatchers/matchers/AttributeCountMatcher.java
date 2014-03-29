package com.moonillusions.htmlUnitMatchers.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.core.MatchableExtractor;
import com.moonillusions.htmlUnitMatchers.utils.StringUtils;

public class AttributeCountMatcher extends
		DerivedMatchableMatcher<DomNode, DomNode> {

	private int count;

	public AttributeCountMatcher(int count,
			MatchableExtractor<DomNode, DomNode> extractor) {
		super(extractor);
		this.count = count;
	}

	@Override
	protected void describeMismatchSafely(DomNode item,
			Description mismatchDescription) {
		mismatchDescription.appendText(StringUtils.print(item)
				+ String.format(
						" has %s attributes instead of the expected %s",
						attributeCount(item), this.count));

	}

	@Override
	protected boolean matchesSafely(DomNode item) {
		return attributeCount(item) == this.count;
	}

	private int attributeCount(DomNode item) {
		return item.getAttributes().getLength();
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(String.format("Has %s attributes", this.count));

	}

	@Factory
	public static AttributeCountMatcher hasAttributes(int count) {
		return new AttributeCountMatcher(count, null);
	}

}
