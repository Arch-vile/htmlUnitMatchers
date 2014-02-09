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
        		.createDomNode("<span attr1='1' attr2=\"2\">text</span>"), 
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
        		.createDomNode("<span attr1='1' attr1='1'>text</span>"), 
        		hasAttributes(new Attribute("attr1", "1")));
	}
	
	
	
	@Test
	public void failsIfWrongOrder() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span attr1='1' attr2=\"2\">text</span>");
		Matcher<DomNode> test = hasAttributes(new Attribute("attr2", 2), new Attribute("attr1",1));
		
		assertThat(test.matches(span), equalTo(false));
		TestUtils.assertDescribeMismatch(test, span, 
				"On HtmlSpan[<span attr1=1 attr2=2>text</span>] on Attributes[<DomAttr[name=attr1 value=1]>,<DomAttr[name=attr2 value=2]>]"
				+ "\n*On attribute: DomAttr[name=attr1 value=1] did not match expected name=attr2");
	}
	
	@Test
	public void failsIfElementHasExtraAttributes() throws IOException {
		HtmlElement span = TestUtils.createDomNode("<span attr1='1' attr2=\"2\" attr3='3'>text</span>");
		Matcher<DomNode> test = hasAttributes(new Attribute("attr1", 1), new Attribute("attr2",2));
		
		assertThat(test.matches(span), equalTo(false));
		TestUtils.assertDescribeTo(test, "DomNode with attributes: <iterable containing [<attr1='1'>, <attr2='2'>]>");
		TestUtils.assertDescribeMismatch(test, span, "Not matched: <" +
				new Attribute("attr3", 3) + 
				"> in " + 
				StringUtils.print(span));
		
	}
	
	
	@Test
	public void failsIfElementHasLessAttributes() throws IOException {
		Matcher<DomNode> test = hasAttributes(new Attribute("attr1", 1), new Attribute("attr2",2));
		HtmlElement span = TestUtils.createDomNode("<span attr1='1'>text</span>");
		
		assertThat(test.matches(span), equalTo(false));
		TestUtils.assertDescribeTo(test, "DomNode with attributes: <iterable containing [<attr1='1'>, <attr2='2'>]>");
		TestUtils.assertDescribeMismatch(test, span, "No item matched: <" +
				new Attribute("attr2", 2) + 
				"> in " + 
				StringUtils.print(span));
	}
	
	@Test
	public void failsIfAttributeValuesNoMatch() throws IOException {
		Matcher<DomNode> test = hasAttributes(new Attribute("attr1", 1), new Attribute("attr2",2));
		assertThat(test
				.matches(TestUtils.createDomNode("<span attr1='1' attr2='3'>text</span>")), 
				equalTo(false));
	}
	
	
	@Test
	public void failsIfElementHasNoAttributes() throws IOException {
		Matcher<DomNode> test = hasAttributes(new Attribute("attr1", 1), new Attribute("attr2",2));
		HtmlElement span = TestUtils.createDomNode("<span>text</span>");
		assertThat(test.matches(span),equalTo(false));
		TestUtils.assertDescribeTo(test, "DomNode with attributes: <iterable containing [<attr1='1'>, <attr2='2'>]>");
		TestUtils.assertDescribeMismatch(test, span, "No item matched: <" +
				new Attribute("attr1", 1) + 
				"> in " + 
				StringUtils.print(span));
	}
	
	
	@Test
	public void failsIfElementHasAttributes() throws IOException {
		Matcher<DomNode> test = hasAttributes();
		HtmlElement span = TestUtils.createDomNode("<span attr1='1'>text</span>");
		
		assertThat(test.matches(span), equalTo(false));
		TestUtils.assertDescribeTo(test, "DomNode with attributes: <an empty collection>");
		TestUtils.assertDescribeMismatch(test, span, "<[" +
				new Attribute("attr1", 1) + 
				"]> found in " + 
				StringUtils.print(span));
		
	}
	
	
	
}
