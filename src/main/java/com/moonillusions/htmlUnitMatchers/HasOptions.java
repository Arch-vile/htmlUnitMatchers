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

import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

import static com.moonillusions.htmlUnitMatchers.AsText.asText;
import static com.moonillusions.htmlUnitMatchers.HasAttributes.hasAttributes;
import static org.hamcrest.Matchers.contains;

public class HasOptions extends BaseElementMatcher<HtmlSelect>  {

	List<Option> options;
	
	public HasOptions(List<Option> options) {
		this.options = options;
	}

	public void describeTo(Description arg0) {
		arg0.appendText("HtmlSelect with options: ").appendValueList("", ",", "", options);
		
	}

	@Override
	protected boolean matchesSafely(HtmlSelect select) {

		List<Option> actual = new ArrayList<Option>();
		for(HtmlOption option : select.getOptions()) {
			actual.add(new Option(option));
		}
		
		List<Matcher> matchers = new ArrayList<Matcher>();
		for(Option option : this.options) {
			matchers.add(hasAttributes(option.getAttributes()));
		}
		
		
		
		
		Matcher contains = contains(matchers);
		
		assertThat(actual, contains);
		
		if (!contains(matchers).matches(actual)) {
			setFailingMatcher(contains);
			return false;
		}
		
		
		//List<HtmlOption> actualOptions = select.getOptions();
		
//		if(this.options.size() > actualOptions.size()) {
//			setMismatchMessage("none matched " + this.options.get(actualOptions.size()));
//			return false;
//		}
//		
//		if(this.options.size() < actualOptions.size()) {
//			setMismatchMessage("extra option " + StringUtils.print(actualOptions.get(this.options.size())) + " was found");
//			return false;
//		}
//		
//		
//		if(actualOptions.size() != this.options.size()) return false;
//		
//		for(int i = 0; i < this.options.size(); i++) {
//			HtmlOption actualOption = actualOptions.get(i);
//			Option expectedOption = this.options.get(i);
//			
//			if(!hasAttributes(expectedOption.getAttributes()).matches(actualOption) ||
//					!asText(expectedOption.getValue().toString()).matches(actualOption)) {
//				setMismatchMessage("option on index " + i + " did not match " + expectedOption);
//				return false;
//			}
//			
//		}
		
		return true;
	}
	
	
	@Factory
	public static <T> Matcher<HtmlSelect> hasOptions(Option...options) {
	    return new HasOptions(Arrays.asList(options));
	}

	@Override
	protected Description getDescribeTo(Description description) {
		return description;
	}
	

}
