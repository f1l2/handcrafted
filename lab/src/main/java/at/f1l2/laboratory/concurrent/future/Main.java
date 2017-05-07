package at.f1l2.laboratory.concurrent.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Main {

	public static void main(String[] args) {
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task());
		tasks.add(new Task());
		tasks.add(new Task());
		

		Task task = new Task();
		
		CompletableFuture<String> receiver = CompletableFuture.supplyAsync(task::doWork);
		
		
		
		
	}

}
