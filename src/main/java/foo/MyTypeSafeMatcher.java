package foo;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.w3c.dom.Node;

import com.moonillusions.htmlUnitMatchers.StringUtils;

public abstract class MyTypeSafeMatcher<T> extends TypeSafeMatcher<T> {
	
	public void describeTo(Description arg0) {
		this.describe(arg0);
	}
	
	@Override
	protected boolean matchesSafely(T arg0) {
		return this.match(arg0);
	}
	
	@Override
	protected void describeMismatchSafely(T item,
			Description mismatchDescription) {
		mismatch(item, mismatchDescription);
	}
	
	

	
	
	protected abstract void mismatch(T item, Description mismatchDescription);
	protected abstract boolean match(T item);
	protected abstract void describe(Description arg0);
	
}
