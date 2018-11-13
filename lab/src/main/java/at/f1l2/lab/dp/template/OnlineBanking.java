package at.f1l2.lab.dp.template;

import java.util.function.Consumer;

public abstract class OnlineBanking {

    /**
     * Template Method Pattern
     * 
     * It is a common solution when you need the flexibility to change certain parts of a class.
     * 
     */

    /**
     * The pattern uses an abstract class as sketch. Different applications can provide different implementations of the abstract class.
     * 
     */

    public void processCustomer(int id) {
        makeCustomerHappy();
    }

    abstract void makeCustomerHappy();

    /**
     * With the help of lambda expression, the plug in part is passed via parameter. The functional interface Consumer<T> is used as assignment target.
     * 
     * (String s) -> System.out.println(s);
     * 
     */
    public void processCustomer(int id, Consumer<String> makeCusomterHappy) {
        makeCusomterHappy.accept("Happy");
    }

}
