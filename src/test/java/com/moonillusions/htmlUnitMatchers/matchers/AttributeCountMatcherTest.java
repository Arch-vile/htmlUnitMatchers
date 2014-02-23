package com.moonillusions.htmlUnitMatchers.matchers;

import org.junit.Test;

import static com.moonillusions.htmlUnitMatchers.matchers.AttributeCountMatcher.hasAttributes;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AttributeCountMatcherTest {

	@Test
	public void test() {
		// TODO: missing tests
	}

	@Test
	public void equals() {
		AttributeCountMatcher test1 = hasAttributes(2);
		AttributeCountMatcher test2 = hasAttributes(2);
		assertThat(test1, equalTo(test2));
	}

}
