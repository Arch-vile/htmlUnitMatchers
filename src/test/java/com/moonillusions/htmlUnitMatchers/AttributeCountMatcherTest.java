package com.moonillusions.htmlUnitMatchers;

import java.io.IOException;
import java.net.MalformedURLException;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

import static com.moonillusions.htmlUnitMatchers.HasOptions.hasOptions;
import static foo.AttributeCountMatcher.hasAttributes;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AttributeCountMatcherTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void matchesWhenAttributeCountMatch() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span attr1='1' attr2=\"2\">text</span>");
		Matcher<DomNode> test = hasAttributes(2);
		assertThat(span, test);

//		assertThat(test.matches(span), equalTo(false));
//		TestUtils.assertDescribeMismatch(test, span, 
//				"Failed on matcher: \n*On HtmlSpan[<span attr1=\"1\" attr2=\"2\">] on attribute DomAttr[name=attr1 value=1] did not match expected name=attr2");
//		
//		assertThat(
//				(HtmlSelect)TestUtils.createDomNode("<select>"
//						+ "<option value=\"1\">one</option>"
//						+ "<option value=\"2\">two</option>"
//						+ "</select>"), 
//				hasOptions(
//						new Option("one").withAttr("value",1),
//						new Option("two").withAttr("value",2)));
	}
	
	@Test
	public void matchesWithoutAttributes() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span>text</span>");
		Matcher<DomNode> test = hasAttributes(0);
		assertThat(span, test);
	}
	
	@Test
	public void matchesNoValueAttributes() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span name>text</span>");
		Matcher<DomNode> test = hasAttributes(1);
		assertThat(span, test);
	}
	
	@Test
	public void matchesIgnoredDuplicateAttributes() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span name=age name=2>text</span>");
		Matcher<DomNode> test = hasAttributes(1);
		assertThat(span, test);
	}
	
	

	@Test
	public void mismatchIfTooFewAttributes() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span name=mikko age=2>text</span>");
		Matcher<DomNode> test = hasAttributes(3);
		assertThat(test.matches(span), equalTo(false));
	}
	
	
	@Test
	public void verifyDescription() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span name=mikko age=2>text</span>");
		Matcher<DomNode> test = hasAttributes(3);
		TestUtils.assertDescribeTo(test, "Has 3 attributes");
	}
	
	@Test
	public void verifyMistmatchDescriptionIfTooFewAttributes() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span name=mikko age=2>text</span>");
		Matcher<DomNode> test = hasAttributes(3);
		TestUtils.assertDescribeMismatch(test, span, "HtmlSpan[<span name=mikko age=2>text</span>] has 2 attributes instead of the expected 3");
	}
	
	@Test
	public void verifyMistmatchDescriptionIfTooManyAttributes() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span name=mikko>text</span>");
		Matcher<DomNode> test = hasAttributes(3);
		TestUtils.assertDescribeMismatch(test, span, "HtmlSpan[<span name=mikko>text</span>] has 1 attributes instead of the expected 3");
	}
		
}
