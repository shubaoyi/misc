package com.byshu.modifier;

public class SamePackage {
	
	Modifier m = new Modifier();
	
	public void test(){
		// protected变量 本包中的类可以访问
		m.a = 1;
	}

}
