package com.moonillusions.htmlUnitMatchers.matchers;

import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.moonillusions.htmlUnitMatchers.core.ElementMatcher;
import com.moonillusions.htmlUnitMatchers.core.MatchableExtractor;
import com.moonillusions.htmlUnitMatchers.core.MatcherPair;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class MatcherCollectionTest {

	SimpleMatcher successMatcher1;
	SimpleMatcher successMatcher2;
	SimpleMatcher failMatcher1;
	MatchableExtractor<String, String> extractor1;
	MatchableExtractor<String, String> extractor2;

	@Before
	public void setup() {
		this.successMatcher1 = spy(new SimpleMatcher(true));
		this.successMatcher2 = spy(new SimpleMatcher(true));
		this.failMatcher1 = spy(new SimpleMatcher(false));
		this.extractor1 = new MatchableExtractor<String, String>() {
			@Override
			public String extract(String from) {
				return "ext1";
			}
		};
		this.extractor2 = new MatchableExtractor<String, String>() {
			@Override
			public String extract(String from) {
				return "ext2";
			}
		};
	}

	@Test
	public void executesAllMatchersOnSuccess() {

		MatcherCollection<String> matchingCollection = new MatcherCollection<String>() {
			{
				addMatcher(new MatcherPair<String, String>(successMatcher1,
						extractor1));
				addMatcher(new MatcherPair<String, String>(successMatcher2,
						extractor2));
			}

		};

		assertThat(matchingCollection.matches("foobar"), equalTo(true));
		verify(successMatcher1).match("ext1");
		verify(successMatcher2).match("ext2");

	}

	@Test
	public void stopMatchersOnFailure() {

		MatcherCollection<String> failingCollection = new MatcherCollection<String>() {
			{
				addMatcher(new MatcherPair<String, String>(failMatcher1,
						extractor2));
				addMatcher(new MatcherPair<String, String>(successMatcher1,
						extractor1));
			}
		};

		assertThat(failingCollection.matches("foobar"), equalTo(false));
		verify(failMatcher1).match("ext2");
		verify(successMatcher1, Mockito.times(0)).match(Mockito.anyString());

	}

	// TODO: missing tests
	// get failed matcher
	// describe to
	// mismatch desc

	private class SimpleMatcher extends ElementMatcher<String> {

		boolean match;

		public SimpleMatcher(boolean match) {
			this.match = match;
		}

		@Override
		protected void mismatch(String item, Description mismatchDescription) {
		}

		@Override
		protected boolean match(String item) {
			return this.match;
		}

		@Override
		protected void describe(Description arg0) {
		}

	}

}
