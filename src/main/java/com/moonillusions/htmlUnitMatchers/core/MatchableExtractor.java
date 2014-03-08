package com.moonillusions.htmlUnitMatchers.core;

public class MatchableExtractor<M, F> {

	public MatchableExtractor() {
	}

	public M extract(F from) {
		return (M) from;
	}

}
