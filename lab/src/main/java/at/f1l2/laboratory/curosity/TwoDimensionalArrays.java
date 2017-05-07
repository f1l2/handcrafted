package at.f1l2.laboratory.curosity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TwoDimensionalArrays {

	
    // No throws clause here
    /**
     * @param args
     */
    /**
     * @param args
     */
    /**
     * @param args
     */
    public static void main(String[] args) {
        
    	int size = 1000000;
    	int[] array = new int[size];
    	
    	List<Integer> integers = new ArrayList<>();
    	
    	for (int i = 0; i < size; i++) {
    		array[i] = i; 
    		integers.add(i);
    	}
    	
    	
    	
    	LocalDateTime start2 = LocalDateTime.now();
    	int sum2 = 0;
    	for (int i = 0; i < array.length; i++) {
    		sum2 = sum2 + array[i];
    	}
    	System.out.println(sum2);
    	Duration duration2 = Duration.between(start2, LocalDateTime.now());
    	
    	System.out.println("Duration " + duration2);
    	
    	
    	
    	
    	LocalDateTime start1 = LocalDateTime.now();
    	
    	int sum1 = integers.stream().mapToInt(i -> i).sum();
    	
    	System.out.println(sum1);
    	Duration duration1 = Duration.between(start1, LocalDateTime.now()); 
    	System.out.println("Duration " + duration1);
    	
    	
    	
    	System.out.println(duration2.getNano()*100/duration1.getNano());
    	
    	
    	
    	
    }
 }
