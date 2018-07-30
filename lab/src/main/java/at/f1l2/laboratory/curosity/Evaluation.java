package at.f1l2.laboratory.curosity;

public class Evaluation {

    /**
     * Evaluation order is specified as left to right.
     * 
     *  
     */
    
    public static void main(String[] args) {
        Evaluation parameterEvaluation = new Evaluation();
        parameterEvaluation.test1();
    }

    public void test1() {
        int i = 0;
        
        /**
         * left-to-right rule means that i++ is first evaluated before making the call to operation(i)
         * 
         * The value of i++ used in the calculation will be zero, because the value of a post-increment expression is the value prior to incrementing the variable.
         *  
         */
        System.out.print(i++ + operation(i));
        System.out.print(i);
    }

    private int operation(int i) {
        System.out.print(i++);
        return i;
    }
}
