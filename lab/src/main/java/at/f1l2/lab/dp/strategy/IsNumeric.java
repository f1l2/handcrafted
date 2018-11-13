package at.f1l2.lab.dp.strategy;

public class IsNumeric implements ValidationStrategy {

    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }

}
