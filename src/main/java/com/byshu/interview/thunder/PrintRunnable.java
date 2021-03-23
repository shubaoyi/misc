package com.byshu.interview.thunder;

public class PrintRunnable implements Runnable {
	
	public char history = '\0';

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			print();
		}
	}
	
	
	public synchronized void print() {
			char name = Thread.currentThread().getName().charAt(0);
			switch (name) {
			case 'A':
			  try {
  				while(history != '\0' && history != 'C') {
  						wait();
  				}
			  } catch (InterruptedException e) {
			    e.printStackTrace();
			  }
				break;
				
			case 'B':
			  try {
  				while(history != 'A') {
  						wait();
  				}
			  } catch (InterruptedException e) {
			    e.printStackTrace();
			  }
				break;
				
			case 'C':
			  try {
  				while(history != 'B') {
  						wait();
  				}
			  } catch (InterruptedException e) {
			    e.printStackTrace();
			  }
				break;
			}
System.out.println(name);
			history = name;
			notifyAll();
	}

}
