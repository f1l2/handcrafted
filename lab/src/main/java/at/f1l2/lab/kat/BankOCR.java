package at.f1l2.lab.kat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BankOCR {

	private static final int NUMBER = 9;
	
	public String readOCR(List<String> files) {
		
	
		String result = "";
		for (int i = 0; i < NUMBER; i++) {
			
			String number = getNumberAtPosition(files, i);
			
			if (number.equals(" _ | ||_|")) {
				result = result + "0";
			} else if (number.equals("     |  |")) {
				result = result + "1";
			} else if (number.equals(" _  _||_ ")) {
				result = result + "2";
			} else if (number.equals(" _  _| _|")) {
				result = result + "3";
			} else if (number.equals("   |_|  |")) {
				result = result + "4";
			} else if (number.equals(" _ |_  _|")) {
				result = result + "5";
			} else if (number.equals(" _ |_ |_|")) {
				result = result + "6";
			} else if (number.equals(" _   |  |")) {
				result = result + "7";
			} else if (number.equals(" _ |_||_|")) {
				result = result + "8";
			} else if (number.equals(" _ |_| _|")) {
				result = result + "9";
			}
		}		
		return result;
	}
	
	public String getNumberAtPosition(List<String> files, int i) {
		return files.stream().map(item -> item.substring(0+i*3, 3+i*3)).collect(Collectors.joining());
	}
	
	
	public String readOCR(String fileName) {
		
		try (FileReader fr = new FileReader(fileName); BufferedReader br = new BufferedReader(fr)) {
			
			List<String> lines = new ArrayList<>();
			
			String line = "";
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			
			return readOCR(lines);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return "";
	}
	
}
