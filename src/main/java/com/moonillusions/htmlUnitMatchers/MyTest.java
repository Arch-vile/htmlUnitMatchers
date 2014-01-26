package com.moonillusions.htmlUnitMatchers;

public class MyTest {
	
	
	public class SomeGeneric<T> {
		
		public SomeGeneric() {
			
		}
		
		public void addSome(SomeGeneric<? extends T> foo) {
			
		}
		
	}
	
	public class SomeNumber extends SomeGeneric<Number> {
		
		public void test() {
			addSome(new IntegerNumber());
		}
		
	}
	
	public class IntegerNumber extends SomeGeneric<Integer> {
		
		
	}
	

}
