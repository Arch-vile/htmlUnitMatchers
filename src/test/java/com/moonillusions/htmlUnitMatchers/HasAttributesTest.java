package com.moonillusions.htmlUnitMatchers;


import static com.moonillusions.htmlUnitMatchers.HasAttributes.hasAttributes;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.net.MalformedURLException;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import static org.hamcrest.Matchers.equalTo;


public class HasAttributesTest {

	
	private Integer first;
	private Integer second;
	
	@Before
	public void setUp() throws Exception {
		first = 1;
		second = 2;
	}

	@Test
	public void matches() throws IOException {
        assertThat(TestUtils
        		.createDomNode("<span attr1='1' attr2=\"2\">text</span>"), 
        		hasAttributes("attr1",first,"attr2",second));
	}
	
	@Test
	public void description() throws IOException {
		Matcher<DomNode> test = hasAttributes("attr1",first,"attr2",second);
		Description description = new StringDescription();
		test.describeTo(description);
		assertThat(description.toString(), equalTo("DomNode with attributes attr1='1', attr2='2'"));
	}
	
	@Test
	public void failsIfElementHasExtraAttributes() throws IOException {
		Matcher<DomNode> test = hasAttributes("attr1",first,"attr2",second);
		assertThat(test
				.matches(TestUtils.createDomNode("<span attr1='1' attr3='3' attr2=\"2\">text</span>")), 
				equalTo(false));
	}
	
	
	@Test
	public void failsIfElementHasLessAttributes() throws IOException {
		Matcher<DomNode> test = hasAttributes("attr1",first,"attr2",second);
		assertThat(test
				.matches(TestUtils.createDomNode("<span attr1='1'>text</span>")), 
				equalTo(false));
	}
	
	@Test
	public void failsIfAttributeValuesNoMatch() throws IOException {
		Matcher<DomNode> test = hasAttributes("attr1",first,"attr2",second);
		assertThat(test
				.matches(TestUtils.createDomNode("<span attr1='1' attr2='3'>text</span>")), 
				equalTo(false));
	}
	
	
	@Test
	public void failsIfElementHasNoAttributes() throws IOException {
		Matcher<DomNode> test = hasAttributes("attr1",first,"attr2",second);
		assertThat(test
				.matches(TestUtils.createDomNode("<span>text</span>")), 
				equalTo(false));
	}
	
	
}
