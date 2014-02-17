package com.moonillusions.htmlUnitMatchers.utils;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;

import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.moonillusions.htmlUnitMatchers.TestUtils;
import com.moonillusions.htmlUnitMatchers.utils.StringUtils;

import static org.junit.Assert.*;

public class StringUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlSelect select = (HtmlSelect)TestUtils.createDomNode("<select attr1=1>"
				+ "<option value=\"1\">one</option>"
				+ "<option value=\"2\">two</option>"
				+ "</select>");
		assertThat(StringUtils.print(select),
				equalTo("HtmlSelect[<select attr1=1><option value=1 selected=selected>one</option><option value=2>two</option></select>]"));
	}
	
	
	@Test
	public void test2() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlSpan span = (HtmlSpan) TestUtils.createDomNode("<span attr1='one' attr2=\"two\">text content</span>");
		assertThat(StringUtils.print(span),
				equalTo("HtmlSpan[<span attr1=one attr2=two>text content</span>]"));
	}

}
