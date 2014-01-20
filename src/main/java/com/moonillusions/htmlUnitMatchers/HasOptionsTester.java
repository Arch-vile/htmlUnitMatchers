package com.moonillusions.htmlUnitMatchers;

import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlSelect;

public class HasOptionsTester extends BaseTester<HtmlSelect> {

	List<Option> options;
	HtmlSelect tested;
	
	public HasOptionsTester(List<Option> options) {
		this.options = options;
	}

	@Override
	public boolean matches(HtmlSelect tested) {
		this.tested = tested;
		return false;
	}

	@Override
	public String getMismatch() {
		return "On " + StringUtils.print(this.tested);
	}




}
