package com.moonillusions.htmlUnitMatchers;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

import static com.moonillusions.htmlUnitMatchers.AsText.asText;
import static com.moonillusions.htmlUnitMatchers.HasAttributes.hasAttributes;

public class HasOptions extends TypeSafeMatcher<HtmlSelect>  {

	List<Option> options;
	
	public HasOptions(List<Option> options) {
		this.options = options;
	}

	public void describeTo(Description arg0) {
		arg0.appendText("HtmlSelect with options: ").appendValueList("", ",", "", options);
		
	}

	@Override
	protected boolean matchesSafely(HtmlSelect select) {
		
		List<HtmlOption> actualOptions = select.getOptions();
		if(actualOptions.size() != this.options.size()) return false;
		
		for(Option option : this.options){
			boolean match = false;
			for(HtmlOption actualOption : actualOptions) {
				if( hasAttributes(option.getAttributes()).matches(actualOption) &&
						asText(option.getValue().toString()).matches(actualOption)) {
					match = true;
				}
			}
			if(!match) return false;
		}
		
		return true;
	}
	
	
	@Factory
	public static <T> Matcher<HtmlSelect> hasOptions(Option...options) {
	    return new HasOptions(Arrays.asList(options));
	}
	

}
