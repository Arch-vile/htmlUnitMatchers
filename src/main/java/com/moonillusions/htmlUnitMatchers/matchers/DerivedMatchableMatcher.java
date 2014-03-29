package com.moonillusions.htmlUnitMatchers.matchers;

import org.hamcrest.TypeSafeMatcher;

import com.moonillusions.htmlUnitMatchers.core.MatchableExtractor;

public abstract class DerivedMatchableMatcher<T, F> extends TypeSafeMatcher<T> {

	private MatchableExtractor<T, F> extractor;

	public DerivedMatchableMatcher(MatchableExtractor<T, F> extractor) {
		this.extractor = extractor;
	}

	protected T matchable(F item) {
		if (this.extractor == null) {
			return (T) item;
		} else {
			return this.extractor.extract(item);
		}
	}
}
