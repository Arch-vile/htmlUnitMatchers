package com.moonillusions.htmlUnitMatchers.matchers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.moonillusions.htmlUnitMatchers.TestUtils;
import com.moonillusions.htmlUnitMatchers.core.MatcherPair;

import static com.moonillusions.htmlUnitMatchers.matchers.AttributeCountMatcher.hasAttributes;
import static com.moonillusions.htmlUnitMatchers.matchers.HasAttribute.hasAttribute;
import static com.moonillusions.htmlUnitMatchers.matchers.HasAttributes.hasAttributes;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HasAttributesTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void hasCorrectMatchers() {
		HasAttributes test = hasAttributes(
				TestUtils.createAttribute("attr2", 2),
				TestUtils.createAttribute("attr1", 1));
		List<MatcherPair<?, DomNode>> matchers = test.getMatchers();
		assertThat(matchers.size(), equalTo(3));
		assertThat((AttributeCountMatcher) matchers.get(0).getMatcher(),
				equalTo(hasAttributes(2)));
		assertThat((HasAttribute) matchers.get(1).getMatcher(),
				equalTo(hasAttribute(TestUtils.createAttribute("attr2", 2), 0)));
		assertThat((HasAttribute) matchers.get(2).getMatcher(),
				equalTo(hasAttribute(TestUtils.createAttribute("attr1", 1), 1)));
	}

	@Test
	public void matchesWithAttributes() throws IOException {
		assertThat(
				TestUtils
						.createDomNode("<span attr1='1' attr2='2'>text</span>"),
				hasAttributes(TestUtils.createAttribute("attr1", 1),
						TestUtils.createAttribute("attr2", 2)));

	}

	@Test
	public void matchesWithoutAttributes() throws IOException {
		assertThat(TestUtils.createDomNode("<span>text</span>"),
				hasAttributes());
	}

	@Test
	public void mismatchIfAttributesInWrongOrder()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1='1' attr2='2'>text</span>");
		HasAttributes test = hasAttributes(
				TestUtils.createAttribute("attr2", 2),
				TestUtils.createAttribute("attr1", 1));
		assertThat(test.matches(span), equalTo(false));
		MatcherPair<?, DomNode> attrTest = test.getFailedMatcher();
		HasAttribute hasAttr = (HasAttribute) attrTest.getMatcher();
		assertThat(hasAttr.getIndex(), equalTo(2));

	}

	@Test
	public void mismatchIfTooManyAttributes() throws IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1='1' attr2='2' attr3='3'>text</span>");
		Matcher<DomNode> test = hasAttributes(
				TestUtils.createAttribute("attr1", 1),
				TestUtils.createAttribute("attr2", 2));
		assertThat(test.matches(span), equalTo(false));
	}

	@Test
	public void mismatchIfTooFewAttributes() throws IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1='1'>text</span>");
		Matcher<DomNode> test = hasAttributes(
				TestUtils.createAttribute("attr1", 1),
				TestUtils.createAttribute("attr2", 2));
		assertThat(test.matches(span), equalTo(false));
	}

	@Test
	public void mismatchIfAttributeValueNoMatch() throws IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1='1'>text</span>");
		Matcher<DomNode> test = hasAttributes(TestUtils.createAttribute(
				"attr1", "one"));
		assertThat(test.matches(span), equalTo(false));
	}

	@Test
	public void mismatchIfNoAttributes() throws IOException {
		HtmlElement span = TestUtils.createDomNode("<span>text</span>");
		Matcher<DomNode> test = hasAttributes(TestUtils.createAttribute(
				"attr1", 1));
		assertThat(test.matches(span), equalTo(false));
	}

	@Test
	public void mismatchIfElementHasAttributes() throws IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1='1'>text</span>");
		Matcher<DomNode> test = hasAttributes();
		assertThat(test.matches(span), equalTo(false));
	}

	@Test
	public void verifyDescription() {
		Matcher<DomNode> test = hasAttributes(TestUtils.createAttribute(
				"attr1", 1));
		TestUtils.assertDescribeTo(test, "Matches all:" + "\n*Has 1 attributes"
				+ "\n*Has attribute on index 0: DomAttr[name=attr1 value=1]");
	}

	@Test
	public void verifyMismatchDescriptionAttributeCount()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1='1'>text</span>");
		Matcher<DomNode> test = hasAttributes(
				TestUtils.createAttribute("attr1", 1),
				TestUtils.createAttribute("attr2", 2));
		test.matches(span);
		TestUtils
				.assertDescribeMismatch(
						test,
						span,
						"Failed on:"
								+ "\n*HtmlSpan[<span attr1=1>text</span>] has 1 attributes instead of the expected 2");
	}

	@Test
	public void verifyMismatchDescriptionAttributeValue()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1='1'>text</span>");
		Matcher<DomNode> test = hasAttributes(TestUtils.createAttribute(
				"attr1", "one"));
		test.matches(span);
		TestUtils
				.assertDescribeMismatch(
						test,
						span,
						"Failed on:"
								+ "\n*On HtmlSpan[<span attr1=\"1\">] on attribute DomAttr[name=attr1 value=1] did not match expected value=one");
	}

	@Test
	public void verifyMismatchDescriptionAttributeName()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr='1'>text</span>");
		Matcher<DomNode> test = hasAttributes(TestUtils.createAttribute(
				"count", "1"));
		test.matches(span);
		TestUtils
				.assertDescribeMismatch(
						test,
						span,
						"Failed on:"
								+ "\n*On HtmlSpan[<span attr=\"1\">] on attribute DomAttr[name=attr value=1] did not match expected name=count");
	}

}
