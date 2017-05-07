package at.f1l2.laboratory.curosity;

import java.util.Optional;
import java.util.logging.Logger;

public class AutoUnboxing {
	
	
	private static final Logger logger = Logger.getLogger(AutoUnboxing.class.getSimpleName());

	private static final Optional<String> optional = Optional.empty();
	
	public static void main(String[] args) {
		
		AutoUnboxing autoUnboxing = new AutoUnboxing();
		autoUnboxing.test(true);
	 
	}
	
	
	private void test(boolean condition) {
		Object o = condition ? Integer.valueOf(1) : Double.valueOf(2.0);
		
		if (o instanceof Integer) {
			logger.info(String.format("I'm a Integer %s", o.toString()));
		} else if (o instanceof Double) {
			logger.info("I'm a Double " + o.toString());
		}
		
		Integer integer = Integer.valueOf(2);
		Double d = Double.valueOf(2.0f);
		
		String string1 = "Hallo";
		
		if (condition) {
			d = null;
			integer = null;
			string1 = null;
			
		}
		
		o = condition ? string1 : d; 
		
		logger.info(o.toString());
	}

	
	private void dummyMethod(String hallo) {
		
		if (optional.isPresent()) {
			
			int i = 123;
			
			System.out.println(optional.get());
		}
	
		
	}
}
