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
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
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
		assertThat(hasAttr.getIndex(), equalTo(0));

	}

	@Test
	public void mismatchIfAttributeCountWrong() throws IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1='1' attr2='2' attr3='3'>text</span>");
		HasAttributes test = hasAttributes(
				TestUtils.createAttribute("attr1", 1),
				TestUtils.createAttribute("attr2", 2));
		assertThat(test.matches(span), equalTo(false));
		MatcherPair<?, DomNode> countTest = test.getFailedMatcher();
		assertThat(countTest.getMatcher(),
				instanceOf(AttributeCountMatcher.class));
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
	public void equals() {
		HasAttributes test1 = hasAttributes(TestUtils.createAttribute("attr1",
				1));
		HasAttributes test2 = hasAttributes(TestUtils.createAttribute("attr1",
				1));
		HasAttributes test3 = hasAttributes(TestUtils.createAttribute("attr2",
				1));
		HasAttributes test4 = hasAttributes(TestUtils.createAttribute("attr1",
				2));
		assertThat(test1, equalTo(test1));
		assertThat(test1, equalTo(test2));
		assertThat(test1, not(equalTo(test3)));
		assertThat(test1, not(equalTo(test4)));
		assertThat(test1, not(equalTo(null)));
		assertThat(test1.equals(new Integer(2)), equalTo(false));
	}

	@Test
	public void hash() {
		HasAttributes test1 = hasAttributes(TestUtils.createAttribute("attr1",
				1));
		HasAttributes test2 = hasAttributes(TestUtils.createAttribute("attr1",
				1));
		HasAttributes test3 = hasAttributes(TestUtils.createAttribute("attr2",
				1));
		HasAttributes test4 = hasAttributes(TestUtils.createAttribute("attr1",
				2));
		assertThat(test1.hashCode(), equalTo(test1.hashCode()));
		assertThat(test1.hashCode(), equalTo(test2.hashCode()));
		assertThat(test1.hashCode(), not(equalTo(test3.hashCode())));
		assertThat(test1.hashCode(), not(equalTo(test4.hashCode())));
	}

}
