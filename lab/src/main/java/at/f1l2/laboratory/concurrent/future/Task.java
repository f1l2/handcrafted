package at.f1l2.laboratory.concurrent.future;


import org.apache.commons.lang3.RandomUtils;

public class Task  {

	
	public String doWork() {
		try {
		Thread.sleep(RandomUtils.nextLong(1000, 5000));
		} catch (Exception ex) {
			
		}
		return "done";
	}

}
