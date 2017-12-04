package at.f1l2.laboratory.spring.importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class Importer {

	private static final Pattern THREE_DIGITS = Pattern.compile("^([1-9]\\.[0-9]\\.[0-9][ |\t])(.*)");

	private static final Pattern FOUR_DIGITS = Pattern.compile("^([1-9]\\.[0-9]\\.[0-9]\\.[0-9][ |\t])(.*)");

	public static void main(String[] args) throws IOException {

		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "context.xml" });

		final List<String> lines = readLinesOfFile(context.getResource("classpath:test.txt"));

		final List<String> preprocessedLines = preprocess(lines);

		final Map<String, String> products = extractProductStructures(preprocessedLines);

		final Map<String, String> productDescriptions = processProductStructures(products);

		for (Entry<String, String> productDescription : productDescriptions.entrySet()) {
			System.out.println("Key:" + productDescription.getKey());
			System.out.println(productDescription.getValue());
		}

	}

	private static Map<String, String> processProductStructures(Map<String, String> products) {

		Map<String, String> result = new HashMap<>();

		for (Entry<String, String> productStructure : products.entrySet()) {

			String[] rowOfProductStructure = productStructure.getValue().split("\n");
			// first row = label; second row = short description
			if (rowOfProductStructure.length <= 2) {
				continue;
			}

			List<String> rows = new ArrayList<>();
			String product;
			for (int i = 2; i < rowOfProductStructure.length; i++) {
				String row = rowOfProductStructure[i].trim();
				if (row.isEmpty()) {
					continue;
				}

				Matcher matcherThreeDigits = FOUR_DIGITS.matcher(row);
				if (matcherThreeDigits.find()) {
					product = matcherThreeDigits.group(2).trim();
					rows.add(product);
				} else {
					rows.add("<li>" + row + "</li>");
				}
			}

			addOuterElementEnumeration(rows);

			result.put(productStructure.getKey(), rows.stream().collect(Collectors.joining("")));
		}
		return result;
	}

	private static List<String> addOuterElementEnumeration(List<String> rows) {
		int beginEnumeration = findIndexOfFirstEnumeration(rows);
		if (beginEnumeration != -1) {
			rows.add(beginEnumeration, "<ul>");
			rows.add("</ul>");
		}
		return rows;
	}

	private static int findIndexOfFirstEnumeration(List<String> rows) {
		for (int i = 0; i < rows.size(); i++) {
			if (rows.get(i).startsWith("<li>")) {
				return i;
			}
		}
		return -1;
	}

	private static Map<String, String> extractProductStructures(List<String> lines) {
		final Map<String, String> products = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		String productStructure = null;

		for (String line : lines) {
			final Matcher matcherThreeDigits = THREE_DIGITS.matcher(line);
			if (matcherThreeDigits.find()) {
				if (productStructure != null) {
					products.put(productStructure, sb.toString());
					productStructure = matcherThreeDigits.group(2).trim();
					sb = new StringBuilder();
				} else {
					productStructure = matcherThreeDigits.group(2).trim();
				}
			}
			if (productStructure != null) {
				sb.append(line.trim() + "\n");
			}
		}
		products.put(productStructure, sb.toString());
		return products;
	}

	private static List<String> preprocess(List<String> lines) {
		return lines.stream().map(item -> item.trim()).collect(Collectors.toList());
	}

	private static List<String> readLinesOfFile(Resource resource) {

		final List<String> lines = new ArrayList<>();
		try {
			InputStream is = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

}
