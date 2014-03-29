package com.moonillusions.htmlUnitMatchers.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.core.MatchableExtractor;
import com.moonillusions.htmlUnitMatchers.utils.StringUtils;

public class AsText extends DerivedMatchableMatcher<DomNode, DomNode> {

	private String text;

	public AsText(String text, MatchableExtractor<DomNode, DomNode> extractor) {
		super(extractor);
		this.text = text;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(String.format("has as text: [%s]", this.text));

	}

	@Override
	protected void describeMismatchSafely(DomNode item,
			Description mismatchDescription) {
		mismatchDescription.appendText(String.format(
				"did not match [%s] on %s", item.asText(),
				StringUtils.print(matchable(item))));
	}

	@Override
	protected boolean matchesSafely(DomNode item) {
		return this.text.equals(matchable(item).asText());
	}

	@Factory
	public static AsText asText(String text) {
		return new AsText(text, null);
	}

	@Factory
	public static AsText asText(String text,
			MatchableExtractor<DomNode, DomNode> extractor) {
		return new AsText(text, extractor);
	}

}
