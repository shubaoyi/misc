package com.byshu.modifier.inner;

import com.byshu.modifier.Modifier;

public class SubPackageTest {

	Modifier m = new Modifier();

	public void test() {
		// protected变量 子包中的类不可以访问
		// m.a = 1;
	}

}
