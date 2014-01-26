package com.moonillusions.htmlUnitMatchers;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.object.HasToString;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

import static com.moonillusions.htmlUnitMatchers.AsText.asText;
import static com.moonillusions.htmlUnitMatchers.HasAttributes.hasAttributes;
import static org.hamcrest.Matchers.contains;

public class HasOptions extends ChainableMatcher<HtmlSelect> { 

	List<Option> options;
	
	public HasOptions(List<Option> options) {
		this.options = options;
		
		for(Option option : options) {
			addMatcher(new IsOption(option));
		}
		
	}


	@Factory
	public static <T> Matcher<HtmlSelect> hasOptions(Option...options) {
	    return new HasOptions(Arrays.asList(options));
	}


	@Override
	protected void chainedDescribeTo(Description desc) {
		desc.appendText("HtmlSelect element that");
		
	}

	@Override
	protected void chainedMismatch(HtmlSelect item, Description desc){
		desc.appendText("On HtmlSelect " + StringUtils.print(item));
	}

	@Override
	protected boolean match(List arg0) {
		return false;
	}


	@Override
	protected List<HtmlOption> matchAgainst(HtmlSelect item) {
		return item.getOptions();
	}


	

}
