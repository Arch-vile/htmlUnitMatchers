package com.moonillusions.htmlUnitMatchers;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public abstract class ChainableMatcher<T> extends TypeSafeMatcher<T> {

	private boolean failed = false;
	private List<ChainableMatcher> matchers = new ArrayList<ChainableMatcher>();
	
	protected void addMatcher(ChainableMatcher matcher){
		this.matchers.add(matcher);
	}

	public final void describeTo(Description desc) {
		innerDescribe(desc, 0);
	}
	
	@Override
	protected final boolean matchesSafely(T arg0) {
		return matchesInner(arg0);
	}
	
	
	protected final boolean matchesInner(T arg0) {
		
		if(this.matchers.isEmpty()) {
			this.setFailed(!this.match(arg0));
		} else {
		
			for(ChainableMatcher<T> matcher : this.matchers) {
				boolean match = matcher.matchesInner(arg0);
				if(!match) {
					this.setFailed(true);
				}
			}
		}
		
		return !this.isFailed();
	}
	
	
	@Override
	protected void describeMismatchSafely(T item, Description mismatchDescription) {
		innerMismatch(item, mismatchDescription, 0);
	}
	
	private void innerDescribe(Description desc, int index) {
		desc.appendText("\n"+indent(index));
		this.chainedDescribeTo(desc);
		for(ChainableMatcher matcher : matchers) {
			matcher.innerDescribe(desc, index+1);
		}
	}
	
	private void innerMismatch(T item, Description desc, int index) {
		desc.appendText("\n"+indent(index));
		this.chainedMismatch(item, desc);
		for(ChainableMatcher matcher : matchers) {
			if(matcher.isFailed()) {
				matcher.innerMismatch(item, desc, index+1);
			}
		}
	}
	
	protected abstract void chainedDescribeTo(Description desc);
	protected abstract void chainedMismatch(T item, Description desc);
	protected abstract boolean match(T arg0);
	
	
	public boolean isFailed() {
		return failed;
	}

	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	private static String indent(int count) {
		String indent = "";
		for(int i = 0; i <= count; i++) {
			indent += "*";
		}
		return indent;
	}
	
}
