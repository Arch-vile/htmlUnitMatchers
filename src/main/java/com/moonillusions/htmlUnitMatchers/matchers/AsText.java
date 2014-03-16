package com.moonillusions.htmlUnitMatchers.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.core.ElementMatcher;
import com.moonillusions.htmlUnitMatchers.utils.StringUtils;

public class AsText extends ElementMatcher<DomNode> {

	private String text;

	public AsText(String text) {
		this.text = text;
	}

	@Override
	protected void mismatch(DomNode item, Description mismatchDescription) {
		mismatchDescription.appendText(String.format(
				"On %s as text [%s] did not match the expected [%s]",
				StringUtils.print(item), item.asText(), this.text));

	}

	@Override
	protected boolean match(DomNode item) {
		return this.text.equals(item.asText());
	}

	@Override
	protected void describe(Description arg0) {
		arg0.appendText(String.format("Element with text content: [%s]",
				this.text));
	}

	@Factory
	public static AsText asText(String text) {
		return new AsText(text);
	}

}
