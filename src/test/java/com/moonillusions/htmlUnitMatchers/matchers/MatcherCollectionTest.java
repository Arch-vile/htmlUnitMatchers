package com.moonillusions.htmlUnitMatchers.matchers;

import java.util.List;

import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.moonillusions.htmlUnitMatchers.TestUtils;
import com.moonillusions.htmlUnitMatchers.core.ElementMatcher;
import com.moonillusions.htmlUnitMatchers.core.MatchableExtractor;
import com.moonillusions.htmlUnitMatchers.core.MatcherPair;

import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.on;
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
	MatcherCollection<String> failingCollection;
	MatcherCollection<String> matchingCollection;

	@Before
	public void setup() {
		this.successMatcher1 = spy(new SimpleMatcher(true, "success1",
				"fail on success1"));
		this.successMatcher2 = spy(new SimpleMatcher(true, "success2",
				"fail on success2"));
		this.failMatcher1 = spy(new SimpleMatcher(false, "fail1",
				"fail on fail1"));
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
		matchingCollection = new MatcherCollection<String>() {
			{
				addMatcher(new MatcherPair<String, String>(successMatcher1,
						extractor1));
				addMatcher(new MatcherPair<String, String>(successMatcher2,
						extractor2));
			}

		};
		failingCollection = new MatcherCollection<String>() {
			{
				addMatcher(new MatcherPair<String, String>(failMatcher1,
						extractor2));
				addMatcher(new MatcherPair<String, String>(successMatcher1,
						extractor1));
			}
		};
	}

	@Test
	public void executesAllMatchersOnSuccess() {
		assertThat(matchingCollection.matches("foobar"), equalTo(true));
		@SuppressWarnings("unchecked")
		List<SimpleMatcher> matchers = (List<SimpleMatcher>) (List<?>) extract(
				matchingCollection.getMatchers(), on(MatcherPair.class)
						.getMatcher());

		assertThat(matchers.size(), equalTo(2));
		assertThat((SimpleMatcher) matchers.get(0), equalTo(successMatcher1));
		assertThat((SimpleMatcher) matchers.get(1), equalTo(successMatcher2));
		verify(successMatcher1).match("ext1");
		verify(successMatcher2).match("ext2");
	}

	@Test
	public void stopMatchersOnFailure() {
		assertThat(failingCollection.matches("foobar"), equalTo(false));
		verify(failMatcher1).match("ext2");
		verify(successMatcher1, Mockito.times(0)).match(Mockito.anyString());

	}

	@Test
	public void getFailingMatcher() {
		assertThat(failingCollection.matches("foobar"), equalTo(false));
		assertThat((SimpleMatcher) failingCollection.getFailedMatcher()
				.getMatcher(), equalTo(failMatcher1));
	}

	@Test
	public void describeTo() {
		TestUtils.assertDescribeTo(failingCollection,
				"Matches all:\n*fail1\n*success1");
	}

	@Test
	public void mismatch() {
		TestUtils.assertDescribeMismatch(failingCollection, "foobar",
				"Failed on:\n*Fail on ext2 due to fail on fail1");
	}

	private class SimpleMatcher extends ElementMatcher<String> {

		boolean match;
		String description;
		String mismatch;

		public SimpleMatcher(boolean match, String description, String mistmatch) {
			this.match = match;
			this.description = description;
			this.mismatch = mistmatch;
		}

		@Override
		protected void mismatch(String item, Description mismatchDescription) {
			mismatchDescription.appendText(String.format(
					"Fail on %s due to %s", item, this.mismatch));
		}

		@Override
		protected boolean match(String item) {
			return this.match;
		}

		@Override
		protected void describe(Description arg0) {
			arg0.appendText(description);
		}

	}

}
