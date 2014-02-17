package com.moonillusions.htmlUnitMatchers.core;

import org.hamcrest.Description;

public class MatcherPair<T,F> {
	private ElementMatcher<T> matcher;
	private MatchableExtractor<T,F> extractor;
	
	public MatcherPair(ElementMatcher<T> matcher, MatchableExtractor<T,F> extractor) {
		this.matcher = matcher;
		this.extractor = extractor;
	}
	public ElementMatcher<T> getMatcher() {
		return matcher;
	}
	public void setMatcher(ElementMatcher<T> matcher) {
		this.matcher = matcher;
	}

	public boolean isMatch(F from) {
		return this.matcher.matches(extractor.extract(from));
	}
	public MatchableExtractor<T,F> getExtractor() {
		return extractor;
	}
	public T getMatchable(F from) {
		return this.extractor.extract(from);
	}
	
	public void proxyDescribeMismatchSafely(F from,
			Description mismatchDescription, int i) {
		getMatcher().describeMismatchSafely(getMatchable(from),
				mismatchDescription, i);

	}
	
	
	

}
