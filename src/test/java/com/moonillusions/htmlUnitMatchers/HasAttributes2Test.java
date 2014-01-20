package com.moonillusions.htmlUnitMatchers;


import java.io.IOException;
import java.net.MalformedURLException;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import static com.moonillusions.htmlUnitMatchers.HasAttributes2.hasAttributes;

public class HasAttributes2Test {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void fails() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span attr1='1' attr2=\"3\">text</span>");
		Matcher<DomNode> test = hasAttributes(new Attribute("attr1", 1), new Attribute("attr2",2));
		
		assertThat(span, test);
		
		assertThat(test.matches(span), equalTo(false));
		TestUtils.assertDescribeTo(test, 
						"\n*DomNode that:" +
						"\n**Has attribute: attr1=1" +
						"\n**Has attribute: attr2=2");
		TestUtils.assertDescribeMismatch(test, span, 
						"\n*on DomNode: HtmlSpan[<span attr1=1 attr2=3>text<span>]" +
						"\n**on Attribute: Atttibute[attr2=3]" +
						"\n***failed because value '3' did not match expected value '2'");
	}
	
	
	
}
