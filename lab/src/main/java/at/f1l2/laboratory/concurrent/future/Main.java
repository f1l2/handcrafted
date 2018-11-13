package at.f1l2.laboratory.concurrent.future;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<Task> tasks = Stream.of("a,b,c,d,e,f,g,h,i,j".split(",")).map(Task::new).collect(Collectors.toList());

        CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(tasks.get(0)::doWork).thenAccept(System.out::println);
        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(tasks.get(1)::doWork).thenAccept(System.out::println);
        CompletableFuture.supplyAsync(tasks.get(2)::doWork).thenAccept(System.out::println);
        CompletableFuture.supplyAsync(tasks.get(3)::doWork).thenAccept(System.out::println);
        CompletableFuture.supplyAsync(tasks.get(4)::doWork).thenAccept(System.out::println);
        CompletableFuture.supplyAsync(tasks.get(5)::doWork).thenAccept(System.out::println);
        CompletableFuture.supplyAsync(tasks.get(6)::doWork).thenAccept(System.out::println);
        CompletableFuture.supplyAsync(tasks.get(7)::doWork).thenAccept(System.out::println);
        CompletableFuture.supplyAsync(tasks.get(8)::doWork).thenAccept(System.out::println);

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2);

        CompletableFuture<Object> oneOf = CompletableFuture.anyOf(future1, future2);

        oneOf.thenAccept(item -> System.out.println("one"));
        combinedFuture.thenAccept(item -> System.out.println("a+b"));

        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
