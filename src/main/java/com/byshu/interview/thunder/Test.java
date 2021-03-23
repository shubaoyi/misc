package com.byshu.interview.thunder;

public class Test {

	
	
	public static void main(String[] args) {
		PrintRunnable run = new PrintRunnable();
		Thread a = new Thread(run, "A");
		Thread b = new Thread(run, "B");
		Thread c = new Thread(run, "C");
		a.start();
		b.start();
		c.start();
	}
	
}
