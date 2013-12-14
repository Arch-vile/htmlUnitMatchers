package com.moonillusions.htmlUnitMatchers;


import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import static com.moonillusions.htmlUnitMatchers.HasOptions.hasOptions;
import static org.hamcrest.MatcherAssert.assertThat;

public class HasOptionsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMatch() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		assertThat(
				TestUtils.createDomNode("<select><option value=\"1\">one</option><option value=\"2\">two</option></select>"), 
				hasOptions());
	}

}
