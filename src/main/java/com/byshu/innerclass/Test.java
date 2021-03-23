package com.byshu.innerclass;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		Bean bean = test.new Bean();

		Bean1 bean1 = new Bean1();
	}

	/**
	 * @desc: 成员内部类
	 */
	class Bean {

	}

	/**
	 * 静态内部类
	 */
	static class Bean1 {

	}
}
