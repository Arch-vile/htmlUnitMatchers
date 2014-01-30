package com.moonillusions.htmlUnitMatchers;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

public abstract class ChainableMatcher<T> extends TypeSafeMatcher<T> {

	private boolean failed = false;
	private List<ChainableMatcher> matchers = new ArrayList<ChainableMatcher>();
	
	private Object failedItem;
	
	protected void addMatcher(ChainableMatcher matcher){
		this.matchers.add(matcher);
	}

	public final void describeTo(Description desc) {
		innerDescribe(desc, 0);
	}
	
	@Override
	protected final boolean matchesSafely(T item) {
		return matchesInner(item);
	}
	
	
	protected final boolean matchesInner(T item) {
		
		if(this.matchers.isEmpty()) {
			this.setFailed(!this.match(this.matchAgainst(item)));
		} else {
			
//			boolean matched = true;
			
//			for(Object value : this.matchAgainst(item)) {
//				for(ChainableMatcher matcher : this.matchers) {
//					if(!matcher.matchesInner(value)) {
//						matched = false;
//					}
//				}
//			}
			
			for(int i = 0; i < this.matchers.size(); i++) {
				if(! this.matchers.get(i).matchesInner(matchAgainst(item).get(i)) ) {
//					matched = false;
					this.failedItem = matchAgainst(item).get(i);
					this.setFailed(true);
					return false;
				}
			}
			
//			if(!matched) {
//				this.setFailed(true);
//			}
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
				matcher.innerMismatch(this.failedItem, desc, index+1);
			}
		}
	}
	
	protected abstract List matchAgainst(T item);
	protected abstract void chainedDescribeTo(Description desc);
	protected abstract boolean match(List arg0);
	
	protected void chainedMismatch(T item, Description desc){
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
