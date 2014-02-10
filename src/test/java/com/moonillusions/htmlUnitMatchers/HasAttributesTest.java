package com.moonillusions.htmlUnitMatchers;


import java.io.IOException;
import java.net.MalformedURLException;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

import static foo.HasAttributes.hasAttributes;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;




public class HasAttributesTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void matches() throws IOException {
        assertThat(TestUtils
        		.createDomNode("<span attr1='1' attr2='2'>text</span>"), 
        		hasAttributes(new Attribute("attr1", 1), new Attribute("attr2",2)));
        
	}
	
	@Test
	public void matchesWithoutAttributes() throws IOException {
        assertThat(TestUtils
        		.createDomNode("<span>text</span>"), 
        		hasAttributes());
	}
	
	@Test
	public void matchesNoValueAttributes() throws IOException {
        assertThat(TestUtils
        		.createDomNode("<span novalue>text</span>"), 
        		hasAttributes(new Attribute("novalue", "")));
	}
	
	@Test
	public void matchesDuplicateAttributes() throws IOException {
        assertThat(TestUtils
        		.createDomNode("<span attr1='1' attr1='2'>text</span>"), 
        		hasAttributes(new Attribute("attr1", "1")));
	}
	
	
	
	
	
	@Test
	public void mismatchIfAttributesInWrongOrder() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span attr1='1' attr2='2'>text</span>");
		Matcher<DomNode> test = hasAttributes(new Attribute("attr2", 2), new Attribute("attr1",1));
		assertThat(test.matches(span), equalTo(false));
	}
	
	@Test
	public void mismatchIfTooManyAttributes() throws IOException {
		HtmlElement span = TestUtils.createDomNode("<span attr1='1' attr2='2' attr3='3'>text</span>");
		Matcher<DomNode> test = hasAttributes(new Attribute("attr1", 1), new Attribute("attr2",2));
		assertThat(test.matches(span), equalTo(false));
	}
	
	
	@Test
	public void mismatchIfTooFewAttributes() throws IOException {
		HtmlElement span = TestUtils.createDomNode("<span attr1='1'>text</span>");
		Matcher<DomNode> test = hasAttributes(new Attribute("attr1", 1), new Attribute("attr2",2));
		assertThat(test.matches(span), equalTo(false));
	}
	
	@Test
	public void mismatchIfAttributeValueNoMatch() throws IOException {
		HtmlElement span = TestUtils.createDomNode("<span attr1='1'>text</span>");
		Matcher<DomNode> test = hasAttributes(new Attribute("attr1", "one"));
		assertThat(test.matches(span), equalTo(false));
	}
	
	@Test
	public void mismatchIfNoAttributes() throws IOException {
		HtmlElement span = TestUtils.createDomNode("<span>text</span>");
		Matcher<DomNode> test = hasAttributes(new Attribute("attr1", 1));
		assertThat(test.matches(span),equalTo(false));
	}
	
	
	@Test
	public void mismatchIfElementHasAttributes() throws IOException {
		HtmlElement span = TestUtils.createDomNode("<span attr1='1'>text</span>");
		Matcher<DomNode> test = hasAttributes();
		assertThat(test.matches(span), equalTo(false));
	}
	
	
	@Test
	public void verifyDescription() {
		Matcher<DomNode> test = hasAttributes(new Attribute("attr1", 1));
		TestUtils.assertDescribeTo(test, "Matches all:"
				+ "\n*Has 1 attribute"
				+ "\n*Has attributex");
	}
	
	
}
