package at.f1l2.laboratory.dp.strategy;

import org.junit.Test;

public class Strategy {

	/**
	 * The strategy pattern is a common solution for representing a family of
	 * algorithms and letting you choose among them at runtime.
	 *
	 */

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
		System.out.println(v2.validate("bbb"));
	}
}
