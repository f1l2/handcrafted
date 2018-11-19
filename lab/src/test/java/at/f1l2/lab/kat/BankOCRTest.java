package at.f1l2.lab.kat;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;


public class BankOCRTest {
	
	
	
	@Test
	public void runTests() {
		
		BankOCR bankOCR = new BankOCR();
		
		List<String> result = Stream.of("0,1,2,3,4,5,6,7,8,9".split(","))
				
				.map(item -> "src/test/java/at/f1l2/lab/kat/bankOCR".concat(item).concat(".txt"))
				//
				.map(bankOCR::readOCR)
				
				.collect(Collectors.toList());
		
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(String.join("", Collections.nCopies(9, String.valueOf(i))), result.get(i));
		}
	}

}
