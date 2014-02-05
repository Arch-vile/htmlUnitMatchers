package com.moonillusions.htmlUnitMatchers;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

public abstract class ChainableMatcher<T> extends TypeSafeMatcher<T> {

	MatchableExtractor extractor;
	
	private boolean failed = false;
	private List<ChainableMatcher> matchers = new ArrayList<ChainableMatcher>();
	
	private Object failedItem;
	
	protected void addMatcher(ChainableMatcher matcher){
		this.matchers.add(matcher);
	}
	
	
	public void setExtractor(MatchableExtractor extractor) {
		this.extractor = extractor;
	}

	public final void describeTo(Description desc) {
		innerDescribe(desc, 0);
	}
	
	@Override
	protected final boolean matchesSafely(T item) {
		return matchesInner(item);
	}
	
	final Object matchAgainst(Object from) {
		if(this.extractor != null) {
			return this.extractor.getMatchable(from);
		}
		return from;
	}
	
	protected final boolean matchesInner(Object item) {
		
		boolean match = this.match(matchAgainst(item));
		
		if(match) {
			for(ChainableMatcher matcher : this.matchers) {
				if(! matcher.matchesInner(item)){
					this.setFailed(true);
					return false;
				}
			}
			return true;
			
		} else {
			this.setFailed(true);
			failedItem = matchAgainst(item);
			return false;
		}
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
	
	private void innerMismatch(Object item, Description desc, int index) {
		Object matched = matchAgainst(item);
		desc.appendText("\n"+indent(index));
		this.chainedMismatch(matched, desc);
		for(ChainableMatcher matcher : matchers) {
			if(matcher.isFailed()) {
				matcher.innerMismatch(matched,desc, index+1);
			}
		}
	}
	
	
	protected abstract void chainedDescribeTo(Description desc);
	protected boolean match(Object item) {
		return true;
	}
	
	protected void chainedMismatch(Object item, Description desc){
		desc.appendText("On " + StringUtils.print((DomNode)item));
	}
	
	
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
