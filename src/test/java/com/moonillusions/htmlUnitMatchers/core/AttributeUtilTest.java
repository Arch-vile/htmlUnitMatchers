package com.moonillusions.htmlUnitMatchers.core;

import org.junit.Test;

import com.moonillusions.htmlUnitMatchers.utils.AttributeUtil;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AttributeUtilTest {

	@Test
	public void getNameFromNameValuePair() {
		String attribute = "foo=bar";
		assertThat(AttributeUtil.getName(attribute), is("foo"));
	}

	@Test
	public void getValueFromNameValuePair() {
		String attribute = "foo=bar";
		assertThat(AttributeUtil.getValue(attribute), is("bar"));
	}

	@Test
	public void getNameFromNoValue() {
		String attribute = "foo";
		assertThat(AttributeUtil.getName(attribute), is("foo"));
	}

	@Test
	public void getValueFromNoValue() {
		String attribute = "foo";
		assertThat(AttributeUtil.getValue(attribute), equalTo(null));
	}

}
