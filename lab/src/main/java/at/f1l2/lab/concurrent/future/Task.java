package at.f1l2.lab.concurrent.future;

import org.apache.commons.lang3.RandomUtils;

public class Task {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String doWork() {
        try {

            System.out.println(Thread.currentThread().getName() + " executing task " + name);

            Thread.sleep(RandomUtils.nextLong(1000, 2000));
        } catch (Exception ex) {

        }
        return Thread.currentThread().getName() + " finished task " + name;
    }

}
