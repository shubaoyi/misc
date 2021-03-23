package com.byshu.overload;

public class Test {

    public void test(int arg1, String arg2) {

    }

    // 参数个数，类型相同，顺序不同可以重载
    public void test(String arg2, int arg1) {

    }

    public void test(String arg1) {

    }

    // 仅修饰符不同 不可以重载
	/*private void test (String arg1) {
		
	}*/

    // 仅返回值类型不同 不可以重载
	/*public int test (int arg1) {
		return 1;
	}*/

}
