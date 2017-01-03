package at.f1l2.laboratory.dp.strategy;

import org.junit.gen5.api.Test;

public class Strategy {

    @Test
    public void withoutLambda() {
        Validator v1 = new Validator(new IsNumeric());
        System.out.println(v1.validate("aaa"));

        Validator v2 = new Validator(new IsAllLowerCase());
        System.out.println(v2.validate("bbb"));
    }

    @Test
    public void withLambda() {
        Validator v1 = new Validator((String s) -> s.matches("\\d+"));
        System.out.println(v1.validate("aaa"));

        Validator v2 = new Validator((String s) -> s.matches("[a-z]+"));
        System.out.println(v1.validate("bbb"));
    }
}
