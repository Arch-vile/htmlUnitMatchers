package com.moonillusions.htmlUnitMatchers.matchers;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Ignore;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.moonillusions.htmlUnitMatchers.TestUtils;

import static com.moonillusions.htmlUnitMatchers.matchers.HasOption.hasOption;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

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
		assertThat(select, hasOption("my text", "myvalue", true, 1));
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
	@Ignore
	public void mismatchIfNotSelected() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		HtmlSelect select = (HtmlSelect) TestUtils
				.createDomNode("<select><option value=myValue>my text</option></select>");
		assertThat(select, not(hasOption("my text", "myValue", false, 0)));
	}

	//
	//
	// TODO: missing tests
	// hasCorrectMatchers
	// mismatchIfSelected
	// describe
	// mismatch

}
