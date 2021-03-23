package com.byshu.syncthread.prodandconsum;

public class Producer implements Runnable {
  
	Basket bk;
	
	public Producer(Basket bk) {
		this.bk = bk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			Bread b = new Bread(i);
			bk.push(b);

			/*try {
				Thread.sleep((int)(Math.random()*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
	}
}
