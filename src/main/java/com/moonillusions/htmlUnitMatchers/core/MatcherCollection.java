package com.moonillusions.htmlUnitMatchers.core;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;

public abstract class MatcherCollection<T> extends ElementMatcher<T>{

	private MatcherPair<?,T> failedMatcher;
	private List<MatcherPair<?,T>> matchers = new ArrayList<MatcherPair<?,T>>();
	
	@Override
	public void describe(Description desc) {
		desc.appendText("Matches all:");
		
	}
	
	@Override
	protected void mismatch(T item, Description mismatchDescription) {
		mismatchDescription.appendText("Failed on:");
	}
	
	@Override
	protected void describeMismatchSafely(T item, Description mismatchDescription) {
		super.describeMismatchSafely(item, mismatchDescription);
		this.failedMatcher.proxyDescribeMismatchSafely(item,mismatchDescription,1);
	}
	
	@Override
	public void describeTo(Description arg0) {
		super.describeTo(arg0);
		for(MatcherPair<?,T> pair : this.matchers) {
			pair.getMatcher().describeTo(arg0,1);
		}
	}
	
	@Override
	protected boolean match(T arg0) {
		for(MatcherPair<?,T> pair : this.matchers) {
			if(!pair.isMatch(arg0)) {
				this.failedMatcher = pair;
				return false;
			}
		}
		
		return true;
	}
	
	protected void addMatcher(MatcherPair<?,T> pair) {
		this.matchers.add(pair);
	}
	
}
