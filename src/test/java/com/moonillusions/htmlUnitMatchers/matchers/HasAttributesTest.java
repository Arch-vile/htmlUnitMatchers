package com.moonillusions.htmlUnitMatchers.matchers;

import java.io.IOException;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.moonillusions.htmlUnitMatchers.TestUtils;

import static com.moonillusions.htmlUnitMatchers.matchers.HasAttributes.hasAttributes;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class HasAttributesTest {

	@Before
	public void setUp() throws Exception {
	}

	// TODO:
	// @Test
	// public void hasCorrectMatchers() {
	// HasAttributes test = hasAttributes("attr2=2", "attr1=1");
	// List<MatcherPair<?, DomNode>> matchers = test.getMatchers();
	// assertThat((HasAttribute) matchers.get(0).getMatcher(),
	// equalTo(hasAttribute("attr2=2")));
	// assertThat((HasAttribute) matchers.get(1).getMatcher(),
	// equalTo(hasAttribute("attr1=1")));
	// }

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
	public void matchesIfElementHasExtraAttributes() throws IOException {
		assertThat(
				TestUtils.createDomNode("<span attr1='1' attr2=2>text</span>"),
				hasAttributes("attr2=2"));
	}

	@Test
	public void mismatchIfElementMissingAttributes() throws IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1='1' attr2=2>text</span>");
		Matcher<DomNode> test = hasAttributes("attr2=2", "attr1=1", "attr3=3");
		assertThat(span, not(test));
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

}
