package com.moonillusions.htmlUnitMatchers;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Description;

import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlOption;

import static com.moonillusions.htmlUnitMatchers.HasAttributes2.hasAttributes;

public class IsOption extends ChainableMatcher<HtmlOption> {

	private Option option;
	
	public IsOption(Option option) {
		this.option = option;
		addMatcher(hasAttributes(option.getAttributes()));
	}

	@Override
	protected void chainedDescribeTo(Description desc) {
		desc.appendText("HtmlOption that:");
	}

	@Override
	protected List<HtmlOption> matchAgainst(HtmlOption option) {
		return Arrays.asList(option);
	}

	@Override
	protected boolean match(List arg0) {
		// TODO Auto-generated method stub
		return false;
	}


}
