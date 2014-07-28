package com.moonillusions.htmlUnitMatchers.matchers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import static org.hamcrest.core.AllOf.allOf;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.moonillusions.htmlUnitMatchers.core.MatchableExtractor;

import static com.moonillusions.htmlUnitMatchers.matchers.AsText.asText;
import static com.moonillusions.htmlUnitMatchers.matchers.HasAttribute.hasAttribute;
import static org.hamcrest.Matchers.not;

public class HasOption extends AllOf<HtmlSelect> {

	private int index;

	public HasOption(int index, Iterable<Matcher<? super HtmlSelect>> arg0) {
		super(arg0);
		this.index = index;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(String.format("HtmlOption on index %s that: ",
				this.index));
		super.describeTo(description);
	}

	@Factory
	public static HasOption hasOption(String text, String value,
			boolean isSelected, final int index) {

		MatchableExtractor<DomNode, DomNode> extractor = new MatchableExtractor<DomNode, DomNode>() {
			@Override
			public DomNode extract(DomNode from) {
				return ((HtmlSelect) from).getOption(index);
			}
		};

		ArrayList<Matcher<? super HtmlSelect>> matchers = new ArrayList<Matcher<? super HtmlSelect>>();

		if (StringUtils.isNotEmpty(value)) {
			Matcher<? super HtmlSelect> e1 = hasAttribute("value", value,
					extractor);
			matchers.add(e1);
		}

		if (isSelected) {
			Matcher<? super HtmlSelect> e1 = hasAttribute("selected",
					"selected", extractor);
			matchers.add(e1);
		} else {
			Matcher<? super HtmlSelect> e1 = hasAttribute("selected",
					"selected", extractor);
			matchers.add(not(e1));
		}

		matchers.add(asText(text, extractor));

		return new HasOption(index, matchers);
	}

	@Factory
	public static HasOption hasOption(String text, String value, final int index) {
		return hasOption(text, value, false, index);
	}
	
	@Factory
	public static HasOption hasOption(String value, final int index) {
		return hasOption(value, value, false, index);
	}
	
	@Factory
	public static Matcher<HtmlSelect> hasOptions(String... values) {
		HasOption[] all = new HasOption[values.length];
		for(int i = 0; i < values.length; i++) {
			all[i] = hasOption(values[i],i);
		}
		return allOf(all);
	}

}
