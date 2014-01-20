package com.moonillusions.htmlUnitMatchers.testers;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.moonillusions.htmlUnitMatchers.TestUtils;

import static org.junit.Assert.*;

public class AsTextTesterTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span>foobar</span>");
		AsTextTester tester = new AsTextTester(span,"not me");
		tester.test();
		assertThat(tester.getTesting(), equalTo("Element as text"));
		assertThat(tester.getMessage(),equalTo("\"foobar\" did not match to \"not me\""));
		assertThat(tester.getTested(), equalTo(span));
		
	}

}
