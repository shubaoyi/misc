package com.byshu.syncthread.prodandconsum;

public class Consumer implements Runnable {

	Basket bk = null;

	public Consumer(Basket bk) {
		this.bk = bk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			bk.pop();
			/*try {
				Thread.sleep((int)(Math.random()*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
	}

}
