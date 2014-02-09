package foo;

public class MatchableExtractor<T,S> {
	
	protected int index;
	
	public MatchableExtractor() {
	}
	
	public MatchableExtractor(int index) {
		this.index = index;
	}

	public T extract(S from) {
		return (T) from;
	}
	
}
