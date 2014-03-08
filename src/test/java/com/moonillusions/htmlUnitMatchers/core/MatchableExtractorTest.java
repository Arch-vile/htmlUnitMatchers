package com.moonillusions.htmlUnitMatchers.core;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MatchableExtractorTest {

	@Test
	public void returnsSameObject() {
		MatchableExtractor<Integer, Object> ext = new MatchableExtractor<Integer, Object>();
		assertThat(ext.extract(new Integer(2)), is(new Integer(2)));
	}
}
