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
import static org.hamcrest.MatcherAssert.assertThat;

public class HasAttributesTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void hasCorrectMatchers() {
		HasAttributes test = hasAttributes("attr2=2", "attr1=1");
		List<MatcherPair<?, DomNode>> matchers = test.getMatchers();
		assertThat(matchers.size(), equalTo(3));
		assertThat((AttributeCountMatcher) matchers.get(0).getMatcher(),
				equalTo(hasAttributes(2)));
		assertThat((HasAttribute) matchers.get(1).getMatcher(),
				equalTo(hasAttribute("attr2=2", 0)));
		assertThat((HasAttribute) matchers.get(2).getMatcher(),
				equalTo(hasAttribute("attr1=1", 1)));
	}

	@Test
	public void matchesWithAttributes() throws IOException {
		assertThat(
				TestUtils
						.createDomNode("<span attr1='1' attr2='2'>text</span>"),
				hasAttributes("attr1=1", "attr2=2"));

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
		HasAttributes test = hasAttributes("attr2=2", "attr1=1");
		assertThat(test.matches(span), equalTo(false));
		MatcherPair<?, DomNode> attrTest = test.getFailedMatcher();
		HasAttribute hasAttr = (HasAttribute) attrTest.getMatcher();
		assertThat(hasAttr.getIndex(), equalTo(0));

	}

	@Test
	public void mismatchIfAttributeCountWrong() throws IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1='1' attr2='2' attr3='3'>text</span>");
		HasAttributes test = hasAttributes("attr1=1", "attr2=2");
		assertThat(test.matches(span), equalTo(false));
		MatcherPair<?, DomNode> countTest = test.getFailedMatcher();
		assertThat(countTest.getMatcher(),
				instanceOf(AttributeCountMatcher.class));
	}

	@Test
	public void mismatchIfAttributeValueNoMatch() throws IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1='1'>text</span>");
		Matcher<DomNode> test = hasAttributes("attr1=one");
		assertThat(test.matches(span), equalTo(false));
	}

	@Test
	public void mismatchIfNoAttributes() throws IOException {
		HtmlElement span = TestUtils.createDomNode("<span>text</span>");
		Matcher<DomNode> test = hasAttributes("attr1=1");
		assertThat(test.matches(span), equalTo(false));
	}

	@Test
	public void mismatchIfElementHasAttributes() throws IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1='1'>text</span>");
		Matcher<DomNode> test = hasAttributes();
		assertThat(test.matches(span), equalTo(false));
	}

}
