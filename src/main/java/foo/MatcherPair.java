package foo;

import org.hamcrest.TypeSafeMatcher;
import org.w3c.dom.Node;

public class MatcherPair {
	private MyTypeSafeMatcher matcher;
	private Object matchee;
	public MatcherPair(MyTypeSafeMatcher matcher, Object matchee) {
		this.matcher = matcher;
		this.matchee = matchee;
	}
	public MyTypeSafeMatcher getMatcher() {
		return matcher;
	}
	public void setMatcher(MyTypeSafeMatcher matcher) {
		this.matcher = matcher;
	}
	public Object getMatchee() {
		return matchee;
	}
	public void setMatchee(Object matchee) {
		this.matchee = matchee;
	}
	public boolean isMatch() {
		return this.matcher.matches(matchee);
	}
	
	

}
