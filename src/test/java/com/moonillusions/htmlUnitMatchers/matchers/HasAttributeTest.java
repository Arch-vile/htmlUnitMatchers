package com.moonillusions.htmlUnitMatchers.matchers;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.moonillusions.htmlUnitMatchers.TestUtils;

import static com.moonillusions.htmlUnitMatchers.matchers.HasAttribute.hasAttribute;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;

public class HasAttributeTest {

	@Test
	public void matchesSingleAttribute() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		assertThat(TestUtils.createDomNode("<span attr1=1>text</span>"),
				hasAttribute("attr1=1", 0));
	}

	@Test
	public void matchesMultipleAttributes()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		assertThat(
				TestUtils.createDomNode("<span attr1=1 attr2=2>text</span>"),
				allOf(hasAttribute("attr1=1", 0), hasAttribute("attr2=2", 1)));
	}

	@Test
	public void matchesNoValueAttributes() throws IOException {
		assertThat(TestUtils.createDomNode("<span novalue>text</span>"),
				hasAttribute("novalue", 0));
	}

	@Test
	public void matchesEmptyValueAttributes() throws IOException {
		assertThat(TestUtils.createDomNode("<span novalue>text</span>"),
				hasAttribute("novalue=", 0));
	}

	@Test
	public void matchesDuplicateAttribute() throws IOException {
		assertThat(
				TestUtils.createDomNode("<span attr1=1 attr1=2>text</span>"),
				hasAttribute("attr1=1", 0));
	}

	@Test
	public void failsIfAttributeNameMismatch()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1=1 attr2=2>text</span>");
		HasAttribute test = hasAttribute("age=1", 0);
		assertThat(test.match(span), equalTo(false));
	}

	@Test
	public void failsIfAttributeValueMismatch()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1=1 attr2=2>text</span>");
		HasAttribute test = hasAttribute("attr1=some", 0);
		assertThat(test.match(span), equalTo(false));
	}

	@Test
	public void failsIfAttributeIndexMismatch()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils
				.createDomNode("<span attr1=1 attr2=2>text</span>");
		HasAttribute test = hasAttribute("attr1=1", 1);
		assertThat(test.match(span), equalTo(false));
	}

	@Test
	public void failsIfNoAttributes() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span>text</span>");
		HasAttribute test = hasAttribute("attr1=1", 1);
		assertThat(test.match(span), equalTo(false));
	}

	@Test
	public void verifyDescription() {
		HasAttribute test = hasAttribute("attr1=1", 4);
		TestUtils.assertDescribeTo(test,
				"Has attribute on index 4: name: [attr1] value: [1]");
	}

	@Test
	public void verifyMismatchDescriptionAttributeName()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils.createDomNode("<span attr1=1>text</span>");
		HasAttribute test = hasAttribute("age=1", 0);
		TestUtils
				.assertDescribeMismatch(
						test,
						span,
						"On HtmlSpan[<span attr1=\"1\">] on attribute DomAttr[name=attr1 value=1] did not match expected name=age");
	}

	@Test
	public void verifyMismatchDescriptionAttributeValue()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils.createDomNode("<span attr1=1>text</span>");
		HasAttribute test = hasAttribute("attr1=2", 0);
		TestUtils
				.assertDescribeMismatch(
						test,
						span,
						"On HtmlSpan[<span attr1=\"1\">] on attribute DomAttr[name=attr1 value=1] did not match expected value=2");
	}

	@Test
	public void verifyMismatchDescriptionNoAttribute()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlElement span = TestUtils.createDomNode("<span attr1=1>text</span>");
		HasAttribute test = hasAttribute("attr1=1", 1);
		TestUtils
				.assertDescribeMismatch(test, span,
						"On HtmlSpan[<span attr1=\"1\">] did not have attribute on index 1");
	}

	@Test
	public void equals() {
		HasAttribute test1 = hasAttribute("attr1=1", 1);
		HasAttribute test2 = hasAttribute("attr1=1", 1);

		assertThat(test1, equalTo(test1));
		assertThat(test1, equalTo(test2));
		assertThat(test1.equals(null), is(false));
		assertThat(test1.equals(new Integer(2)), is(false));
	}

	@Test
	public void equalsFailsIfAttributeNameMismatch() {
		HasAttribute test1 = hasAttribute("attr1=1", 1);
		HasAttribute test2 = hasAttribute("attr2=1", 1);
		assertThat(test1, not(equalTo(test2)));
	}

	@Test
	public void equalsFailsIfAttributeValueMismatch() {
		HasAttribute test1 = hasAttribute("attr1=1", 1);
		HasAttribute test2 = hasAttribute("attr2=2", 1);
		assertThat(test1, not(equalTo(test2)));
	}

	@Test
	public void equalsFailsIfAttributeIndexMismatch() {
		HasAttribute test1 = hasAttribute("attr1=1", 1);
		HasAttribute test2 = hasAttribute("attr2=2", 2);
		assertThat(test1, not(equalTo(test2)));
	}

	@Test
	public void hash() {
		HasAttribute test1 = hasAttribute("attr1=1", 1);
		HasAttribute test2 = hasAttribute("attr1=1", 1);
		HasAttribute test3 = hasAttribute("attr2=1", 1);
		HasAttribute test4 = hasAttribute("attr1=2", 1);
		HasAttribute test5 = hasAttribute("attr1=1", 2);
		assertThat(test1.hashCode(), is(test2.hashCode()));
		assertThat(test1.hashCode(), is(not(test3.hashCode())));
		assertThat(test1.hashCode(), is(not(test4.hashCode())));
		assertThat(test1.hashCode(), is(not(test5.hashCode())));
	}
}
