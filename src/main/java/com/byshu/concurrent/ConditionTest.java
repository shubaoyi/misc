package com.byshu.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    public static void main(String[] args) {
        ConditionTest test = new ConditionTest();
        new TestThread(test, 1).start();
        new TestThread(test, 1).start();
        new TestThread(test, 1).start();
        new TestThread(test, 2).start();
    }

    Lock lock = new ReentrantLock();
    Condition readCon = lock.newCondition();
    Condition writeCon = lock.newCondition();
    Object[] datas = new Object[100];
    int writeIndex, readIndex, count;
    int index;
	
	/*public void write(Object data) throws InterruptedException {
	  lock.lock();
		try {
			while (count == datas.length) {
				writeCon.await();
			}
			datas[writeIndex] = data;
			if (++writeIndex == datas.length) {
				writeIndex = 0;
			}
			count++;
			readCon.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public Object read() throws InterruptedException {
	  lock.lock();
		try {
			while (count == 0) {
				readCon.await();
			}
			Object obj = datas[readIndex];
			if (++readIndex == datas.length) {
				readIndex = 0;
			}
			count--;
			writeCon.signalAll();
			return obj;
		} finally {
			lock.unlock();
		}
	}*/

    public void write(Object data) throws InterruptedException {
        lock.lock();
        try {
            while (index == datas.length) {
                writeCon.await();
            }
            datas[index++] = data;
            readCon.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Object read() throws InterruptedException {
        lock.lock();
        try {
            while (index == 0) {
                readCon.await();
            }
            Object obj = datas[--index];
            writeCon.signalAll();
            return obj;
        } finally {
            lock.unlock();
        }
    }

}

class TestThread extends Thread {

    ConditionTest arg;
    int flag;

    public TestThread(ConditionTest arg, int flag) {
        this.arg = arg;
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag == 1) {
            for (int i = 0; i < 10; i++) {
                try {
                    arg.write(i);
                    System.err.println(Thread.currentThread().getName() + "写入数据" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (int i = 0; i < 30; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + "读取数据" + arg.read());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
