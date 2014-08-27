package com.moonillusions.htmlUnitMatchers.matchers;

import static com.moonillusions.htmlUnitMatchers.matchers.HasOption.hasOption;
import static com.moonillusions.htmlUnitMatchers.matchers.HasOption.hasOptions;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.moonillusions.htmlUnitMatchers.TestUtils;

public class HasOptionTest {

	@Test
	public void matchesSingleOption() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		HtmlSelect select = (HtmlSelect) TestUtils
				.createDomNode("<select><option value=myvalue>mytext</option></select>");
		assertThat(select, hasOption("mytext", "myvalue", 0));
	}

	@Test
	public void matchesFromMultipleOptions()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		HtmlSelect select = (HtmlSelect) TestUtils.createDomNode("<select>"
				+ "<option value=somevalue>some text</option>"
				+ "<option value=myvalue>my text</option>" + "</select>");
		assertThat(select, hasOption("my text", "myvalue", 1));
	}

	@Test
	public void matchesWithoutValue() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		HtmlSelect select = (HtmlSelect) TestUtils
				.createDomNode("<select><option>mytext</option></select>");
		assertThat(select, hasOption("mytext", "", 0));
	}

	@Test
	public void matchesWithoutText() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		HtmlSelect select = (HtmlSelect) TestUtils
				.createDomNode("<select><option value=1></option></select>");
		assertThat(select, hasOption("", "1", 0));
	}

	@Test
	public void matchesSelected() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		HtmlSelect select = (HtmlSelect) TestUtils.createDomNode("<select>"
				+ "<option value=myValue selected=selected>my text</option>"
				+ "<option value=myValue2>my text2</option>" + "</select>");
		assertThat(select, hasOption("my text", "myValue", true, 0));
	}

	@Test
	public void mismatchIfWrongValue() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		HtmlSelect select = (HtmlSelect) TestUtils
				.createDomNode("<select><option value=notMyValue>mytext</option></select>");
		assertThat(select, not(hasOption("mytext", "myvalue", 0)));
	}

	@Test
	public void mismatchIfWrongText() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		HtmlSelect select = (HtmlSelect) TestUtils
				.createDomNode("<select><option value=myValue>not my text</option></select>");
		assertThat(select, not(hasOption("my text", "myValue", 0)));
	}

	@Test
	public void mismatchIfNotSelected() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		HtmlSelect select = (HtmlSelect) TestUtils.createDomNode("<select>"
				+ "<option value=myValue selected=selected>my text</option>"
				+ "<option value=myValue2>my text2</option>" + "</select>");

		assertThat(select, not(hasOption("my text2", "myValue2", true, 1)));
	}

	@Test
	public void mismatchIfSelected() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		HtmlSelect select = (HtmlSelect) TestUtils.createDomNode("<select>"
				+ "<option value=myValue selected=selected>my text</option>"
				+ "<option value=myValue2>my text2</option>" + "</select>");
		assertThat(select, not(hasOption("my text", "myValue", false, 0)));
	}

	@Test
	public void matchMultiple() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		HtmlSelect select = (HtmlSelect) TestUtils.createDomNode("<select>"
				+ "<option value=first>first</option>"
				+ "<option value=second>second</option>"
				+ "<option value=myValue2>myValue2</option>" + "</select>");
		assertThat(select, hasOptions("first", "second", "myValue2"));
	}

	@Test
	public void mismatchMultiple() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		HtmlSelect select = (HtmlSelect) TestUtils.createDomNode("<select>"
				+ "<option value=first>first</option>"
				+ "<option value=second>third</option>"
				+ "<option value=myValue3>myValue3</option>" + "</select>");
		assertThat(select, not(hasOptions("first", "second", "myValue2")));
	}

	@Test
	public void mismatch_null_description()
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		TestUtils.assertDescribeMismatch(hasOption("mytext", "myvalue", 0),
				null, "given HtmlSelect element was null");
	}

	//
	// TODO: missing tests
	// hasCorrectMatchers
	// mismatchIfSelected
	// describe
	// mismatch

}
