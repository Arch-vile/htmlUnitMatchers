package com.moonillusions.htmlUnitMatchers;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.net.MalformedURLException;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

import static com.moonillusions.htmlUnitMatchers.HasOptions.hasOptions;
import static org.hamcrest.MatcherAssert.assertThat;

public class HasOptionsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void matches() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		assertThat(
				(HtmlSelect)TestUtils.createDomNode("<select>"
						+ "<option value=\"1\">one</option>"
						+ "<option value=\"2\">two</option>"
						+ "</select>"), 
				hasOptions(
						new Option("one").withAttr("value",1),
						new Option("two").withAttr("value",2)));
	}
	
	@Test
	public void matchesNoOptions() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		assertThat(
				(HtmlSelect)TestUtils.createDomNode("<select>"
						+ "</select>"), 
				hasOptions());
	}
	
	@Test
	public void failsIfOptionAttributeValuesDiffer() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		Matcher<HtmlSelect> test = hasOptions(
				new Option("one").withAttr("value",1),
				new Option("two").withAttr("value","mismatch"));
		HtmlSelect select = (HtmlSelect)TestUtils.createDomNode("<select>"
				+ "<option value=\"1\">one</option>"
				+ "<option value=\"2\">two</option>"
				+ "</select>");
		
		assertThat(test.matches(select), equalTo(false));
	}
	
	
	
	@Test
	public void failsIfAsTextDiffer() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		Matcher<HtmlSelect> test = hasOptions(
				new Option("mismatch").withAttr("value",1));
		HtmlSelect select = (HtmlSelect)TestUtils.createDomNode("<select>"
				+ "<option value=\"1\">one</option>"
				+ "</select>");
		
		assertThat(test.matches(select), equalTo(false));
	}
	
	@Test
	public void failsIfTooFewOptions() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		Matcher<HtmlSelect> test = hasOptions(
				new Option("one").withAttr("value",1));
		HtmlSelect select = (HtmlSelect)TestUtils.createDomNode("<select>"
				+ "<option value=\"1\">one</option>"
				+ "<option value=\"2\">two</option>"
				+ "</select>");
		
		assertThat(test.matches(select), equalTo(false));
	}
	
	@Test
	public void failsIfTooManyOptions() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		Matcher<HtmlSelect> test = hasOptions(
				new Option("one").withAttr("value",1),
				new Option("two").withAttr("value",2),
				new Option("three").withAttr("value",3));
		HtmlSelect select = (HtmlSelect)TestUtils.createDomNode("<select>"
				+ "<option value=\"1\">one</option>"
				+ "<option value=\"2\">two</option>"
				+ "</select>");
		
		assertThat(test.matches(select), equalTo(false));
	}
	
	@Test
	public void failsIfNoOptions() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		Matcher<HtmlSelect> test = hasOptions();
		HtmlSelect select = (HtmlSelect)TestUtils.createDomNode("<select>"
				+ "<option value=\"1\">one</option>"
				+ "<option value=\"2\">two</option>"
				+ "</select>");
		
		assertThat(test.matches(select), equalTo(false));
	}
	
	@Test
	public void describe() {
		Matcher<HtmlSelect> test = hasOptions(
				new Option("one").withAttr("value",1),
				new Option("two").withAttr("value",2));
		Description desc = new StringDescription();
		test.describeTo(desc);
		assertThat(desc.toString(), 
				equalTo("HtmlSelect with options: "
						+ "<<option value='1'>one</option>>,"
						+ "<<option value='2'>two</option>>"));
	}

}
