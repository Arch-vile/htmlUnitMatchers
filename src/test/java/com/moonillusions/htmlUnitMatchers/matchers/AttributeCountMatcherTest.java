package com.moonillusions.htmlUnitMatchers.matchers;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.moonillusions.htmlUnitMatchers.TestUtils;

import static com.moonillusions.htmlUnitMatchers.matchers.AttributeCountMatcher.hasAttributes;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class AttributeCountMatcherTest {

	@Test
	public void matchesNonZeroAttributes()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1=1 attr2=2>foo</span>");
		assertThat(span, hasAttributes(2));
	}

	@Test
	public void matchesZeroAttributes() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span>foo</span>");
		assertThat(span, hasAttributes(0));
	}

	@Test
	public void mismatchIfTooFewAttributes()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils.createDomNode("<span attr1=1>foo</span>");
		assertThat(hasAttributes(2).matches(span), equalTo(false));
	}

	@Test
	public void mismatchIfTooManyAttributes()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1=1 attr2=2>foo</span>");
		assertThat(hasAttributes(1).matches(span), equalTo(false));
	}

	@Test
	public void mismatchIfHaveUnExpected()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1=1 attr2=2>foo</span>");
		assertThat(hasAttributes(0).matches(span), equalTo(false));
	}

	@Test
	public void mismatchIfHaveNoAttributes()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils.createDomNode("<span>foo</span>");
		assertThat(hasAttributes(1).matches(span), equalTo(false));
	}

	@Test
	public void describe() {
		TestUtils.assertDescribeTo(hasAttributes(2), "Has 2 attributes");
	}

	@Test
	public void mismatchDecriptionIfCountMismatch()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		TestUtils
				.assertDescribeMismatch(hasAttributes(2),
						TestUtils.createDomNode("<span attr1=1>foo</span>"),
						"HtmlSpan[<span attr1=1>foo</span>] has 1 attributes instead of the expected 2");
	}

	@Test
	public void equals() {
		AttributeCountMatcher test1 = hasAttributes(2);
		AttributeCountMatcher test2 = hasAttributes(2);
		assertThat(test1, equalTo(test2));
		assertThat(test1, equalTo(test1));
		assertThat(test1, not(equalTo(null)));
		assertThat(test1.equals(new Integer(2)), equalTo(false));
	}

	@Test
	public void equalsFailsIfCountMismatch() {
		AttributeCountMatcher test1 = hasAttributes(2);
		AttributeCountMatcher test2 = hasAttributes(3);
		assertThat(test1, not(equalTo(test2)));
	}

	@Test
	public void hash() {
		AttributeCountMatcher test1 = hasAttributes(2);
		AttributeCountMatcher test2 = hasAttributes(2);
		AttributeCountMatcher test3 = hasAttributes(3);
		assertThat(test1.hashCode(), equalTo(test2.hashCode()));
		assertThat(test3.hashCode(), not(equalTo(test2.hashCode())));
	}
}
