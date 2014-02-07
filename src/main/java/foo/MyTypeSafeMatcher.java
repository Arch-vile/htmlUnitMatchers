package foo;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.moonillusions.htmlUnitMatchers.StringUtils;

public abstract class MyTypeSafeMatcher<T> extends TypeSafeMatcher<T> {
	
	public void describeInnerMismatch(T item,
			Description mismatchDescription, int matcherDepth) {
		mismatchDescription.appendText(StringUtils.indent(matcherDepth));
		this.describeMismatchSafely(item, mismatchDescription);
	}
	
	protected abstract void mismatch(T item, Description mismatchDescription);
	
}
