
package at.f1l2.lab.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class Synchronized implements Runnable {

	private static AtomicInteger var = new AtomicInteger(0);

	private static int var2 = 0;

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Synchronized synchronized1 = new Synchronized();
		synchronized1.start();
		String hallo = "halExternalizeStringslo";
	}

	private void start() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(this);
			thread.start();
		}

		Thread.sleep(1000);
		System.out.println(var);
		System.out.println(var2);
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			var.incrementAndGet();
			var2++;
		}
	}
}