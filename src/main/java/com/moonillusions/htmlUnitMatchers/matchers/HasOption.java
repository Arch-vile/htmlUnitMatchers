package com.moonillusions.htmlUnitMatchers.matchers;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Factory;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.moonillusions.htmlUnitMatchers.core.MatchableExtractor;
import com.moonillusions.htmlUnitMatchers.core.MatcherPair;

import static com.moonillusions.htmlUnitMatchers.matchers.AsText.asText;
import static com.moonillusions.htmlUnitMatchers.matchers.HasAttributes.hasAttributes;

public class HasOption extends MatcherCollection<HtmlSelect> {

	public HasOption(String text, String value, boolean isSelected,
			final int index) {

		MatchableExtractor<DomNode, HtmlSelect> extractor = new MatchableExtractor<DomNode, HtmlSelect>() {
			@Override
			public DomNode extract(HtmlSelect from) {
				return from.getOption(index);
			}
		};

		if (StringUtils.isNotEmpty(value)) {
			HasAttributes hasAttributes = hasAttributes("value=" + value);
			addMatcher(new MatcherPair<DomNode, HtmlSelect>(hasAttributes,
					extractor));
		}

		AsText asText = asText(text);
		addMatcher(new MatcherPair<DomNode, HtmlSelect>(asText, extractor));

	}

	@Factory
	public static HasOption hasOption(String text, String value, int index) {
		return new HasOption(text, value, false, index);
	}

	@Factory
	public static HasOption hasOption(String text, String value,
			boolean isSelected, int index) {
		return new HasOption(text, value, isSelected, index);
	}

}
