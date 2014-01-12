package com.moonillusions.htmlUnitMatchers;

import java.io.IOException;
import static org.hamcrest.Matchers.equalTo;

import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;

import static org.junit.Assert.*;

public class StringUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlSpan span = (HtmlSpan) TestUtils.createDomNode("<span attr1='one' attr2=\"two\">text content</span>");
		assertThat(StringUtils.print(span),
				equalTo("HtmlSpan[<span attr1=\"one\" attr2=\"two\">\\n text content\\n</span>\\n]"));
	}

}
